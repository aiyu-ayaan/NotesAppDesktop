package utils

import androidx.compose.runtime.Composable
import navigation.NavController
import navigation.NavigationHost
import navigation.composable
import screen.CommandViewModel
import screen.add_edit.AddEditScreen
import screen.home.NoteScreen

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object AddEditScreen : Screen("add_edit_screen")
}

@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    val dataShare = CommandViewModel()
    NavigationHost(navController) {
        composable(Screen.HomeScreen.route) {
            NoteScreen(
                navController = navController,
                viewModel = dataShare
            )
        }

        composable(Screen.AddEditScreen.route) {
            AddEditScreen(
                navController = navController,
                viewModel = dataShare
            )
        }
    }.build()
}