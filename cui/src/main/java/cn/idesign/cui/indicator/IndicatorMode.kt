package cn.idesign.cui.indicator

interface IndicatorMode {
    companion object {
        const val NORMAL = 0
        const val SMOOTH = NORMAL shl 1
        const val WORM = NORMAL shl 2
        const val SCALE = NORMAL shl 3
        const val COLOR = NORMAL shl 4
    }
}