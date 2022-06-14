package gilbert.assessment.gilbertaccentureassessment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class Network @Inject constructor(val context: Context) : NetworkConnection {
    override fun getInfoNetwork(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    override fun isConnected(): Boolean {
        val info = getInfoNetwork()
        return info != null && info.isConnected
    }

}

interface NetworkConnection{
    fun getInfoNetwork(): NetworkInfo?
    fun isConnected():Boolean
}