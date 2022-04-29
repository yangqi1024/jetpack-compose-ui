package cn.idesign.cui.curtain

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import cn.idesign.cui.banner.Banner
import cn.idesign.cui.banner.floorMod
import cn.idesign.cui.banner.rememberBannerState
import cn.idesign.cui.image.NetworkImage
import cn.idesign.cui.indicator.Indicator
import cn.idesign.cui.indicator.IndicatorMode
import cn.idesign.cui.modal.Modal
import cn.idesign.cui.modal.ModalState
import cn.idesign.cui.modal.rememberModalState

@Composable
fun Curtain(
    state: ModalState = rememberModalState(),
    url: String,
    size: DpSize = DpSize(260.dp, 350.dp),
    closeAlignment: CurtainAlignment = CurtainAlignment.BottomCenter,
    onClick: (() -> Unit)? = null,
) {
    Curtain(
        state = state,
        size = size,
        data = listOf(CurtainModel(url)),
        closeAlignment = closeAlignment,
        onClick = {
            onClick?.let { it() }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Curtain(
    state: ModalState = rememberModalState(),
    size: DpSize = DpSize(250.dp, 350.dp),
    data: List<out CurtainModel>,
    closeAlignment: CurtainAlignment = CurtainAlignment.BottomCenter,
    onClick: ((value: CurtainModel) -> Unit)? = null,
) {

    val widthDp = size.width
    val heightDp = size.height
    val bannerState = rememberBannerState()
    val clickState = rememberUpdatedState(onClick)
    Curtain(state = state) {
        ConstraintLayout(Modifier.width(widthDp)) {
            val (banner, indicator, close) = createRefs()
            Banner(
                count = data.size,
                modifier = Modifier
                    .constrainAs(banner) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                state = bannerState
            ) { page ->
                NetworkImage(
                    data = data[page].url,
                    modifier = Modifier
                        .size(
                            width = widthDp,
                            height = heightDp
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            enabled = clickState.value != null,
                            onClick = {
                                clickState.value?.invoke(data[page])
                            }
                        )
                        .clip(RoundedCornerShape(4.dp)),

                    contentScale = ContentScale.FillBounds
                )
            }
            if (data.size > 1) {
                Indicator(
                    modifier = Modifier
                        .constrainAs(indicator) {
                            Log.d("Alignment", "banner:${banner}")
                            start.linkTo(banner.start)
                            end.linkTo(banner.end)
                            bottom.linkTo(banner.bottom, margin = 10.dp)
                        },
                    pageCount = data.size,
                    currentPage = (bannerState.currentPage - bannerState.initialPage).floorMod(
                        data.size
                    ),
                    indicatorProgress = bannerState.currentPageOffset,
                    mode = IndicatorMode.Smooth,
                    activeColor = Color.White
                )
            }
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                IconButton(
                    onClick = {
                        state.hide()
                    },
                    modifier = Modifier
                        .constrainAs(
                            close,
                            constrainBlock = CurtainAlignment.constrainBlock(banner, closeAlignment)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun Curtain(state: ModalState = rememberModalState(), content: @Composable () -> Unit) {
    Modal(state = state, onClose = { state.hide() }) {
        content()
    }
}

sealed class CurtainAlignment {
    object TopStart : CurtainAlignment()
    object TopCenter : CurtainAlignment()
    object TopEnd : CurtainAlignment()
    object BottomStart : CurtainAlignment()
    object BottomCenter : CurtainAlignment()
    object BottomEnd : CurtainAlignment()
    companion object {

        fun constrainBlock(
            ref: ConstrainedLayoutReference,
            alignment: CurtainAlignment
        ): ConstrainScope.() -> Unit = {
            when (alignment) {
                BottomEnd -> {
                    top.linkTo(ref.bottom, margin = 10.dp)
                    end.linkTo(ref.end)
                }
                BottomStart -> {
                    top.linkTo(ref.bottom, margin = 10.dp)
                    start.linkTo(ref.start)
                }
                TopCenter -> {
                    bottom.linkTo(ref.top, margin = 10.dp)
                    start.linkTo(ref.start)
                    end.linkTo(ref.end)
                }
                TopEnd -> {
                    bottom.linkTo(ref.top, margin = 10.dp)
                    end.linkTo(ref.end)
                }
                TopStart -> {
                    bottom.linkTo(ref.top, margin = 10.dp)
                    start.linkTo(ref.start)
                }
                else -> {
                    top.linkTo(ref.bottom, margin = 10.dp)
                    start.linkTo(ref.start)
                    end.linkTo(ref.end)
                }
            }
        }
    }
}