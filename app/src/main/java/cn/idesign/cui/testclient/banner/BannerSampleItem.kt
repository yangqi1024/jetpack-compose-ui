/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.idesign.cui.banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cn.idesign.cui.testclient.banner.BannerItem
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
    Box(modifier) {
        // Our page content, displaying a random image

            Image(
                painter = data.painter,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
            )
//        Image(
//            painter = rememberAsyncImagePainter(
//                 rememberRandomSampleImageUrl(width = 600),
//            ),
//            contentScale = ContentScale.FillBounds,
//            contentDescription = null,
//            modifier = Modifier
//                .height(300.dp)
//                .fillMaxWidth()
//        )

//        Box( modifier = Modifier
//            .height(300.dp)
//            .fillMaxWidth().background(color= Color.DarkGray))

        // Displays the page index
//        Text(
//            text = page.toString(),
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//                .background(MaterialTheme.colors.surface, RoundedCornerShape(4.dp))
//                .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
//                .padding(8.dp)
//                .wrapContentSize(Alignment.Center)
//        )
    }
}
