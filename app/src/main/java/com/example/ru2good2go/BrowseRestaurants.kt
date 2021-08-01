package com.example.ru2good2go

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ru2good2go.databinding.ActivityMapsBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class BrowseRestaurants : AppCompatActivity(), OnMapReadyCallback, LocationListener,
            GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    internal lateinit var lastLocation: Location
    internal var currLocationMarker: Marker? = null
    internal var googleAPIClient: GoogleApiClient? = null
    lateinit var locationRequest: LocationRequest
    private lateinit var searchView : SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val backArrow : ImageView = findViewById(R.id.backArrow)
        backArrow.setOnClickListener {
            onBackPressed()
        }

        searchView = findViewById(R.id.searchLocation)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var addressList: List<Address>? = null
                if(query == null || query == "") {
                    Toast.makeText(applicationContext, "Must provide location", Toast.LENGTH_SHORT).show()
                } else {
                    val geoCoder = Geocoder(applicationContext)
                    try {
                        addressList = geoCoder.getFromLocationName(query, 1)
                    } catch(e: IOException) {
                        Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                        return false
                    }
                    try {
                        val address = addressList!![0]
                        val latLng = LatLng(address.latitude, address.longitude)
                        //Toast.makeText(applicationContext, "lat: " + address.latitude + " long: " + address.longitude, Toast.LENGTH_SHORT).show()

                        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11.0f))
                    } catch(e: NullPointerException) {
                        //Toast.makeText(applicationContext, "No results found", Toast.LENGTH_SHORT).show()
                        return false
                    }

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                buildGoogleAPIClient()
                mMap!!.isMyLocationEnabled = true
            }
        } else {
            buildGoogleAPIClient()
            mMap!!.isMyLocationEnabled = true
        }

        val nb = LatLng(40.5008, -74.4474)
        //mMap.addMarker(MarkerOptions().position(nb).title("Marker in New Brunswick"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nb))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(14.0f))

        //dummy data -- markers
        val tacoria = LatLng(40.4977, -74.4489)
        mMap.addMarker(MarkerOptions().position(tacoria).title("Tacoria"))
        val fritzs = LatLng(40.4992, -74.4520)
        mMap.addMarker(MarkerOptions().position(fritzs).title("Fritz's"))
        val honeygrow = LatLng(40.4991, -74.4481)
        mMap.addMarker(MarkerOptions().position(honeygrow).title("Honeygrow"))

        mMap.setOnMarkerClickListener { marker ->
            val name : String = marker.title
            val restaurantImg : ImageView = findViewById(R.id.restaurantImg)
            val restaurantTextView : TextView = findViewById(R.id.restaurantName)
            restaurantTextView.text = name
            val linearLayout : LinearLayout = findViewById(R.id.fragment_layout)

            //setting correct images -- what is the best way to do this when we have lots of restaurants?
            if(marker.title == "Honeygrow") {
                restaurantImg.setImageResource(R.mipmap.honeygrow_foreground)
            } else if(marker.title == "Fritz's") {
                restaurantImg.setImageResource(R.mipmap.fritzs_foreground)
            } else if(marker.title == "Tacoria") {
                restaurantImg.setImageResource(R.mipmap.tacoria_foreground)
            }
            linearLayout.visibility = View.VISIBLE
            true
        }

    }

    protected fun buildGoogleAPIClient() {
        googleAPIClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        googleAPIClient!!.connect()

    }

    override fun onLocationChanged(location: Location) {
        lastLocation = location
        if(currLocationMarker != null) {
            currLocationMarker!!.remove()
        }
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        currLocationMarker = mMap!!.addMarker(markerOptions)

        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.moveCamera(CameraUpdateFactory.zoomTo(11f))

        if(googleAPIClient != null) {
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        locationRequest = LocationRequest.create()
        locationRequest.interval = 1000
        locationRequest.fastestInterval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.getFusedLocationProviderClient(this)
        }
    }



    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }



    //fun searchLocation() {
      //  val locationSearch: SearchView = findViewById<SearchView>(R.id.searchLocation)
        //var location: String

    //}
}