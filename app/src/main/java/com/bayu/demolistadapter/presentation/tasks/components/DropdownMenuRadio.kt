package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.bayu.demolistadapter.R
import com.bayu.demolistadapter.core.data.utils.Sorting

@Composable
fun DropdownMenuRadio(
    isShowMenu: Boolean,
    setIsShowMenu: (Boolean) -> Unit,
    sortingTask: Sorting,
    setSortingTask: (Sorting) -> Unit,
    offset: DpOffset = DpOffset(0.dp, 0.dp)
) {
    DropdownMenu(
        expanded = isShowMenu,
        onDismissRequest = { setIsShowMenu(false) },
        offset = offset,
    ) {
        DropdownMenuRadioItem(
            text = stringResource(id = R.string.sort_latest),
            selected = sortingTask == Sorting.LATEST,
            onClick = {
                setSortingTask(Sorting.LATEST)
                setIsShowMenu(!isShowMenu)
            }
        )
        DropdownMenuRadioItem(
            text = stringResource(id = R.string.sort_oldest),
            selected = sortingTask == Sorting.OLDEST,
            onClick = {
                setSortingTask(Sorting.OLDEST)
                setIsShowMenu(!isShowMenu)
            }
        )
    }
}

@Composable
fun DropdownMenuRadioItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    DropdownMenuItem(onClick = onClick) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = text)
            RadioButton(
                selected = selected,
                onClick = onClick
            )
        }
    }
}