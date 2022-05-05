package cn.idesign.cui.testclient.segmented

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.segmented.Segmented
import cn.idesign.cui.segmented.SegmentedModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun SegmentedTest() {
    var commonValue by mutableStateOf("Apple")
    var disabledValue by mutableStateOf("Apple")
    var iconValue by mutableStateOf("Apple")
    var blockValue by mutableStateOf("Apple")
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                text = "基本用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Segmented(
                value = commonValue,
                onChange = { commonValue = it },
                options = listOf("Apple", "Banana", "Cherry")
            )
        }

        item {
            Text(
                text = "禁用用法",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Segmented(
                value = disabledValue,
                onChange = { disabledValue = it.value },
                options = listOf(
                    SegmentedModel("Apple", "Apple"),
                    SegmentedModel("Banana", "Banana", disabled = true),
                    SegmentedModel("Cherry", "Cherry")
                )
            )
        }

        item {
            Text(
                text = "设置图标",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Segmented(
                value = iconValue,
                onChange = { iconValue = it.value },
                options = listOf(
                    SegmentedModel(
                        "Apple",
                        "Apple",
                        icon = {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(14.dp)
                                    .padding(end = 2.dp),
                                tint = LocalContentColor.current.copy(alpha = if (iconValue == "Apple") ContentAlpha.high else ContentAlpha.disabled)
                            )
                        }),
                    SegmentedModel("Banana", "Banana", icon = {
                        Icon(
                            Icons.Default.Folder,
                            contentDescription = null,
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 2.dp),
                            tint = LocalContentColor.current.copy(alpha = if (iconValue == "Banana") ContentAlpha.high else ContentAlpha.disabled)
                        )
                    }),
                )
            )
        }

        item {
            Text(
                text = "填充父组件",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.high),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium)
            )
            Segmented(
                block = true,
                value = blockValue,
                onChange = { blockValue = it },
                options = listOf("Apple", "Banana", "Cherry")
            )
        }
    }
}