package nz.ac.canterbury.seng303.lab2.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import nz.ac.canterbury.seng303.lab2.models.Store
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.rememberCameraPositionState
import nz.ac.canterbury.seng303.lab2.MainActivity
import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.viewmodels.SettingViewModel


@Composable
fun Locations(navController: NavController, settingViewModel: SettingViewModel)
{
    // current context
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var foundUserLocation by remember { mutableStateOf(false) }

    var userLocation by remember {mutableStateOf(LatLng(-43.5565101,172.7005736))}


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-43.5307882, 172.5752179), 13f) // Default to store
    }


    // Check if app has location permissions
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.

        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = {hasPermission ->
                if (hasPermission) {
                    locationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token)
                        .addOnSuccessListener { location ->
                            userLocation = LatLng(location.latitude, location.longitude)

                            foundUserLocation = true
                            cameraPositionState.position = CameraPosition(userLocation,13f,0f,0f)

                            Log.d("LOCATION", "Found user location")
                        }
                }
            })
        
        LaunchedEffect(Unit) {
            launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        Log.d("LOCATION", "Location permission not granted" )
    } else {

        locationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token)
            .addOnSuccessListener { location ->
                userLocation = LatLng(location.latitude, location.longitude)

                foundUserLocation = true
                cameraPositionState.position = CameraPosition(userLocation,13f,0f,0f)

                Log.d("LOCATION", "Found user location")
            }
            .addOnFailureListener { exception ->
                Log.d("LOCATION", "Location Exception: $exception")
            }
    }



    var isMapLoaded by remember { mutableStateOf(false) }

    val storeList : List<Store> = Store.getStores()

    var selectedMarker by remember { mutableStateOf<Marker?>(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f)) {
            val currentStore by settingViewModel.currentStore.collectAsState()
            Text(text = "Current store: " + currentStore)
        }

        // Google maps
        Box() {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    isMapLoaded = true
                }
            ) {

                if (foundUserLocation) {
                    Log.d("LOCATION", "User location marker placed")
                    Marker(
                        rememberMarkerState(position = userLocation),
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)

                    )
                    Log.d("LOCATION", "Placed user location marker at $userLocation")
                }

                storeList.forEach { place ->
                    val storeMarker = rememberMarkerState(position = place.coords)

                    Marker(storeMarker, title = place.name,

                        onClick = {
                            selectedMarker = it
                            true
                        }
                    )

                    selectedMarker?.let { markerState ->
                        StoreInfoWindow(markerState, settingViewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun StoreInfoWindow(selectedStore : Marker, settingViewModel: SettingViewModel)
{
    Log.d("STORE_MAP", "Selected store: " + selectedStore.title)
    if(selectedStore.title != null) {

        settingViewModel.setStore(selectedStore.title!!)
    }
}