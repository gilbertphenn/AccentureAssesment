package gilbert.assessment.gilbertaccentureassessment.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity: AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> activityBinding(@LayoutRes resId: Int): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }
}