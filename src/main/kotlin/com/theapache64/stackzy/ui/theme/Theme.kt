package com.theapache64.stackzy.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.theapache64.stackzy.ui.common.ToolBar

// Colors
private val TelegramBlue = Color(48, 163, 230)
val BigStone = Color(0xff0D1D32)
private val Elephant = Color(0xff0D2841)

// Color set
val LightTheme = lightColors() // TODO :
val DarkTheme = darkColors(
    primary = TelegramBlue,
    onPrimary = Color.White,
    secondary = Elephant,
    onSecondary = Color.White,
    surface = BigStone,
)

@Composable
fun StackzyTheme(
    title: String = "",
    displayToolbar: Boolean = true,
    isDark: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = StackzyTypography
    ) {
        Surface {
            Column {
                if (displayToolbar) {
                    Column {
                        ToolBar(
                            title = title
                        )
                        content()
                    }
                } else {
                    content()
                }
            }
        }
    }
}