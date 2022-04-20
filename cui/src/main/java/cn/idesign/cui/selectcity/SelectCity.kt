package cn.idesign.cui.selectcity

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.idesign.cui.testclient.searchbar.SearchBar
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalStdlibApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun SelectCity(
    modifier: Modifier = Modifier,
    cityList: List<CityModel>,
    hotCityList: List<CityModel> = Collections.emptyList(),
    onSelect: ((city: CityModel) -> Unit)? = null
) {
    val itemHeight = 50.dp
    val scope = rememberCoroutineScope()
    var searchValue by remember {
        mutableStateOf("")
    }

    val searchList by remember(searchValue) {
        derivedStateOf { cityList.filter { it.city.contains(searchValue) } }
    }
    Column(modifier=Modifier.background(MaterialTheme.colors.surface)) {
        Box( modifier = Modifier.padding(10.dp)) {
            SearchBar(value = searchValue,onActionClick = {
                searchValue = it
            }) { searchValue = it }
        }
        if (searchValue.isBlank()) {
            CityList(cityList, hotCityList, scope, itemHeight, onSelect)
        } else {
            SearchList(searchList, itemHeight, onSelect)
        }
    }

}

@Composable
fun SearchList(
    searchList: List<CityModel>, itemHeight: Dp,
    onSelect: ((city: CityModel) -> Unit)? = null
) {
    LazyColumn {
        if (searchList.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight)
                        .padding(start = 15.dp)
                ) {
                    Divider()
                    Text(
                        text = "未找到相关信息，请修改后重试",
                        modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically)
                            .weight(1f),
                    )
                }
            }
        }
        items(searchList) { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
                    .padding(start = 15.dp)
                    .clickable {
                        onSelect?.let { it(item) }
                    }
            ) {
                Divider()
                Text(
                    text = item.city,
                    modifier = Modifier
                        .wrapContentHeight(Alignment.CenterVertically)
                        .weight(1f),
                )
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun CityList(
    cityList: List<CityModel>,
    hotCityList: List<CityModel>,
    scope: CoroutineScope,
    itemHeight: Dp,
    onSelect: ((city: CityModel) -> Unit)? = null
) {
    val indexBarState = rememberIndexBarState()
    val lazyColumnState = rememberLazyListState()
    var showTip by remember {
        mutableStateOf(false)
    }

    var selectTag by remember {
        mutableStateOf("")
    }

    val tagHeight = 40.dp

    remember(lazyColumnState.firstVisibleItemIndex) {
        if (cityList.isNotEmpty()) {
            val tag = cityList[lazyColumnState.firstVisibleItemIndex].tagIndex
            selectTag = tag
            indexBarState.selectIndex = tag
        }
    }
    remember(indexBarState.isTouchDown) {
        showTip = indexBarState.isTouchDown
    }

    remember(indexBarState.selectIndex) {
        selectTag = indexBarState.selectIndex
        val index = getPositionByTag(cityList, indexBarState.selectIndex)
        scope.launch {
            if (hotCityList.isEmpty()) {
                if (index >= 0) {
                    lazyColumnState.scrollToItem(index, 0)
                }
            } else {
                if (index >= 0) {
                    lazyColumnState.scrollToItem(index + 1, 0)
                }
            }

        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = lazyColumnState) {
            item {
                HotCity(hotCityList, onSelect)
            }
            items(cityList) { item ->
                if (item.isShowSuspension) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(tagHeight)
                            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
                            .padding(start = 15.dp),
                    ) {

                        Text(
                            text = item.tagIndex,
                            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                            modifier = Modifier
                                .wrapContentHeight(Alignment.CenterVertically)
                                .weight(1f)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight)
                        .padding(start = 15.dp)
                        .clickable {
                            onSelect?.let { it(item) }
                        }
                ) {

                    Text(
                        text = item.city,
                        modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically)
                            .weight(1f),

                        )
                    Divider()
                }
            }
        }
        IndexBar(
            Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp),
            state = indexBarState
        )

        AnimatedVisibility(
            visible = showTip,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(
                    Alignment.Center
                )
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectTag,
                    color = MaterialTheme.colors.surface.copy(ContentAlpha.high),
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}

@Composable
fun HotCity(hotCityList: List<CityModel>, onSelect: ((city: CityModel) -> Unit)? = null) {
    val itemSize: Dp = ((LocalConfiguration.current.screenWidthDp.dp - 40.dp) / 3)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = "热门城市")
        FlowRow(
            modifier = Modifier.padding(top = 16.dp),
            mainAxisSize = SizeMode.Expand,
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            repeat(hotCityList.size) { index ->
                val model = hotCityList[index]
                Box(
                    modifier = Modifier
                        .width(itemSize)
                        .clickable {
                            onSelect?.let { it(model) }
                        }
                        .background(
                            color = MaterialTheme.colors.onSurface.copy(0.05f),
                            shape = MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = model.city,
                        color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
                        style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}


//@Composable
//fun SearchBar(searchValue: String, onValueChange: (value: String) -> Unit) {
//    SearchBar
//    Box(
//        Modifier
//            .fillMaxWidth()
//            .padding(15.dp)
//            .background(MaterialTheme.colors.onSurface.copy(0.05f), RoundedCornerShape(5.dp))
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                Icons.Default.Search,
//                null,
//                modifier = Modifier
//                    .size(20.dp),
//                tint = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled)
//            )
//            Spacer(modifier = Modifier.width(10.dp))
//            BasicTextField(
//                value = searchValue,
//                onValueChange = onValueChange,
//                singleLine = true,
//                modifier = Modifier.weight(1f),
//                textStyle = MaterialTheme.typography.body2,
//                decorationBox = { innerTextField ->
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        if (searchValue.isEmpty()) {
//                            Text(
//                                text = "请输入搜索信息",
//                                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
//                            )
//                        }
//                    }
//                    innerTextField()
//                }
//            )
//            Spacer(modifier = Modifier.width(10.dp))
//
//            AnimatedVisibility(
//                visible = searchValue.isNotBlank(), enter = fadeIn(),
//                exit = fadeOut(),
//            ) {
//                Icon(
//                    Icons.Default.Close,
//                    null,
//                    tint = MaterialTheme.colors.surface.copy(ContentAlpha.high),
//                    modifier = Modifier
//                        .size(20.dp)
//                        .padding(2.dp)
//                        .clickable(
//                            indication = null,
//                            interactionSource = remember { MutableInteractionSource() },
//                            onClick = {
//                                onValueChange("")
//                            }
//                        )
//                        .background(
//                            MaterialTheme.colors.onSurface.copy(0.12f),
//                            CircleShape
//                        )
//                )
//            }
//        }
//    }
//}

