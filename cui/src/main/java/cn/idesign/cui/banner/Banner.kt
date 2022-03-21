package cn.idesign.cui.banner

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.idesign.cui.banner.BannerDirection.Horizontal
import cn.idesign.cui.banner.BannerDirection.Vertical
import com.google.accompanist.pager.*
import kotlin.math.abs

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    count: Int,
    loop: Boolean = false,
    direction: BannerDirection = Horizontal,
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp),
    itemSpacing: Dp = 0.dp,
    reverseLayout: Boolean = false,
    state: BannerState = rememberBannerState(),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    modifier: Modifier = Modifier,
    content: @Composable() (BannerScope.(page: Int) -> Unit),
) {

    val realCount = if (loop) Int.MAX_VALUE else count
    val startIndex = if (loop) Int.MAX_VALUE / 2 else state.initialPage

    val pagerState = rememberPagerState(initialPage = startIndex)

    val bannerScope = remember(state, startIndex, count) {
        state.pageState = pagerState
        state.showPageCount = count
        state.initialPage = startIndex
        BannerScopeImpl(state)
    }

    LaunchedEffect(loop) {
        pagerState.scrollToPage(startIndex)
    }
    when (direction) {
        is Horizontal -> {
            HorizontalPager(
                count = realCount,
                state = pagerState,
                contentPadding = contentPadding,
                itemSpacing = itemSpacing,
                reverseLayout = reverseLayout,
                modifier = modifier,
                verticalAlignment = verticalAlignment
            ) { index ->
                val page = if (loop) (index - startIndex).floorMod(count) else index
                bannerScope.content(page)
            }
        }

        is Vertical -> {
            VerticalPager(
                count = realCount,
                state = pagerState,
                contentPadding = contentPadding,
                itemSpacing = itemSpacing,
                reverseLayout = reverseLayout,
                modifier = modifier,
                horizontalAlignment = horizontalAlignment
            ) { index ->
                val page = if (loop) (index - startIndex).floorMod(count) else index
                bannerScope.content(page)
            }
        }
    }

}

sealed class BannerDirection {
    object Vertical : BannerDirection()
    object Horizontal : BannerDirection()
}

@Stable
interface BannerScope {
    val currentPage: Int
    val currentPageOffset: Float
    val initialPage: Int
    val showPageCount: Int
}

@ExperimentalPagerApi
private class BannerScopeImpl(
    private val state: BannerState,
) : BannerScope {
    override val currentPage: Int get() = state.currentPage
    override val currentPageOffset: Float get() = state.currentPageOffset
    override val initialPage: Int
        get() = state.initialPage
    override val showPageCount: Int
        get() = state.showPageCount
}

@Composable
fun rememberBannerState(
    @IntRange(from = 0) initialPage: Int = 0,
): BannerState = rememberSaveable(saver = BannerState.Saver) {
    BannerState(
        initialPage = initialPage,
    )
}

class BannerState(
    @IntRange(from = 0) var initialPage: Int = 0,
) {

    @OptIn(ExperimentalPagerApi::class)
    internal lateinit var pageState: PagerState

    private var _pageCount: Int by mutableStateOf(0)

    @OptIn(ExperimentalPagerApi::class)
    val realPageCount: Int
        get() = pageState.pageCount

    @OptIn(ExperimentalPagerApi::class)
    val currentPage: Int
        get() = pageState.currentPage

    @OptIn(ExperimentalPagerApi::class)
    val currentPageOffset: Float
        get() = pageState.currentPageOffset

    @get:IntRange(from = 0)
    var showPageCount: Int
        get() = _pageCount
        internal set(value) {
            if (value != _pageCount) {
                _pageCount = value
            }
        }

    @OptIn(ExperimentalPagerApi::class)
    suspend fun scrollToPage(
        @IntRange(from = 0) page: Int,
        @FloatRange(from = 0.0, to = 1.0) pageOffset: Float = 0f,
    ) {
        pageState.scrollToPage(page, pageOffset)
    }

    @OptIn(ExperimentalPagerApi::class)
    suspend fun animateScrollToPage(
        @IntRange(from = 0) page: Int,
        @FloatRange(from = 0.0, to = 1.0) pageOffset: Float = 0f,
    ) {
        pageState.animateScrollToPage(page, pageOffset)
    }

    companion object {
        /**
         * The default [Saver] implementation for [PagerState].
         */
        val Saver: Saver<BannerState, *> = listSaver(
            save = {
                listOf<Any>(
                    it.initialPage,
                )
            },
            restore = {
                BannerState(
                    initialPage = it[0] as Int,
                )
            }
        )
    }
}

fun BannerScope.calculateCurrentOffsetForPage(page: Int): Float {
    return abs(currentPage - initialPage - page) % showPageCount + currentPageOffset
}

fun Int.floorMod(other: Int): Int = when (other) {

    0 -> this
    else -> this - floorDiv(other) * other
}

