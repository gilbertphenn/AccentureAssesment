package gilbert.assessment.gilbertaccentureassessment.ui.activity.mainActivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import gilbert.assessment.gilbertaccentureassessment.R
import gilbert.assessment.gilbertaccentureassessment.base.BaseActivity
import gilbert.assessment.gilbertaccentureassessment.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initController()
    }

    private fun initController() {
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = findNavController(this, R.id.fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(this, R.id.fragment).navigateUp()
}