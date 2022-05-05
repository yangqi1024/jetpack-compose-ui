package cn.idesign.cui.skeleton

interface SkeletonScope {
    fun skeletonItem(
        variant: SkeletonType = SkeletonType.Image,
        animated: Boolean = false,
    ) {

    }

}

sealed class SkeletonType {
    object Image : SkeletonType()
    object Text : SkeletonType()
}

