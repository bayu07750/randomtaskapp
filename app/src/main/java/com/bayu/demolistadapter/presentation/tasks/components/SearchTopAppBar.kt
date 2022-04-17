package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import com.bayu.demolistadapter.core.data.utils.Sorting

@Composable
fun SearchTopAppBar(
    isShowMenu: Boolean,
    setIsShowMenu: (Boolean) -> Unit,
    sortingTask: Sorting,
    setSortingTask: (Sorting) -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Tasks") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Task"
                )
            }
            IconButton(onClick = { setIsShowMenu(!isShowMenu) }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Vert")
            }
            DropdownMenuRadio(
                isShowMenu = isShowMenu,
                setIsShowMenu = setIsShowMenu,
                sortingTask = sortingTask,
                setSortingTask = setSortingTask,
            )
        },
    )
}