package id.lacakcepat.covidnineteen.ui.adapter.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.response.newsapi.NewsResponse.Article
import kotlinx.android.synthetic.main.item_news_other.view.*
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapterOther(
    private var list: List<Article> = arrayListOf(),
    val listener: (Article) -> Unit
) :
    RecyclerView.Adapter<NewsAdapterOther.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news_other,
                parent, false
            )
        )
    }

    fun setData(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = list[position]
        holder.setItem(article)
        holder.itemView.setOnClickListener {
            listener(article)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(article: Article) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .override(300,100)
                .centerCrop()
                .into(itemView.iv_news)
            itemView.tv_newsTitle.text = article.title
            itemView.tv_newsDesc.text = article.content
            itemView.tv_newsAuthor.text = article.author
            itemView.tv_newsTime.text = timeParse(article.publishedAt)

        }

        private fun timeParse(publishedAt: String): String {
            print(publishedAt)
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val millis = System.currentTimeMillis() - inputFormat.parse(publishedAt)!!.time
            val mins = millis / (1000 * 60) % 60
            val hours = millis / (1000 * 60 * 60)
            val days = millis / (1000 * 60 * 60 * 24)

            if (days > 0)
                return "$days Hari yang lalu."
            else if (hours > 0)
                return "$hours Jam yang lalu."
            else if (mins > 0)
                return "$mins Menit yang lalu."
            else
                return "Baru saja."
        }

    }
}