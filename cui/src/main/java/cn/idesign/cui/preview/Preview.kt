package cn.idesign.cui.preview

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import cn.idesign.cui.banner.Banner
import cn.idesign.cui.banner.rememberBannerState
import cn.idesign.cui.image.NetworkImage
import cn.idesign.cui.modal.Modal
import cn.idesign.cui.modal.ModalState
import cn.idesign.cui.modal.rememberModalState
import cn.idesign.cui.preview.PreviewType.Image
import cn.idesign.cui.preview.PreviewType.Video
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.coroutines.launch

@Composable
fun Preview(
    modifier: Modifier = Modifier,
    data: List<PreviewItem>,
    initialPage: Int = 0,
    onClose: (() -> Unit)? = null,
    state: ModalState = rememberModalState()
) {
    val bannerState = rememberBannerState(initialPage)
    val scope = rememberCoroutineScope()
    Log.d("Modal", "Preview state:${state.currentValue} ")
    Modal(state = state, onClose = {
        scope.launch {
            if (onClose != null) {
                onClose()
            } else {
                state.hide()
            }

        }
    }) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .then(modifier)
        ) {
            val (tip, close, banner) = createRefs()
            Banner(
                count = data.size,
                modifier = Modifier.constrainAs(banner) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                state = bannerState,
            ) { page ->
                val item = data[page]
                when (item.type) {
                    Image -> {
                        NetworkImage(
                            data = data[page].url,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillWidth,
                            placeHolderDrawable = null,
                        )
                    }
                    Video -> {
                        //TODO 填充视频
                    }
                }

            }

            Text(
                text = "${bannerState.currentPage + 1}/${data.size}",
                color = Color.White,
                modifier = Modifier.constrainAs(tip) {
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            IconButton(onClick = {
                scope.launch {
                    if (onClose != null) {
                        onClose()
                    } else {
                        state.hide()
                    }

                }
            }, modifier = Modifier.constrainAs(close) {
                top.linkTo(parent.top, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
            }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                )
            }
        }
    }
}

@Parcelize
class PreviewItem(
    val url: String,
    val type: @RawValue PreviewType = Image
) : Parcelable

@Parcelize
enum class PreviewType : Parcelable {
    Image, Video
}

//sealed class PreviewType {
//    object Image : PreviewType()
//    object Video : PreviewType()
//}