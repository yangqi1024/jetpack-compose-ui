package cn.idesign.cui.timeselect

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.idesign.cui.bottomsheet.BottomSheet
import cn.idesign.cui.bottomsheet.BottomSheetState
import cn.idesign.cui.bottomsheet.rememberBottomSheetState

/**
 * 类似于京东选择配送时间组件
 */
@Composable
fun TimeSelect1(
    state: BottomSheetState = rememberBottomSheetState(),
    timeValueActiveModifier: Modifier = Modifier,
    timeValueNormalModifier: Modifier = Modifier

) {
    BottomSheet(
        state = state,
        content = {
            Column(
                Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "取件时间")
                    Icon(
                        Icons.Default.Close, contentDescription = null
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Column(
                        Modifier
                            .width(140.dp)
                            .background(MaterialTheme.colors.background),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "2月23日(今天)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.surface)
                                .padding(15.dp),
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Text(
                            text = "2月24日(星期三)", modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSurface.copy(
                                    ContentAlpha.medium
                                )
                            )
                        )
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .background(MaterialTheme.colors.surface)
                            .padding(10.dp, 0.dp, 10.dp, 50.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "9:00-10:00",
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colors.primary.copy(0.12f),
                                )
                                .border(
                                    1.dp,
                                    MaterialTheme.colors.primary,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(15.dp),
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = "10:00-11:00",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.surface)
                                .border(
                                    1.dp,
                                    MaterialTheme.colors.onSurface.copy(0.12f),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(15.dp), style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = "11:00-12:00",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.surface)
                                .border(
                                    1.dp,
                                    MaterialTheme.colors.onSurface.copy(0.12f),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(15.dp), style = MaterialTheme.typography.body2
                        )
                    }
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(text = "确定")
                }
            }
        }
    )
}