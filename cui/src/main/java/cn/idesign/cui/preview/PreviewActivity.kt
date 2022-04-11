package cn.idesign.cui.preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import cn.idesign.cui.modal.ModalValue
import cn.idesign.cui.modal.rememberModalState

class PreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        val list = extras?.getParcelableArrayList<PreviewItem>(KEY_DATA)
        var initialPage = extras?.getInt(KEY_INITIALPAGE)
        setContent {
            initialPage = if (initialPage == null) 0 else initialPage
            if (list != null) {
                val rememberModalState = rememberModalState(ModalValue.Expanded)
                Preview(
                    data = list,
                    initialPage = initialPage!!,
                    modifier = Modifier.fillMaxSize(),
                    state = rememberModalState,
                    onClose = {
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        val KEY_DATA = "PreviewActivity_key_data"
        val KEY_INITIALPAGE = "PreviewActivity_key_initialPage"
    }
}