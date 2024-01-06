package screen.home

import Note
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import comman.Toolbar
import navigation.NavController
import screen.CommandViewModel
import screen.SharedEvents
import utils.Screen


@Composable
@Preview
fun NoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CommandViewModel,
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        Toolbar(
            title = "Notes",
        )
    }, floatingActionButton = {
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(
                    Screen.AddEditScreen.route
                )
            },
            text = { Text(text = "Add Note") },
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note") },
        )
    }) {
        if (viewModel.noteList.value.isEmpty()) {
            EmptyScreen()
            return@Scaffold
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 500.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(viewModel.noteList.value, key = { it.created }) {
                NotesItem(
                    note = it,
                    onClick = {
                        viewModel.onEvent(
                            SharedEvents.ClickNote(
                                note = it
                            )
                        )
                        navController.navigate(
                            Screen.AddEditScreen.route
                        )
                    },
                    onDeleteClick = {
                        viewModel.onEvent(
                            SharedEvents.DeleteNote(
                                note = it
                            )
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun NotesItem(
    note: Note,
    onClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    var isPopUpVisible by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .combinedClickable(
                onLongClick = {
                    isPopUpVisible = true
                },
                onClick = {
                    onClick()
                }
            )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            elevation = 8.dp, shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = note.title,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = note.des,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (isPopUpVisible)
            Popup {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            isPopUpVisible = false
                            onDeleteClick.invoke()
                        },
                    elevation = 8.dp, shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                        )
                        Text(text = "Delete")
                    }
                }

            }
    }
}


@Composable
@Preview
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = painterResource("empty.png")
        Image(
            modifier = Modifier.fillMaxWidth(0.50f),
            painter = bitmap, contentDescription = "Empty"
        )
        Text(
            text = "No Notes",
            style = MaterialTheme.typography.h4
        )
    }
}