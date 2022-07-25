package cn.idesign.cui.category

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun rememberCategoryState(
    @IntRange(from = 0) initialPosition: Int = 0,
): CategoryState = rememberSaveable(saver = CategoryState.Saver) {
    CategoryState(
        initialPosition = initialPosition,
    )
}

class CategoryState(
    @IntRange(from = 0) var initialPosition: Int = 0,
) {

    //    @OptIn(ExperimentalPagerApi::class)
//    internal lateinit var pageState: PagerState
//
    private var _currentPosition: Int by mutableStateOf(initialPosition)

    //
//    @OptIn(ExperimentalPagerApi::class)
//    val realPageCount: Int
//        get() = pageState.pageCount
//
    var currentPosition: Int
        get() = _currentPosition
        set(value) {
            if (value != _currentPosition) {
                _currentPosition = value
            }
        }
//
//    @OptIn(ExperimentalPagerApi::class)
//    val currentPageOffset: Float
//        get() = pageState.currentPageOffset
//
//    @get:IntRange(from = 0)
//    var showPageCount: Int
//        get() = _pageCount
//        internal set(value) {
//            if (value != _pageCount) {
//                _pageCount = value
//            }
//        }
//
//    @OptIn(ExperimentalPagerApi::class)
//    suspend fun scrollToPage(
//        @IntRange(from = 0) page: Int,
//        @FloatRange(from = 0.0, to = 1.0) pageOffset: Float = 0f,
//    ) {
//        pageState.scrollToPage(page, pageOffset)
//    }
//
//    @OptIn(ExperimentalPagerApi::class)
//    suspend fun animateScrollToPage(
//        @IntRange(from = 0) page: Int,
//        @FloatRange(from = 0.0, to = 1.0) pageOffset: Float = 0f,
//    ) {
//        pageState.animateScrollToPage(page, pageOffset)
//    }

    companion object {
        val Saver: Saver<CategoryState, *> = Saver(
            save = {
                it.initialPosition
            },
            restore = {
                CategoryState(
                    initialPosition = it,
                )
            }
        )
    }
}

