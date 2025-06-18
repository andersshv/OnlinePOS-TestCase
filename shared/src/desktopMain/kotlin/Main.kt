import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.onlinepos.testcase.ui.MainScreen

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.system.exitProcess

@Composable fun MainView() = MainScreen()

@Preview
@Composable
fun AppPreview() {
    MainScreen()
}

fun main() = application {
    Window(
        onCloseRequest = { exitProcess(0) }, // 👈 clean exit on close
        title = "OnlinePOS"
    ) {
        MainScreen()
    }
}