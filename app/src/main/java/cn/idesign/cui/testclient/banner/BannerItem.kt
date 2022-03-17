package cn.idesign.cui.testclient.banner
import cn.idesign.cui.testclient.R

data class BannerItem(val id:Int,val title:String)


val dataList = arrayListOf(
    BannerItem(R.drawable.advertise0,"相信自己,你努力的样子真的很美"),
    BannerItem(R.drawable.advertise1,"极致简约,梦幻小屋"),
    BannerItem(R.drawable.advertise2,"超级卖梦人"),
    BannerItem(R.drawable.advertise3,"夏季新搭配"),
    BannerItem(R.drawable.advertise4,"绝美风格搭配"),
)