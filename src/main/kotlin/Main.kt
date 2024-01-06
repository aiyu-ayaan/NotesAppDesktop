import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import navigation.rememberNavController
import utils.CustomNavigationHost
import utils.Screen

@Composable
@Preview
fun App() {
    val navController by rememberNavController(Screen.HomeScreen.route)
    CustomNavigationHost(navController = navController)
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
