package id.lacakcepat.covidnineteen.service

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*

class BluetoothReceiver : BroadcastReceiver() {
    companion object {
        const val TAG = "BluetoothReceiver"
        const val MINIMUM_SPEED = 4.6
        fun deviceToString(device: BluetoothDevice): String {
            return "[Address: " + device.address + ", Name: " + device.name + "]"
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        Log.d(TAG, "Incoming intent : $action")

        when (action) {
            BluetoothDevice.ACTION_FOUND -> {
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                if (context != null) {
                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                    fusedLocationClient.lastLocation.addOnCompleteListener {
                        val location: Location? = it.result
                        if (location == null) {
                            requestNewLocationData(context)
                        } else {
                            Log.d(TAG, "speed: ${location.speed}")
                            Log.d(TAG, "latitude: ${location.latitude}")
                            Log.d(TAG, "longitude: ${location.longitude}")
                        }
                    }
                }
            }
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                Log.d(TAG, "Discovery ended.")
            }
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                Log.d(TAG, "Bluetooth state changed.")
            }
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            Log.d(TAG, "latitude: ${mLastLocation.latitude}")
            Log.d(TAG, "longitude: ${mLastLocation.longitude}")
        }
    }

    private fun requestNewLocationData(context: Context) {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }
}