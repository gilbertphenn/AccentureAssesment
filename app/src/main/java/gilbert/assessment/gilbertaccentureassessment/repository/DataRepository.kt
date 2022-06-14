package gilbert.assessment.gilbertaccentureassessment.repository

import gilbert.assessment.gilbertaccentureassessment.io.RemoteData
import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteData: RemoteData, private val ioDispatcher: CoroutineContext) : DataRepoSource {

    override suspend fun getListUser(): Flow<Resource<MutableList<UserData>>> {
        return flow {
            emit(remoteData.getListUser())
        }.flowOn(ioDispatcher)
    }

    override suspend fun getDetail(userName: String): Flow<Resource<UserData>> {
        return flow {
            emit(remoteData.getDetail(userName))
        }.flowOn(ioDispatcher)
    }
}