package cn.idesign.cui.testclient

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.Collator
import java.util.*

class MainActivity : BaseActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Render() {
        val list = getData(intent.getStringExtra(EXTRA_PATH))
        Log.d("MainActivity", "list:${list}")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(list.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        onClick = {
                            val intent = list[index].get("intent") as Intent
                            startActivity(intent)
                        }
                    ) {
                        Text(
                            text = list[index].get("title").toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                        )
                    }
                }
            }
        )
    }

    override fun title(): String = "Compose UI Design"


    private fun getData(prefix: String?): List<Map<String, Any>> {
        val myData = ArrayList<Map<String, Any>>()

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory("cn.idesign.cui.testclient.SAMPLE_CODE")

        @SuppressLint("QueryPermissionsNeeded") // Only querying our own Activities
        val list = packageManager.queryIntentActivities(mainIntent, 0)

        val prefixPath: Array<String>?
        var prefixWithSlash = prefix

        if (prefix.isNullOrEmpty()) {
            prefixPath = null
        } else {
            prefixPath = prefix.split("/".toRegex()).toTypedArray()
            prefixWithSlash = "$prefix/"
        }

        val entries = HashMap<String, Boolean>()

        list.forEach { info ->
            val labelSeq = info.loadLabel(packageManager)
            val label = labelSeq?.toString() ?: info.activityInfo.name

            if (prefixWithSlash.isNullOrEmpty() || label.startsWith(prefixWithSlash)) {
                val labelPath = label.split("/".toRegex()).toTypedArray()
                val nextLabel = if (prefixPath == null) labelPath[0] else labelPath[prefixPath.size]
                if (prefixPath?.size ?: 0 == labelPath.size - 1) {
                    addItem(
                        data = myData,
                        name = nextLabel,
                        intent = activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name
                        )
                    )
                } else {
                    if (entries[nextLabel] == null) {
                        addItem(
                            data = myData,
                            name = nextLabel,
                            intent = browseIntent(
                                if (prefix == "") nextLabel else "$prefix/$nextLabel"
                            )
                        )
                        entries[nextLabel] = true
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator)

        return myData
    }

    private fun activityIntent(pkg: String, componentName: String): Intent {
        val result = Intent()
        result.setClassName(pkg, componentName)
        return result
    }

    private fun browseIntent(path: String): Intent {
        val result = Intent()
        result.setClass(this, MainActivity::class.java)
        result.putExtra(EXTRA_PATH, path)
        return result
    }

    private fun addItem(data: MutableList<Map<String, Any>>, name: String, intent: Intent) {
        val temp = mutableMapOf<String, Any>()
        temp["title"] = name
        temp["intent"] = intent
        data += temp
    }

    companion object {
        private const val EXTRA_PATH = "com.example.android.apis.Path"

        private val sDisplayNameComparator = object : Comparator<Map<String, Any>> {
            private val collator = Collator.getInstance()

            override fun compare(map1: Map<String, Any>, map2: Map<String, Any>): Int {
                return collator.compare(map1["title"], map2["title"])
            }
        }
    }
}

