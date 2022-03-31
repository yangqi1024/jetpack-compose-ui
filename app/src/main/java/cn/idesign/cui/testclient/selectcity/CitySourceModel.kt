package cn.idesign.cui.testclient.selectcity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CitySourceModel(
    val name: String,
    val id: String? = null,
    val cityList: List<CitySourceModel>? = null,
)