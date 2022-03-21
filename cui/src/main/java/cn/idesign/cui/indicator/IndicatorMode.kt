package cn.idesign.cui.indicator

sealed class IndicatorMode {
    object Normal : IndicatorMode()
    object Smooth : IndicatorMode()
    object Worm : IndicatorMode()
    object Scale : IndicatorMode()
    object Color : IndicatorMode()
}