package gilbert.assessment.gilbertaccentureassessment.ui.fragment.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gilbert.assessment.gilbertaccentureassessment.model.UserData
import gilbert.assessment.gilbertaccentureassessment.repository.DataRepoSource
import gilbert.assessment.gilbertaccentureassessment.ui.fragment.userList.adapter.UserAdapter
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val dataRepository: DataRepoSource): ViewModel() {

    private val _userList: MutableLiveData<Resource<MutableList<UserData>>> = MutableLiveData()
    val userList: LiveData<Resource<MutableList<UserData>>> get() = _userList

    fun getUserList() {
        viewModelScope.launch {
            _userList.value = Resource.Loading()
            withContext(Dispatchers.Main) {
                dataRepository.getListUser().collectLatest {
                    _userList.value = it
                }
            }
        }
    }

    fun filter(text: String, adapter: UserAdapter) {
        val tempList: ArrayList<UserData> = arrayListOf()
        for (item in userList.value?.data ?: mutableListOf()) {
            if (item.login.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                tempList.add(item)
            }
        }
        adapter.filterList(tempList)
    }
}