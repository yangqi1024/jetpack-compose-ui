package cn.idesign.cui.testclient.skeleton

import androidx.compose.runtime.Composable
import cn.idesign.cui.testclient.BaseActivity

class SkeletonActivity : BaseActivity() {

    @Composable
    override fun Render() {
        SkeletonTest()
    }

    override fun title(): String = "Skeleton示例"
}