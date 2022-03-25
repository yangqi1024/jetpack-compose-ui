package cn.idesign.cui.testclient.banner

import android.util.Log
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
import cn.idesign.cui.indicator.Indicator
import cn.idesign.cui.indicator.IndicatorMode
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun BannerTest() {
    val (contentPadding, onContentPaddingChange) = remember {
        mutableStateOf(0f)
    }
    val (itemSpacing, onItemSpacingChange) = remember {
        mutableStateOf(0f)
    }

    val (loop, onLoopChange) = remember {
        mutableStateOf(false)
    }

    Column(Modifier.fillMaxSize()) {
        val bannerState = rememberBannerState()
        val pageCount = 5
        Box(
            Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Banner(
                count = pageCount,
                state = bannerState,
                loop = loop,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.matchParentSize(),
                itemSpacing = itemSpacing.dp,
                contentPadding = PaddingValues(all = contentPadding.dp),
            ) { page ->
                BannerSampleItem(
                    page = page,
                    data = dataList[page],
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = calculateCurrentOffsetForPage(page).absoluteValue
                            Log.d("graphicsLayer", "offset:${offset}")
                            scaleInGraphics(offset)
                            alphaInGraphics(offset)
                        }
                        .fillMaxWidth()
                )
            }
        }

        Indicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            pageCount = pageCount,
            currentPage = (bannerState.currentPage - bannerState.initialPage).floorMod(pageCount),
            indicatorProgress = bannerState.currentPageOffset,
            mode = IndicatorMode.Smooth
        )

        Column {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "内容Padding:${Math.round(contentPadding)} dp",
                    modifier = Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Slider(
                    value = contentPadding,
                    onValueChange = onContentPaddingChange,
                    valueRange = 0f..100f
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "内容间距:${Math.round(itemSpacing)} dp",
                    modifier = Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Slider(
                    value = itemSpacing,
                    onValueChange = onItemSpacingChange,
                    valueRange = -50f..50f
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "是否循环", modifier = Modifier.width(150.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Switch(checked = loop, onCheckedChange = onLoopChange)
            }
        }

        ActionsRow(
            bannerState = bannerState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            infiniteLoop = loop,
        )
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
            enabled = infiniteLoop || bannerState.currentPage < bannerState.realPageCount - 1,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(bannerState.currentPage + 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateNext, null)
        }

        IconButton(
            enabled = infiniteLoop.not() && bannerState.currentPage < bannerState.realPageCount - 1,
            onClick = {
                scope.launch {
                    bannerState.animateScrollToPage(bannerState.realPageCount - 1)
                }
            }
        ) {
            Icon(Icons.Default.LastPage, null)
        }
    }
}