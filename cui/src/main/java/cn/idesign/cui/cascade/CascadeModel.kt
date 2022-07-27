package cn.idesign.cui.cascade

class CascadeModel(
    var label:String,
    var value:String,
    internal var index:Int,
    val children:List<CascadeModel>?

) {
    override fun toString(): String {
        return "CascadeModel(label='$label', value='$value', children=$children)"
    }
}

