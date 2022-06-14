package gilbert.assessment.gilbertaccentureassessment.ui.fragment.userDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import gilbert.assessment.gilbertaccentureassessment.R
import gilbert.assessment.gilbertaccentureassessment.databinding.FragmentUserDetailBinding
import gilbert.assessment.gilbertaccentureassessment.utils.Resource
import gilbert.assessment.gilbertaccentureassessment.utils.hideView
import gilbert.assessment.gilbertaccentureassessment.utils.showView

@AndroidEntryPoint
class UserDetail: Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by lazy { ViewModelProvider(this)[UserDetailViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = layoutInflater.inflate(R.layout.fragment_user_detail, container, false)
        binding = FragmentUserDetailBinding.bind(viewRoot)
        binding.vm = viewModel

        viewModel.userName.value = arguments?.getString("username")
        Log.d("userName", "${viewModel.userName.value}")
        initController()
        return binding.root
    }

    private fun initController() {
        viewModel.userData.observe(requireActivity()) {
            when(it) {
                is Resource.Loading -> {
                    binding.llcMain.hideView()
                    binding.iLoading.clLoading.showView()
                }
                is Resource.DataError -> {
                    binding.llcMain.hideView()
                    binding.iLoading.clLoading.showView()
                }
                else -> {
                    binding.iLoading.clLoading.hideView()
                    binding.llcMain.showView()

                    val name = it.data?.login
                    val followers = it.data?.followers
                    val following = it.data?.following
                    val company = it.data?.company
                    val profile = it.data?.avatar_url
                    val joinedAt = it.data?.created_at

                    binding.tvToolbarName.text = name
                    binding.tvName.text = name
                    binding.tvFollowers.text = followers.toString()
                    binding.tvFollowing.text = following.toString()
                    binding.tvCompany.text = company
                    binding.tvJoinedAt.text = joinedAt
                    Glide.with(this).load(profile).into(binding.civProfile)
                }
            }
        }
    }

    private fun observable() {
        viewModel.getUserDetail()
    }

    override fun onResume() {
        super.onResume()
        observable()
    }
}