package cn.idesign.cui

import cn.idesign.cui.theme.ComposeUiTheme

class ComposeUiInjection private constructor() {
    lateinit var theme:ComposeUiTheme

    companion object {
        val instance = ComposeUiInjectionHolder.holder

        @JvmStatic
        fun init(){
            init(ComposeUiTheme.DEFAULT)
        }
        @JvmStatic
        fun init(composeUiTheme: ComposeUiTheme){
            instance.theme = composeUiTheme
        }
    }

    private object ComposeUiInjectionHolder {
        val holder= ComposeUiInjection()
    }

}