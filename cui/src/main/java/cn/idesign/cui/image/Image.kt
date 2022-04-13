package cn.idesign.cui.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    data: Any,
    imageModifier: (RequestBuilder<Drawable>) -> RequestBuilder<Drawable> = {
        it
    },
    placeHolderDrawable: Drawable? = getDrawableShape(),
    errorDrawable: Drawable? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    BoxWithConstraints(modifier = modifier) {
        val state = remember {
            mutableStateOf<ImageBitmap?>(null)
        }
        val context = LocalContext.current

        DisposableEffect(
            data,
            modifier,
            imageModifier,
        ) {
            val glide = Glide.with(context)
            var builder = when (data) {
                is Int -> {
                    glide.load(data)
                }
                is Uri -> {
                    glide.load(data)
                }
                is File -> {
                    glide.load(data)
                }
                is Drawable -> {
                    glide.load(data)
                }
                is ByteArray -> {
                    glide.load(data)
                }
                is Bitmap -> {
                    glide.load(data)
                }
                is String -> {
                    glide.load(data)
                }
                else -> {
                    glide.load(data)
                }
            }
            builder = builder.placeholder(placeHolderDrawable).error(errorDrawable)

            builder = imageModifier(builder)
            val request = builder.into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    Log.d("NetworkImage", "onResourceReady")
                    state.value = resource.toBitmap().asImageBitmap()
                }

                override fun onLoadStarted(placeholder: Drawable?) {
                    Log.d("NetworkImage", "${constraints.maxWidth},${constraints.maxHeight}")
                    if (placeholder != null) {
                        state.value = placeholder.toBitmap(
                            width = constraints.maxWidth,
                            height = constraints.maxHeight
                        ).asImageBitmap()
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    if (placeholder != null) {
                        state.value = placeholder.toBitmap(
                            width = constraints.maxWidth,
                            height = constraints.maxHeight
                        ).asImageBitmap()
                    }
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    Log.d("NetworkImage", "onLoadFailed ${errorDrawable} ")
                    if (errorDrawable != null) {
                        state.value = errorDrawable.toBitmap(
                            width = constraints.maxWidth,
                            height = constraints.maxHeight
                        ).asImageBitmap()
                    }
                }
            }).request!!
            onDispose {
                Log.d("NetworkImage", "onDispose")
                request.clear()
            }
        }
        val currentBitmap = state.value
        if (currentBitmap != null) {
            Image(
                modifier = modifier,
                contentDescription = contentDescription,
                painter = BitmapPainter(currentBitmap),
                contentScale = contentScale,
            )
        }
    }
}

@Composable
fun getDrawableShape(): Drawable {
    val color = MaterialTheme.colors.onSurface
    return remember {
        val roundedCorners = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        ShapeDrawable(RoundRectShape(roundedCorners, null, null)).apply {
            paint.color = color.copy(alpha = 0.12f).toArgb()
        }
    }
}