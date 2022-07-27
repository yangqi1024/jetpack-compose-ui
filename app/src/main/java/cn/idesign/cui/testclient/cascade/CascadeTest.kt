package cn.idesign.cui.testclient.cascade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cn.idesign.cui.bottomsheet.rememberBottomSheetState
import cn.idesign.cui.cascade.Cascade
import cn.idesign.cui.cascade.CascadeModel
import cn.idesign.cui.cell.Cell
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

@Composable
fun CascadeTest() {
    val json =
        "[{\"label\":\"浙江\",\"value\":\"浙江\",\"children\":[{\"label\":\"杭州\",\"value\":\"杭州\",\"children\":[{\"label\":\"西湖区\",\"value\":\"西湖区\"},{\"label\":\"上城区\",\"value\":\"上城区\"},{\"label\":\"余杭区\",\"value\":\"余杭区\",\"disabled\":true}]},{\"label\":\"温州\",\"value\":\"温州\",\"children\":[{\"label\":\"鹿城区\",\"value\":\"鹿城区\"},{\"label\":\"龙湾区\",\"value\":\"龙湾区\",\"disabled\":true},{\"label\":\"瓯海区\",\"value\":\"瓯海区\"}]},{\"label\":\"宁波\",\"value\":\"宁波\",\"children\":[{\"label\":\"海曙区\",\"value\":\"海曙区\"},{\"label\":\"江北区\",\"value\":\"江北区\"},{\"label\":\"镇海区\",\"value\":\"镇海区\"}]}]},{\"label\":\"安徽\",\"value\":\"安徽\",\"children\":[{\"label\":\"合肥\",\"value\":\"合肥\",\"children\":[{\"label\":\"包河区\",\"value\":\"包河区\"},{\"label\":\"蜀山区\",\"value\":\"蜀山区\"},{\"label\":\"瑶海区\",\"value\":\"瑶海区\"}]},{\"label\":\"芜湖\",\"value\":\"芜湖\",\"children\":[{\"label\":\"镜湖区\",\"value\":\"镜湖区\"},{\"label\":\"弋江区\",\"value\":\"弋江区\"},{\"label\":\"湾沚区\",\"value\":\"湾沚区\"}]}]},{\"label\":\"江苏\",\"value\":\"江苏\",\"children\":[{\"label\":\"南京\",\"value\":\"南京\",\"children\":[{\"label\":\"玄武区\",\"value\":\"玄武区\"},{\"label\":\"秦淮区\",\"value\":\"秦淮区\"},{\"label\":\"建邺区\",\"value\":\"建邺区\"}]},{\"label\":\"苏州\",\"value\":\"苏州\",\"children\":[{\"label\":\"虎丘区\",\"value\":\"虎丘区\"},{\"label\":\"吴中区\",\"value\":\"吴中区\"},{\"label\":\"相城区\",\"value\":\"相城区\"}]}]}]"
    val options =
        Gson().fromJson<List<CascadeModel>>(json, object : TypeToken<List<CascadeModel>>() {}.type)
    val context = LocalContext.current
    val state = rememberBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectResult by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Cell(
            text = "基本用法", showDivider = true, onClick = {
                scope.launch {
                    state.show()
                }
            }
        )
        Text(text = selectResult)
    }
    Cascade(state, options = options, onConfirm = {
        for (item in it) {
            println("model:${item.value}")
        }
        selectResult = it.map { it.value }.joinToString(separator = "-")
    })
}