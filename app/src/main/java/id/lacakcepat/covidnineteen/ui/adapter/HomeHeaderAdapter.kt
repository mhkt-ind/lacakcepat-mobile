package id.lacakcepat.covidnineteen.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.HomeHeader
import id.lacakcepat.covidnineteen.databinding.AdapterHomeHeaderBinding
import id.lacakcepat.covidnineteen.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.adapter_home_header.view.*

class HomeHeaderAdapter(context: Context?, private val listHeader: List<HomeHeader>) :
    BaseAdapter<AdapterHomeHeaderBinding, HomeHeaderAdapter.ViewHolder, HomeHeader>(
        context,
        listHeader
    ) {

    override fun getContentView() = R.layout.adapter_home_header

    override fun getViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.let {
            it.bindHeader(listHeader[position])
            it.setBackground(position)
        }
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindHeader(homeHeader: HomeHeader?) {
            view.iv_header.setImageResource(homeHeader?.icon ?: R.drawable.ic_about_covid)
            getViewBinding().homeHeader = homeHeader
        }

        fun setBackground(position: Int) {
            view.apply {
                when (position) {

                    0 -> v_background.setBackgroundColor(
                        ActivityCompat.getColor(
                            context,
                            R.color.blueColor
                        )
                    )

                    1 -> v_background.setBackgroundColor(
                        ActivityCompat.getColor(
                            context,
                            R.color.greenColor
                        )
                    )

                    2 -> v_background.setBackgroundColor(
                        ActivityCompat.getColor(
                            context,
                            R.color.redColor
                        )
                    )

                }
            }
        }

    }

}