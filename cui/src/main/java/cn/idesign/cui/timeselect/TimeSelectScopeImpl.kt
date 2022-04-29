package cn.idesign.cui.timeselect

class TimeSelectScopeImpl : TimeSelectScope {
    private val _intervals = mutableListOf<TimeSelectIntervalContent>()
    val intervals: List<TimeSelectIntervalContent> = _intervals
    override fun timePanel(
        title: String, key: String,
        data: List<String>,
    ) {
        _intervals.add(
            TimeSelectIntervalContent(
                key = key,
                title = title,
                data = data,
            )
        )
    }
}


class TimeSelectIntervalContent(
    val key: String,
    val title: String,
    val data: List<String>,
)