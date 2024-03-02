import android.app.Activity
import android.view.View
import android.view.Window

class UiUtils {

    fun setStatusBar(activity: Activity, statusBarColor: Int) {
        val window: Window = activity.window

        window.statusBarColor = activity.getColor(statusBarColor)

        // Set the status bar text color to white
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}
