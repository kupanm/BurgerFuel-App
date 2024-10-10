import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.viewmodels.SettingViewModel

@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel) {
    val isDarkMode by settingViewModel.isDarkMode.collectAsState()

    val backgroundColor = if (isDarkMode) {
        colorResource(id = R.color.black)
    } else {
        colorResource(id = R.color.white)
    }

    val textColor = if (isDarkMode) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.black)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(backgroundColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Appearance: " + if (isDarkMode) "Dark Theme" else "Light Theme",
                fontSize = 20.sp,
                color = textColor,
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Switch (Toggle) to represent the toggle action
            Switch(
                checked = isDarkMode,
                onCheckedChange = { settingViewModel.toggleTheme() }, // Update state on toggle
                modifier = Modifier.padding(16.dp)
            )
        }



        // Spacer for layout spacing
        Spacer(modifier = Modifier.height(16.dp))

        // You can add other UI elements here
    }
}