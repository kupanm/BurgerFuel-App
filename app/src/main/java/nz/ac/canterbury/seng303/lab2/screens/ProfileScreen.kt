package nz.ac.canterbury.seng303.lab2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.viewmodels.SettingViewModel

@Composable
fun Profile(navController: NavController, settingViewModel: SettingViewModel)
{

    val isDarkMode by settingViewModel.isDarkMode.collectAsState()
    val backgroundColorMain = if (isDarkMode) {
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
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColorMain),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val gradientBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF4A2F7f), // Purple Shade
                Color(0xFF0ABEF0) // Blue Shade
            ),
            start = Offset(0f, 1000f),
            end = Offset(1000f, 0f)
        )

        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Column(
            modifier = Modifier
                .wrapContentSize()
                .border(
                    width = 8.dp,
                    brush = gradientBrush,
                    shape = RoundedCornerShape(24.dp)
                )
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(24.dp))
                .background(gradientBrush),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.qr_code_placeholder),
                contentDescription = "QR code",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp).width(1.dp))
            Image(
                painter = painterResource(id = R.drawable.kids),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp),
                        clip = false
                    )
            )
            Text(
                "Full name",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(10.dp).width(1.dp))
        }

        Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())

        val iconSize: Int = 75
        val borderWidth: Float = 2F
        val textSize = MaterialTheme.typography.bodyLarge
        val backgroundColor = gradientBrush
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            // Settings Button
            Button(
                onClick = { navController.navigate("Settings")},
                shape = RoundedCornerShape(0.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Settings",
                        tint = Color.White,
                        modifier = Modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(8.dp),
                                clip = false
                            )
                            .clip(RoundedCornerShape(24.dp))
                            .background(backgroundColor)
                            .padding(8.dp)
                            .size(iconSize.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Settings",
                        color = textColor,
                        style = textSize
                    )
                }
            }
            // Contact Button
            Button(
                onClick = { navController.navigate("Contact") },
                shape = RoundedCornerShape(0.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Outlined.MailOutline,
                        contentDescription = "Contact",
                        tint = Color.White,
                        modifier = Modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(8.dp),
                                clip = false
                            )
                            .clip(RoundedCornerShape(24.dp))
                            .background(backgroundColor)
                            .padding(8.dp)
                            .size(iconSize.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Contact",
                        color = textColor,
                        style = textSize
                    )
                }
            }
            // Allergy Button
            Button(
                onClick = { navController.navigate("Allergy") },
                shape = RoundedCornerShape(0.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Outlined.List,
                        contentDescription = "Allergy",
                        tint = Color.White,
                        modifier = Modifier
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(8.dp),
                                clip = false
                            )
                            .clip(RoundedCornerShape(24.dp))
                            .background(backgroundColor)
                            .padding(8.dp)
                            .size(iconSize.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Allergy",
                        color = textColor,
                        style = textSize
                    )
                }
            }
        }
    }
}