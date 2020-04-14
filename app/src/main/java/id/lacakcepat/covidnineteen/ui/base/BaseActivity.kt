package id.lacakcepat.covidnineteen.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.lacakcepat.covidnineteen.util.SharedPreference
import javax.inject.Inject

/**
 * This class is used to replace the AppCompatActivity class.
 * here implements functions that are used repeatedly in the activity.
 *
 * @param<V> - Mean layout binding
 */
abstract class BaseActivity<V: ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreference: SharedPreference

    private lateinit var viewBinding: V

    /**
     * This method get layout value from resource
     */
    protected abstract fun getContentView(): Int

    /**
     * This method replaces the function of onCreate in the android lifecycle
     */
    protected abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView<V>(this, getContentView())
            .apply { lifecycleOwner = this@BaseActivity }

        onCreated(savedInstanceState)

    }

    protected fun getViewBinding() = viewBinding

}