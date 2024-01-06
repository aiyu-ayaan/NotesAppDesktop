package screen.add_edit

import Note
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comman.Toolbar
import navigation.NavController
import screen.CommandViewModel
import screen.SharedEvents


@Composable
@Preview
fun AddEditScreen(
    navController: NavController,
    viewModel: CommandViewModel,
) {
    var title by remember { mutableStateOf(viewModel.currentClickNote?.title ?: "") }
    var des by remember { mutableStateOf(viewModel.currentClickNote?.des ?: "") }
    val created by remember { mutableStateOf(viewModel.currentClickNote?.created ?: 0L) }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(title = "Add Note", navigationIcon = {
            IconButton(onClick = {
                viewModel.onEvent(
                    SharedEvents.OnBack
                )
                navController.navigateBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }, floatingActionButton = {
        AnimatedVisibility(visible = title.isNotBlank() && des.isNotBlank()) {
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.onEvent(
                        SharedEvents.SaveNote(
                            note = Note(
                                title = title,
                                des = des,
                                created = created
                            )
                        )
                    )
                    navController.navigateBack()
                },
                text = { Text(text = "Save") },
                icon = { Icon(imageVector = Icons.Default.Create, contentDescription = "Save") },
            )
        }
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            OutlinedTextField(value = title,
                onValueChange = {
                    title = it
                },
                placeholder = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.h6,
                trailingIcon = {
                    if (title.isNotBlank()) IconButton(onClick = {
                        title = ""
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete")
                    }
                })
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            OutlinedTextField(value = des,
                onValueChange = {
                    des = it
                },
                placeholder = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.body1,
                trailingIcon = {
                    if (des.isNotBlank()) IconButton(onClick = {
                        des = ""
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete")
                    }
                })
        }
    }
}