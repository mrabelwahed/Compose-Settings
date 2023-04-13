package com.ramadanapps.composesettings.ui.screen

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramadanapps.composesettings.R
import com.ramadanapps.composesettings.SettingsViewModel
import com.ramadanapps.composesettings.model.SettingsState
import kotlin.math.min

@Composable
fun Settings() {
    val settingsViewModel: SettingsViewModel = viewModel()
    MaterialTheme {
        val state = settingsViewModel.uiState.collectAsState().value
        SettingsList(
            state = state,
            onNotificationToggled = { status ->
                settingsViewModel.toggleNotificationsSettings(status)
            },
            onHintSelected = { selected ->
                settingsViewModel.toggleHintSSettings(selected)
            },
            onSettingsClicked = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsList(
    state: SettingsState, modifier: Modifier = Modifier,
    onNotificationToggled: (Boolean) -> Unit,
    onHintSelected: (Boolean) -> Unit,
    onSettingsClicked: () -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.cd_go_back
                        )
                    )
                }
            },
            title = {
                Text(text = stringResource(id = R.string.settings))
            },
        )

        NotificationSettings(
            modifier = Modifier.fillMaxWidth(),
            title = "Enabled Notification",
            isChecked = state.notificationsEnabled,
            onNotificationToggled = { flag ->
                onNotificationToggled(flag)
            }
        )
        Divider()

        HintSettings(
            modifier = Modifier.fillMaxWidth(),
            title = "Show Hints", isChecked = state.hintEnabled, onHintSelected = { flag ->
                onHintSelected(flag)
            })

        Divider()

        ManageSubscriptionSettingItem (
            modifier = Modifier.fillMaxWidth(),
            title = "Manage Subscriptions",
            onSettingClicked = onSettingsClicked
        )

        Divider()
        SectionSpacer(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun NotificationSettings(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: Boolean,
    onNotificationToggled: (Boolean) -> Unit
) {
    val desc = if (isChecked)
        stringResource(R.string.cd_notifications_enabled)
    else
        stringResource(
            id = R.string.cd_notifications_disabled
        )
    SettingsItem(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .toggleable(
                    value = isChecked,
                    onValueChange = onNotificationToggled,
                    role = Role.Switch
                )
                .semantics {
                    stateDescription = desc
                }
                .padding(horizontal = 16.dp)
                .padding(vertical = 10.dp)) {
            Text(text = title, modifier = Modifier.weight(1f))
            Switch(checked = isChecked, onCheckedChange = null)
        }
    }
}

@Composable
fun HintSettings(
    modifier: Modifier = Modifier,
    title: String,
    isChecked: Boolean,
    onHintSelected: (Boolean) -> Unit
) {
    val desc = if (isChecked)
        stringResource(R.string.cd_hints_enabled)
    else
        stringResource(
            id = R.string.cd_hints_disabled
        )
    SettingsItem(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .toggleable(
                    value = isChecked,
                    onValueChange = onHintSelected,
                    role = Role.Checkbox
                )
                .semantics {
                    stateDescription = desc
                }
                .padding(horizontal = 16.dp)
                .padding(vertical = 10.dp)) {
            Text(text = title, modifier = Modifier.weight(1f))
            Checkbox(checked = isChecked, onCheckedChange = null)
        }
    }
}

@Composable
fun ManageSubscriptionSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    onSettingClicked: () -> Unit
) {
    SettingsItem {
        Row(
            modifier = Modifier
                .clickable(
                    onClickLabel = stringResource(
                        id = R.string.cd_open_subscription, title
                    )
                ) { onSettingClicked() }
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier.heightIn(min = 56.dp)) {
        content()
    }
}

@Composable
fun SectionSpacer(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .height(48.dp)
        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)) )
}