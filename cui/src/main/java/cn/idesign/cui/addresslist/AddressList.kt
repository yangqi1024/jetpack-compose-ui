package cn.idesign.cui.addresslist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.util.*

@Composable
fun AddressList(
    modifier: Modifier = Modifier,
    data: List<AddressModel> = Collections.emptyList(),
    divider: Boolean = true,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        items(data.size) { index ->
            AddressItem(
                data = data[index],
                divider = if (divider) index != data.size - 1 else false
            )
        }
    }
}