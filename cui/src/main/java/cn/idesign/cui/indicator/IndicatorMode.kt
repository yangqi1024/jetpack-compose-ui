package cn.idesign.cui.indicator

sealed class IndicatorMode {
    object NORMAL : IndicatorMode()
    object SMOOTH : IndicatorMode()
    object WORM : IndicatorMode()
    object SCALE : IndicatorMode()
    object COLOR : IndicatorMode()
}