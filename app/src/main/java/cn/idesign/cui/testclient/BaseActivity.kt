package cn.idesign.cui.testclient

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import cn.idesign.cui.testclient.ui.theme.CUITestTheme
import cn.idesign.cui.testclient.ui.theme.Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

abstract class BaseActivity : ComponentActivity() {
    val THEME_KEY = "cui-theme-key"
    val LIGHT_THEME = "light"
    val DARK_THEME = "dark"
    val BLUE_THEME = "blue"
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("RememberReturnType")
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        sharedPreferences = getSharedPreferences("cui", MODE_PRIVATE)

        setContent {
            val theme = sharedPreferences.getString(THEME_KEY, BLUE_THEME)
            var themeType by remember(theme) {
                mutableStateOf(getThemeType(theme!!))
            }
            CUITestTheme(theme = themeType) {
                val systemUiController = rememberSystemUiController()
                val primary = MaterialTheme.colors.primary

                remember(primary) {
                    systemUiController.setStatusBarColor(primary)
                }

                var expanded by remember {
                    mutableStateOf(false)
                }
                val menuItems =
                    listOf(
                        ThemeModel("亮主题", LIGHT_THEME),
                        ThemeModel("暗主题", DARK_THEME),
                        ThemeModel("蓝主题", BLUE_THEME),
                    )

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = title(),
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            actions = {

                                IconButton(onClick = {
                                    expanded = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    offset = DpOffset((-56).dp, (-56).dp),
                                    onDismissRequest = { expanded = false }) {

                                    menuItems.forEach {
                                        DropdownMenuItem(onClick = {

                                            themeType = getThemeType(it.value)
                                            save(it)
                                            expanded = false
                                        }) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                RadioButton(
                                                    selected = it.value == theme,
                                                    onClick = {
                                                        themeType = getThemeType(it.value)
                                                        save(it)
                                                        expanded = false
                                                    })
                                                Text(text = it.label)
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    Surface {
                        Render()
                    }
                }
            }
        }
    }

    private fun getThemeType(it: String): Theme {
        return when (it) {
            DARK_THEME -> Theme.Dark
            BLUE_THEME -> Theme.Blue
            else -> Theme.Light
        }
    }

    private fun save(model: ThemeModel) {
        sharedPreferences.edit().putString(THEME_KEY, model.value).apply()
    }

    @Composable
    abstract fun Render()

    abstract fun title(): String

}

data class ThemeModel(
    val label: String,
    val value: String,
)