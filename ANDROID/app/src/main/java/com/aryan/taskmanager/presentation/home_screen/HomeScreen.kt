import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryan.taskmanager.ui.theme.spotifyBG

@Composable
fun HomeComposable(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(spotifyBG)
    ) {
        ExtendedFloatingActionButton(
            onClick = { /* Handle click action */ },
            text = { Text(text = "Create New Task") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Icon"
                )
            },
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
