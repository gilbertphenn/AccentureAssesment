package gilbert.assessment.gilbertaccentureassessment.ui.fragment.userList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gilbert.assessment.gilbertaccentureassessment.R
import gilbert.assessment.gilbertaccentureassessment.databinding.FragmentUserListBinding
import gilbert.assessment.gilbertaccentureassessment.ui.fragment.userList.adapter.UserAdapter
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import gilbert.assessment.gilbertaccentureassessment.utils.checkStringNullOrNot
import gilbert.assessment.gilbertaccentureassessment.utils.showView

@AndroidEntryPoint
class UserList: Fragment(), UserAdapter.AdapterItemOnClick {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by lazy { ViewModelProvider(this)[UserListViewModel::class.java] }
    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewRoot = layoutInflater.inflate(R.layout.fragment_user_list, container, false)
        binding = FragmentUserListBinding.bind(viewRoot)
        binding.vm = viewModel
        initController()
        return binding.root
    }

    private fun initController() {
        initListener()
    }

    private fun initListener() {

    }

    override fun onResume() {
        super.onResume()
        observable()
    }

    private fun observable() {
        viewModel.getUserList()
        viewModel.userList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.iLoading.clLoading.showView()
                }
                is Resource.DataError -> {
                    binding.iLoading.clLoading.showView()
                }
                else -> {
                    if (it.data!!.isNotEmpty()) {
                        adapter = UserAdapter(requireActivity(), it.data, this)
                        binding.rvUser.adapter = adapter
                        binding.etSearch.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                return
                            }

                            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                                return
                            }

                            override fun afterTextChanged(text: Editable?) {
                                if (checkStringNullOrNot(text.toString())) {
                                    viewModel.filter(text.toString(),adapter)
                                    return
                                }else{
                                    adapter.filterList(viewModel.userList.value?.data?: mutableListOf())
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onItemClick(name: String) {
        val bundle = bundleOf("username" to  name)
        findNavController().navigate(R.id.action_usersListFragment_to_singleUserFragment, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}