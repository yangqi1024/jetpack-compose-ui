package cn.idesign.cui.testclient.indicator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.idesign.cui.banner.*
import cn.idesign.cui.indicator.Indicator
import cn.idesign.cui.indicator.IndicatorMode
import cn.idesign.cui.testclient.banner.BannerSampleItem
import cn.idesign.cui.testclient.banner.dataList
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun IndicatorTest() {


    val radioOptions = listOf("Circle", "Dash", "RoundRect")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val indicatorMap = mapOf(
        Pair("Circle", CircleShape),
        Pair("Dash", RectangleShape),
        Pair("RoundRect", RoundedCornerShape(10.dp)),
    )


    val modeOptions = listOf("Normal", "Scale", "Color", "Smooth","Worm")
    val (selectModeOption, onSelectModeOptionChange) = remember { mutableStateOf(modeOptions[4]) }
    val modeMap = mapOf(
        Pair("Normal", IndicatorMode.Normal),
        Pair("Scale", IndicatorMode.Scale),
        Pair("Color", IndicatorMode.Color),
        Pair("Smooth", IndicatorMode.Smooth),
        Pair("Worm", IndicatorMode.Worm),
    )

        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            val bannerState = rememberBannerState()
            val pageCount = 5
            Box(
                Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                Banner(
                    count = pageCount,
                    loop = true,
                    state = bannerState,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.matchParentSize(),
                    itemSpacing = (-10).dp,
                    contentPadding = PaddingValues(30.dp)
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
            }
            Indicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                pageCount = pageCount,
                currentPage = (bannerState.currentPage - bannerState.initialPage).floorMod(pageCount),
                indicatorProgress =  bannerState.currentPageOffset,
                indicatorShape = indicatorMap[selectedOption] as Shape,
                mode = modeMap[selectModeOption] as IndicatorMode,
            )


            Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(10.dp)) {
                Text(
                    text = "指示器类型",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                radioOptions.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                }
                            )
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body1.merge(),
                        )
                    }
                }
                Text(
                    text = "指示器模式",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                modeOptions.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectModeOption),
                                onClick = {
                                    onSelectModeOptionChange(text)
                                }
                            )
                    ) {
                        RadioButton(
                            selected = (text == selectModeOption),
                            onClick = { onSelectModeOptionChange(text) }
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body1.merge(),
                        )
                    }
                }

            }

            ActionsRow(
                bannerState = bannerState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
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