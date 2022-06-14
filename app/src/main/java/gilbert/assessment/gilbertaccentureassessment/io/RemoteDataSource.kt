package gilbert.assessment.gilbertaccentureassessment.io

import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.utils.Resource

interface RemoteDataSource {
    //get user list
    suspend fun getListUser(): Resource<MutableList<UserData>>

    //get detail user list
    suspend fun getDetail(userName:String): Resource<UserData>
}