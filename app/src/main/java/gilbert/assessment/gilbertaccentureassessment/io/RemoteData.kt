package gilbert.assessment.gilbertaccentureassessment.io

import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.utils.Config.NETWORK_ERROR
import gilbert.assessment.gilbertaccentureassessment.utils.Config.NO_INTERNET_CONNECTION
import gilbert.assessment.gilbertaccentureassessment.utils.NetworkConnection
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(private val networkConnection: NetworkConnection, private val restService: RestClient, ) : RemoteDataSource {

    private val apiService = restService.createService(APIInterfaces::class.java)

    override suspend fun getListUser(): Resource<MutableList<UserData>> {
        val responseCall = apiService::getListUser
        if (!networkConnection.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                Resource.Success(data = response.body()  as MutableList<UserData>)
            } else {
                Resource.DataError(errorCode = responseCode)
            }
        } catch (e: IOException) {
            return Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun getDetail(userName: String): Resource<UserData> {
        val responseCall = apiService::getUserDetail
        if (!networkConnection.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION)
        }
        return try {
            val response = responseCall.invoke(userName)
            val responseCode = response.code()
            if (response.isSuccessful) {
                Resource.Success(data = response.body() as UserData)
            } else {
                Resource.DataError(errorCode = responseCode)
            }
        } catch (e: IOException) {
            return Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }
}