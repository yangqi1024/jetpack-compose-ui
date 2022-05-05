package cn.idesign.cui.skeleton

class SkeletonScopeImpl : SkeletonScope {
    private val _intervals = mutableListOf<SkeletonIntervalContent>()
    val intervals: List<SkeletonIntervalContent> = _intervals
    override fun skeletonItem(variant: SkeletonType, animated: Boolean) {
        _intervals.add(
            SkeletonIntervalContent(
                variant = variant,
                animated = animated,
            )
        )
    }
}

class SkeletonIntervalContent(
    val variant: SkeletonType,
    val animated: Boolean,
)