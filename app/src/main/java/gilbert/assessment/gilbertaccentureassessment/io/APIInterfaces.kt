package gilbert.assessment.gilbertaccentureassessment.io

import gilbert.assessment.gilbertaccentureassessment.model.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterfaces {

    @GET("users")
    suspend fun getListUser(): Response<MutableList<UserData>>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") userName: String): Response<UserData>
}