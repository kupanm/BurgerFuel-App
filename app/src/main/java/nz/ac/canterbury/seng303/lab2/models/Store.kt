package nz.ac.canterbury.seng303.lab2.models

import com.google.android.gms.maps.model.LatLng

class Store(val id: Int,
    val name: String,
    val coords: LatLng
): Identifiable {

    companion object {
        fun getStores(): List<Store> {
            return listOf (
                Store (1,
                    "Bush Inn Riccarton",
                    LatLng(-43.5307882, 172.5752179)),
                Store(2,
                    "Sydenham",
                    LatLng(-43.5498754,172.6373158)),
                Store(3,
                    "Ferrymead",
                    LatLng(-43.55647433, 172.7005843)),
                Store(4,
                "Spitfire Square",
                    LatLng(-43.49079179, 172.5480479))
            )
        }
    }
    override fun getIdentifier(): Int {
        return id;
    }
}