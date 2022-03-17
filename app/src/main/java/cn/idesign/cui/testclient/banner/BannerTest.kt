package cn.idesign.cui.testclient.banner

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import cn.idesign.cui.banner.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun BannerTest() {
    val contentPadding = remember {
        mutableStateOf(0f)
    }
    val itemSpacing = remember {
        mutableStateOf(0f)
    }

    val loop = remember {
        mutableStateOf(true)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Banner实例") },
                backgroundColor = MaterialTheme.colors.surface,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            val bannerState = rememberBannerState()
            Banner(
                count = 5,
                state = bannerState,
                loop = loop.value,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                itemSpacing = itemSpacing.value.dp,
                contentPadding = PaddingValues(all = contentPadding.value.dp),
            ) { page ->
                BannerSampleItem(
                    page = page,
                    data = dataList[page],
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = calculateCurrentOffsetForPage(page).absoluteValue
                            scaleInGraphics(offset)
                            alphaInGraphics(offset)
                        }
                        .fillMaxWidth()
                )
            }

            Column() {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "内容Padding:${Math.round(contentPadding.value)} dp",
                        modifier = Modifier.width(150.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Slider(
                        value = contentPadding.value,
                        onValueChange = { contentPadding.value = it },
                        valueRange = 0f..100f
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "内容间距:${Math.round(itemSpacing.value)} dp",
                        modifier = Modifier.width(150.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Slider(
                        value = itemSpacing.value,
                        onValueChange = { itemSpacing.value = it },
                        valueRange = -50f..50f
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "是否循环", modifier = Modifier.width(150.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Switch(checked = loop.value, onCheckedChange = { loop.value = it })
                }
            }

            ActionsRow(
                bannerState = bannerState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }


}


@Composable
internal fun ActionsRow(
    bannerState: BannerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(modifier) {
        val scope = rememberCoroutineScope()

        IconButton(
            enabled = infiniteLoop.not() && bannerState.currentPage > 0,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(0)
                }
            }
        ) {
            Icon(Icons.Default.FirstPage, null)
        }

        IconButton(
            enabled = infiniteLoop || bannerState.currentPage > 0,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(bannerState.currentPage - 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateBefore, null)
        }

        IconButton(
            enabled = infiniteLoop || bannerState.currentPage < bannerState.pageCount - 1,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(bannerState.currentPage + 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateNext, null)
        }

        IconButton(
            enabled = infiniteLoop.not() && bannerState.currentPage < bannerState.pageCount - 1,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(bannerState.pageCount - 1)
                }
            }
        ) {
            Icon(Icons.Default.LastPage, null)
        }
    }
}