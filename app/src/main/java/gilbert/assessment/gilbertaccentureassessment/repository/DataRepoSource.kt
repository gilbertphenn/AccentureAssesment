package gilbert.assessment.gilbertaccentureassessment.repository

import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DataRepoSource {
    //get user list
    suspend fun getListUser(): Flow<Resource<MutableList<UserData>>>
    //get detail user list
    suspend fun getDetail(userName:String): Flow<Resource<UserData>>
}