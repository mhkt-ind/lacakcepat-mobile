package id.lacakcepat.covidnineteen.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * This class is used to replace the Fragment class.
 * here implements functions that are used repeatedly in the fragment.
 *
 * @param<V> - Mean layout binding
 */
abstract class BaseFragment<V: ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewBinding: V

    /**
     * This method get layout value from resource
     */
    protected abstract fun getContentView(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate<V>(inflater, getContentView(), container, false)
            .apply { lifecycleOwner = this@BaseFragment }
        return viewBinding.root
    }

    protected fun getViewBinding() = viewBinding

}