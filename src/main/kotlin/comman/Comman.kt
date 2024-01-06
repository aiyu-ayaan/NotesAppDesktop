package comman

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
@Preview
fun Toolbar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        navigationIcon = navigationIcon
    )
}