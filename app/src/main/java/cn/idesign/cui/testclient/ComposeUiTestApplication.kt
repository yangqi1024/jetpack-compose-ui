package cn.idesign.cui.testclient

import android.app.Application
import cn.idesign.cui.ComposeUiInjection

class ComposeUiTestApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        ComposeUiInjection.init()
    }
}