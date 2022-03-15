package cn.idesign.cui.banner

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.idesign.cui.banner.BannerDirection.Horizontal
import cn.idesign.cui.banner.BannerDirection.Vertical
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    count: Int,
    direction: BannerDirection = Horizontal,
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp),
    itemSpacing: Dp = 0.dp,
    reverseLayout: Boolean = false,
    state: PagerState = rememberPagerState(),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    content: @Composable (page: Int) -> Unit,
) {

    when (direction) {
        is Horizontal -> {
            HorizontalPager(
                count = count,
                state = state,
                contentPadding = contentPadding,
                itemSpacing = itemSpacing,
                reverseLayout = reverseLayout,
                modifier = modifier,
                verticalAlignment = verticalAlignment
            ) { page ->
                content(page)
            }
        }

        is Vertical -> {
            VerticalPager(
                count = count,
                state = state,
                contentPadding = contentPadding,
                itemSpacing = itemSpacing,
                reverseLayout = reverseLayout,
                modifier = modifier,
                horizontalAlignment = horizontalAlignment
            ) { page ->
                content(page)
            }
        }
    }

}

sealed class BannerDirection {
    object Vertical : BannerDirection()
    object Horizontal : BannerDirection()
}