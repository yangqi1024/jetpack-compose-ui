package cn.idesign.cui.testclient.banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi

/**
 * Simple pager item which displays an image
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun BannerSampleItem(
    page: Int,
    data: BannerItem,
    modifier: Modifier = Modifier,
) {
    Card( modifier) {
            Box {
                Image(
                    painter = painterResource(id = data.id),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = page.toString(),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .background(MaterialTheme.colors.surface, RoundedCornerShape(4.dp))
                        .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                        .padding(8.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }

    }
}
