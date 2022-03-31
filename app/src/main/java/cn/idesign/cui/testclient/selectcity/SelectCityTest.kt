package cn.idesign.cui.testclient.selectcity

import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import cn.idesign.cui.selectcity.CityModel
import cn.idesign.cui.selectcity.SelectCity
import cn.idesign.cui.selectcity.sortCity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectCityTest() {
    var cityList by remember {
        mutableStateOf<List<CityModel>>(Collections.emptyList())
    }

    var hotCityList by remember {
        mutableStateOf<List<CityModel>>(Collections.emptyList())
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        scope.launch {
            val json = context.assets.open("china_city_data.json").bufferedReader().readText()
            val moshi: Moshi = Moshi.Builder().build()
            val type = Types.newParameterizedType(List::class.java, CitySourceModel::class.java)
            val adapter = moshi.adapter<List<CitySourceModel>>(type)
            val data = adapter.fromJson(json)
            data?.let {
                val mutableList = mutableListOf<CityModel>()
                it.forEach { citySourceModel ->
                    citySourceModel.cityList?.let { cityList ->
                        cityList.forEach { subItem ->
                            run {
                                mutableList.add(CityModel(subItem.name))
                            }
                        }

                    }
                }
                sortCity(mutableList)
                cityList = mutableList
                hotCityList = mutableList.filter { model ->
                    when (model.city) {
                        "北京市" -> true
                        "广州市" -> true
                        "成都市" -> true
                        "深圳市" -> true
                        "杭州市" -> true
                        "西安市" -> true
                        else -> false
                    }
                }
            }
        }
    }
    SelectCity(cityList = cityList, hotCityList = hotCityList, onSelect = {
        Toast.makeText(context, "选择了：${it.city}", Toast.LENGTH_SHORT).show()
    })
}