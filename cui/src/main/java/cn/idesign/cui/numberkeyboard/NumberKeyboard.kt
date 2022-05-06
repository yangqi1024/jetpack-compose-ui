package cn.idesign.cui.numberkeyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import cn.idesign.cui.bottomsheet.BottomSheet
import cn.idesign.cui.bottomsheet.BottomSheetState
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import com.google.accompanist.flowlayout.FlowRow

/**
 * 类似微信数字键盘
 */
@Composable
fun NumberKeyBoard(
    modifier: Modifier = Modifier,
    confirmModifier: Modifier = Modifier,
    state: BottomSheetState = rememberBottomSheetState(),
    horizontalSpacing: Dp = 6.dp,
    verticalSpacing: Dp = 6.dp,
    itemHeight: Dp = 48.dp,
    confirmText: String = "确定",
    confirmDisable: Boolean = false,
    random: Boolean = false,
    onInput: ((value: String) -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
    onConfirm: (() -> Unit)? = null,
) {
    BottomSheet(
        state = state,
        content = {
            BoxWithConstraints(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(bottom = 22.dp)
                    .then(modifier)
            ) {
                val itemWidthDp = (maxWidth - horizontalSpacing * 5) / 4
                val keys = if (random) List(9) { it + 1 }.shuffled() else List(9) { it + 1 }
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = verticalSpacing,
                            start = horizontalSpacing,
                            end = horizontalSpacing
                        )
                ) {
                    val (refFlow, ref0, refDelete, refDot, refDone) = createRefs()

                    FlowRow(
                        mainAxisSpacing = horizontalSpacing,
                        crossAxisSpacing = verticalSpacing,
                        modifier = Modifier
                            .constrainAs(refFlow) {
                                start.linkTo(parent.start)
                                end.linkTo(refDelete.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(ref0.top, margin = verticalSpacing)
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        repeat(keys.size) {
                            val value = (keys[it]).toString()
                            Box(
                                modifier = Modifier
                                    .size(itemWidthDp, itemHeight)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        MaterialTheme.colors.surface,
                                    )
                                    .clickable {
                                        onInput?.invoke(value)
                                    }, contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.h6
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .constrainAs(refDelete) {
                                start.linkTo(refFlow.end)
                                end.linkTo(parent.end)
                                top.linkTo(refFlow.top)
                                bottom.linkTo(refDone.top, margin = verticalSpacing)
                            }
                            .size(itemWidthDp, itemHeight)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                MaterialTheme.colors.surface
                            )
                            .clickable {
                                onDelete?.invoke()
                            }, contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Outlined.Backspace, contentDescription = null)
                    }
                    Box(
                        modifier = Modifier
                            .constrainAs(refDone) {
                                start.linkTo(refFlow.end)
                                end.linkTo(parent.end)
                                top.linkTo(refDelete.bottom)
                                bottom.linkTo(parent.bottom)
                                height = Dimension.fillToConstraints
                            }
                            .width(itemWidthDp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                MaterialTheme.colors.primary
                            )
                            .alpha(if (confirmDisable) ContentAlpha.disabled else 1.0f)
                            .clickable(
                                enabled = !confirmDisable,
                                onClick = {
                                    onConfirm?.invoke()
                                }
                            )
                            .then(confirmModifier), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = confirmText,
                            style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.onPrimary)
                        )
                    }


                    Box(
                        modifier = Modifier
                            .constrainAs(ref0) {
                                start.linkTo(parent.start)
                                end.linkTo(refDot.start, margin = horizontalSpacing)
                                top.linkTo(refFlow.bottom)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .height(itemHeight)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                MaterialTheme.colors.surface
                            )
                            .clickable {
                                onInput?.invoke("0")
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(text = "0", style = MaterialTheme.typography.h6)
                    }
                    Box(
                        modifier = Modifier
                            .constrainAs(refDot) {
                                start.linkTo(ref0.end)
                                end.linkTo(refDone.start, margin = horizontalSpacing)
                                top.linkTo(ref0.top)
                                bottom.linkTo(ref0.bottom)
                            }
                            .size(itemWidthDp, itemHeight)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                MaterialTheme.colors.surface
                            )
                            .clickable {
                                onInput?.invoke(".")
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(text = ".", style = MaterialTheme.typography.h6)
                    }
                }
            }
        }
    )
}