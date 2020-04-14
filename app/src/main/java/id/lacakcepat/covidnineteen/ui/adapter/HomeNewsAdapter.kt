package id.lacakcepat.covidnineteen.ui.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.News
import id.lacakcepat.covidnineteen.databinding.AdapterNewsBinding
import id.lacakcepat.covidnineteen.ui.base.BaseAdapter

class HomeNewsAdapter(context: Context?, private val listNews: List<News>) :
    BaseAdapter<AdapterNewsBinding, HomeNewsAdapter.ViewHolder, News>(context, listNews) {

    override fun getContentView() = R.layout.adapter_news

    override fun getViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNews(listNews[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindNews(news: News) {
            getViewBinding().news = news
        }

    }

}