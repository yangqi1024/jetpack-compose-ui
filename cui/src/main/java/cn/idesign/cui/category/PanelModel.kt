package cn.idesign.cui.category

class PanelModel(
    val title: String? = null,
    val children: List<PanelGroup>,
)

class PanelGroup(
    val title:String,
    val children: List<PanelItemModel>,
)

class PanelItemModel(
    val title: String,
    val icon: String,

) {
    override fun toString(): String {
        return "PanelItemModel(title='$title', icon='$icon')"
    }
}