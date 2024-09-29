package nz.ac.canterbury.seng303.lab2.viewmodels

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.model.Marker

internal class MapInfoWindow : AppCompatActivity(),
    GoogleMap.OnInfoWindowClickListener,
    OnMapReadyCallback {
    override fun onMapReady(googleMap: GoogleMap) {
        // Add markers to the map and do other map setup.
        // ...
        // Set a listener for info window events.
        googleMap.setOnInfoWindowClickListener(this)
    }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(
            this, "Info window clicked",
            Toast.LENGTH_SHORT
        ).show()
    }
}
