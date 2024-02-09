package com.example.foodcourt

import android.content.ContentProviderClient
import android.content.pm.PackageManager
import android.Manifest
import android.location.Address
import android.widget.Toast
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.foodcourt.databinding.ActivityChooseLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*


@Suppress("DEPRECATION")
class ChooseLocationActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
    private val binding: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val locationlist : Array<String> = arrayOf("Maradana", "Dehiwala", "Union Place")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationlist)
        val autoCompleteTextView = binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)

        //Geo Location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
            ){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }else{
            fetchLocation()
        }

    }
    private fun fetchLocation(){
        var geocoder = Geocoder(this, Locale.getDefault())
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        // Get address from coordinates
                        val addresses: List<Address> =
                            geocoder.getFromLocation(latitude, longitude, 1) as List<Address>
                        if (addresses.isNotEmpty()) {
                            val address: Address = addresses[0]
                            val addressText = address.getAddressLine(0)
                            // Update TextView with address
                            binding.Currentloc.text =  addressText

                        } else {
                            showToast("No address found")
                        }
                    } else {
                        showToast("No location available")
                    }
                }
                .addOnFailureListener { e ->
                    showToast("Failed to get location: ${e.message}")
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch location
                fetchLocation()
            } else {
                showToast("Location permission denied")
            }
        }
    }
}