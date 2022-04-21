package cn.idesign.cui.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    iconPainter: Painter? = null,
    iconModifier: Modifier = Modifier,
    text: String,
    secondaryText: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(
        color = MaterialTheme.colors.onSurface
    ),
    secondaryTextStyle: TextStyle = MaterialTheme.typography.body2.copy(
        color = MaterialTheme.colors.onSurface.copy(
            ContentAlpha.disabled
        )
    ),
    rightText: String? = null,
    rightTextStyle: TextStyle = MaterialTheme.typography.body2.copy(
        color = MaterialTheme.colors.onSurface.copy(
            ContentAlpha.disabled
        )
    ),
    showRight: Boolean = true,
    rightComponent: (@Composable () -> Unit)? = null,
    showDivider: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Cell(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colors.surface)
            .clickable(
                enabled = onClick != null,
                onClick = {
                    onClick?.invoke()
                })
            .padding(horizontal = 10.dp)
            .then(modifier),
        text = { Text(text = text, style = textStyle) },
        icon = {
            iconPainter?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                        .then(iconModifier)
                )
            }
        },
        secondaryText = {
            secondaryText?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = secondaryText,
                    style = secondaryTextStyle
                )
            }
        },
        rightComponent = {
            if (showRight) {
                if (rightComponent == null) {
                    Icon(
                        Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        tint = MaterialTheme.colors.onSurface.copy(ContentAlpha.high)
                    )
                } else {
                    rightComponent()
                }
            }
        },
        rightExtra = {
            rightText?.let {
                Text(
                    text = rightText,
                    style = rightTextStyle
                )
            }
        },
        showDivider = showDivider
    )
}

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    text: @Composable () -> Unit,
    secondaryText: (@Composable () -> Unit)? = null,
    rightComponent: (@Composable () -> Unit)? = null,
    rightExtra: (@Composable () -> Unit)? = null,
    showDivider: Boolean = false,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        Box(
            Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                    text()
                    secondaryText?.invoke()
                }
                rightExtra?.invoke()
                rightComponent?.invoke()
            }
            if (showDivider) {
                Divider(modifier = Modifier.align(Alignment.BottomStart))
            }
        }

    }


//    ConstraintLayout(
//        Modifier
//            .fillMaxWidth()
//            .then(modifier)
//    ) {
//        val (iconRef, textRef, secondaryTextRef, rightIconRef, rightExtraRef,dividerRef) = createRefs()
//        val startBarrier = createStartBarrier(rightExtraRef, rightExtraRef)
//        Box(modifier = Modifier
//            .constrainAs(iconRef) {
//                start.linkTo(parent.start)
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                end.linkTo(textRef.start)
//                width = Dimension.wrapContent
//                visibility = if (icon == null) Visibility.Gone else Visibility.Visible
//            }
//        ) {
//            icon?.invoke()
//        }
//        Box(
//            modifier = Modifier
//                .constrainAs(textRef) {
//                    start.linkTo(iconRef.end, margin = 12.dp, goneMargin = 0.dp)
//                    end.linkTo(startBarrier)
//                    top.linkTo(parent.top)
//                    bottom.linkTo(secondaryTextRef.top, margin = 4.dp, goneMargin = 0.dp)
//                    width = Dimension.fillToConstraints
//                }
//        ) {
//            text()
//        }
//        Box(modifier = Modifier.constrainAs(secondaryTextRef) {
//            start.linkTo(textRef.start)
//            end.linkTo(textRef.end)
//            top.linkTo(textRef.bottom)
//            bottom.linkTo(parent.bottom)
//            width = Dimension.fillToConstraints
//            visibility = if (secondaryText == null) Visibility.Gone else Visibility.Visible
//        }) {
//            secondaryText?.invoke()
//        }
//
//        Box(modifier = Modifier.constrainAs(rightIconRef) {
//            end.linkTo(parent.end)
//            top.linkTo(parent.top)
//            bottom.linkTo(parent.bottom)
//            visibility = if (rightComponent == null) Visibility.Gone else Visibility.Visible
//        }) {
//            rightComponent?.invoke()
//        }
//
//        Box(modifier = Modifier.constrainAs(rightExtraRef) {
//            end.linkTo(rightIconRef.start, margin = 4.dp, goneMargin = 0.dp)
//            top.linkTo(parent.top)
//            bottom.linkTo(parent.bottom)
//            visibility = if (rightExtra == null) Visibility.Gone else Visibility.Visible
//        }) {
//            rightExtra?.invoke()
//        }
//
//        Box(modifier = Modifier.constrainAs(dividerRef) {
//            start.linkTo(textRef.start)
//            end.linkTo(parent.end)
//            bottom.linkTo(parent.bottom)
//            width = Dimension.fillToConstraints
//            height = Dimension.wrapContent
//            visibility = if (showDivider) Visibility.Visible else Visibility.Gone
//        }) {
//            Divider()
//        }
//    }
}