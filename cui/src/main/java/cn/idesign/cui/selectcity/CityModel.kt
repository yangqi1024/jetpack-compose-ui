package cn.idesign.cui.selectcity

class CityModel(
    val city: String
) {
    lateinit var tagIndex: String
    lateinit var pinyin: String
    var isShowSuspension: Boolean = false
    var isNeedToPinyin: Boolean = true
    override fun toString(): String {
        return "CityModel>>> city:${city},tag:${tagIndex}"
    }
}