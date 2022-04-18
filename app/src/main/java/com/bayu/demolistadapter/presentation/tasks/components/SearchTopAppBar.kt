package com.bayu.demolistadapter.presentation.tasks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.bayu.demolistadapter.core.data.utils.Sorting

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTopAppBar(
    isShowMenu: Boolean,
    setIsShowMenu: (Boolean) -> Unit,
    sortingTask: Sorting,
    setSortingTask: (Sorting) -> Unit,
    queryTask: String,
    setQueryTask: (String) -> Unit,
) {
    val (expandSearchBar, setExpandSearchBar) = remember { mutableStateOf(false) }

    if (expandSearchBar) {
        TopAppBar {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = { setExpandSearchBar(false) }) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
                }
                TextField(
                    value = queryTask,
                    onValueChange = { setQueryTask(it) },
                    modifier = Modifier
                        .weight(1F),
                    placeholder = {
                        Text(text = "Search Task")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onPrimary,
                        placeholderColor = MaterialTheme.colors.onPrimary.copy(alpha = .8F)
                    )
                )
                IconButton(onClick = { setQueryTask("") }) {
                    Icon(imageVector = Icons.Rounded.Clear, contentDescription = "Delete Query")
                }
                IconButton(onClick = { setIsShowMenu(!isShowMenu) }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Vert")
                }
                DropdownMenuRadio(
                    isShowMenu = isShowMenu,
                    setIsShowMenu = setIsShowMenu,
                    sortingTask = sortingTask,
                    setSortingTask = setSortingTask,
                    offset = DpOffset(240.dp, 0.dp),
                )
            }
        }
    } else {
        TopAppBar(
            title = { Text(text = "Tasks") },
            actions = {
                IconButton(onClick = { setExpandSearchBar(true) }) {
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
}