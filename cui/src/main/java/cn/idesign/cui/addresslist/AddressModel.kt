package cn.idesign.cui.addresslist

data class AddressModel(
    val area: String,
    val address: String,
    val receiver: String,
    val phoneNumber: String,
    val tag: String ?=null,
    val defaultAddress: Boolean = false,
    val extra: Map<String, Any>? = null
)
