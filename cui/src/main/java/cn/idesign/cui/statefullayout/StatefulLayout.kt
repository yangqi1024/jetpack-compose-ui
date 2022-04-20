package cn.idesign.cui.statefullayout

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.android.parcel.Parcelize

@Composable
fun StatefulLayout(
    modifier: Modifier = Modifier,
    state: StatefulState = rememberStatefulState(),
    event: Event = Event(),
    errorLayout: @Composable (onClick: (() -> Unit)?) -> Unit = { Error(modifier, onClick = it) },
    emptyLayout: @Composable () -> Unit = { Empty() },
    netErrorLayout: @Composable (onClick: (() -> Unit)?) -> Unit = {
        NetError(
            modifier,
            onClick = it
        )
    },
    locationOffLayout: @Composable (onClick: (() -> Unit)?) -> Unit = {
        LocationOff(
            modifier,
            onClick = it
        )
    },
    loadingLayout: @Composable () -> Unit = { Loading(modifier) },
    content: @Composable () -> Unit,
) {
    Log.d("StatefulLayout", "StatefulLayout")
    Box(modifier) {
        when (state.currentState) {
            StatefulStatus.Content -> Box(modifier) { content() }
            StatefulStatus.Loading -> loadingLayout()
            StatefulStatus.Empty -> emptyLayout()
            StatefulStatus.Error -> errorLayout(event.onErrorRetry)
            StatefulStatus.LocationOff -> locationOffLayout(event.onLocationRetry)
            StatefulStatus.NetError -> netErrorLayout(event.onNetRetry)
        }
    }
}

@Composable
fun rememberStatefulState(
    initialState: StatefulStatus = StatefulStatus.Loading,
): StatefulState = rememberSaveable(saver = StatefulState.Saver) {
    StatefulState(
        initialState = initialState,
    )
}


class StatefulState(val initialState: StatefulStatus) {
    private var _currentState: StatefulStatus by mutableStateOf(initialState)

    var currentState: StatefulStatus
        get() = _currentState
        set(value) {
            if (value != _currentState) {
                _currentState = value
            }
        }

    companion object {
        val Saver: Saver<StatefulState, *> = Saver(
            save = {
                it.initialState
            },
            restore = {
                StatefulState(
                    initialState = it,
                )
            }
        )
    }
}

@Parcelize
enum class StatefulStatus : Parcelable {
    Loading, Empty, Error, LocationOff, NetError, Content
}

data class Event(
    val onLocationRetry: (() -> Unit)? = null,
    val onNetRetry: (() -> Unit)? = null,
    val onErrorRetry: (() -> Unit)? = null,
)
