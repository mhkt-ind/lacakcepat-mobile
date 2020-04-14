package id.lacakcepat.covidnineteen.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * This class is used to replace the Adapter RecyclerView class.
 * here implements functions that are used repeatedly in the adapter.
 *
 * @param<V> - Mean layout binding
 * @param<H> - Mean recycler view holder
 * @param<E> - Mean entity
 */
abstract class BaseAdapter<V : ViewDataBinding, H: RecyclerView.ViewHolder, E : Any>(
    private val context: Context?,
    private val items: List<Any>?
) : RecyclerView.Adapter<H>() {

    private lateinit var viewBinding: V

    /**
     * This method get layout value from resource
     */
    protected abstract fun getContentView(): Int

    /**
     * This method get view holder from subclass
     */
    protected abstract fun getViewHolder(view: View): H

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getContentView(), parent, false)
        return getViewHolder(viewBinding.root)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    protected fun getViewBinding() = viewBinding

}