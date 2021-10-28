package app.ui.dialogs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.input.pointer.isPrimaryPressed
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.git.ResetType

@Composable
fun ResetBranchDialog(
    onReject: () -> Unit,
    onAccept: (resetType: ResetType) -> Unit
) {
    var resetType by remember { mutableStateOf(ResetType.MIXED) }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
    ) {
        RadioButtonText(
            selected = resetType == ResetType.SOFT,
            onClick = {
                resetType = ResetType.SOFT
            },
            text = "Soft reset"
        )
        RadioButtonText(
            selected = resetType == ResetType.MIXED,
            onClick = {
                resetType = ResetType.MIXED
            },
            text = "Mixed reset"
        )
        RadioButtonText(
            selected = resetType == ResetType.HARD,
            onClick = {
                resetType = ResetType.HARD
            },
            text = "Hard reset"
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.End)
        ) {
            TextButton(
                modifier = Modifier.padding(end = 8.dp),
                onClick = {
                    onReject()
                }
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    onAccept(resetType)
                }
            ) {
                Text("Reset branch")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RadioButtonText(
    selected: Boolean,
    text: String,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .mouseClickable {
                if(this.buttons.isPrimaryPressed) {
                    if (onClick != null) {
                        onClick()
                    }
                }
            }
    ) {
        RadioButton(
            selected,
            onClick,
            modifier,
            enabled,
            interactionSource,
            colors
        )

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}