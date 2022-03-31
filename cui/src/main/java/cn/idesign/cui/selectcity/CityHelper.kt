package cn.idesign.cui.selectcity

import com.github.promeg.pinyinhelper.Pinyin
import java.util.*

fun sortCity(data: MutableList<CityModel>) {
    setIndexPinYin(data)

    //排序
    data.sortWith { lhs, rhs ->
        when {
            lhs.tagIndex == "#" -> 1
            rhs.tagIndex == "#" -> -1
            else -> lhs.pinyin.compareTo(rhs.pinyin)
        }
    }
    //判断是否显示Tag
    var tempTag: String? = null
    data.forEach { model ->
        run {
            val tag = model.tagIndex
            if (tempTag != tag) {
                tempTag = tag
                model.isShowSuspension = true
            } else {
                model.isShowSuspension = false
            }
        }
    }
}


fun setIndexPinYin(data: List<CityModel>) {

    data.forEach { model ->
        run {
            if (model.isNeedToPinyin) {
                val target = model.city
                val pySb = StringBuilder()
                for (element in target) {
                    //利用TinyPinyin将char转成拼音
                    //查看源码，方法内 如果char为汉字，则返回大写拼音
                    //如果c不是汉字，则返回String.valueOf(c)
                    pySb.append(Pinyin.toPinyin(element).uppercase(Locale.getDefault()))
                }
                val pinyin = pySb.toString()
                model.pinyin = pinyin
                val tag = pinyin.substring(0, 1)
                if (tag.matches(Regex("[A-Z]"))) {
                    model.tagIndex = tag
                } else {
                    model.tagIndex = "#"
                }
            }
        }
    }
}


fun getPositionByTag(data: List<CityModel>, tag: String): Int {
    for (index in data.indices) {
        val item = data[index]
        if (item.tagIndex == tag) {
            return index
        }
    }
    return -1
}