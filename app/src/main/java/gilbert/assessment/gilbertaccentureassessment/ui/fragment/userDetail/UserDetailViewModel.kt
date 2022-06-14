package gilbert.assessment.gilbertaccentureassessment.ui.fragment.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.repository.DataRepoSource
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val dataRepository: DataRepoSource): ViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData()
    val userData: MutableLiveData<Resource<UserData>> = MutableLiveData()

    fun getUserDetail() {
        viewModelScope.launch {
            userData.value = Resource.Loading()
            withContext(Dispatchers.Main) {
                dataRepository.getDetail(userName.value ?: "").collectLatest {
                    userData.value = it
                }
            }
        }
    }
}