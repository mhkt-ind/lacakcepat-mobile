package id.lacakcepat.covidnineteen.ui.activity

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.repository.Result
import id.lacakcepat.covidnineteen.ui.adapter.news.NewsAdapterOther
import id.lacakcepat.covidnineteen.ui.adapter.news.NewsAdapterRecent
import id.lacakcepat.covidnineteen.ui.adapter.news.NewsAdapterTrend
import id.lacakcepat.covidnineteen.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        viewModel.getRecentNews()
        viewModel.getHeadlineNews()
        viewModel.getOtherNews()

        val adapterRecent = NewsAdapterRecent{ openUrl(it.url) }
        rv_news_recent.adapter = adapterRecent
        rv_news_recent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.recentNews.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    adapterRecent.setData(result.data)
                }
                is Result.Error -> {
                    toast(result.exception.toString())
                }
            }
        })

        val adapterTrend = NewsAdapterTrend{ openUrl(it.url) }
        rv_news_trend.adapter = adapterTrend
        rv_news_trend.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.trendNews.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    adapterTrend.setData(result.data)
                }
                is Result.Error -> {
                    toast(result.exception.toString())
                }
            }
        })

        val adapterOther = NewsAdapterOther{ openUrl(it.url) }
        rv_news_other.adapter = adapterOther
        rv_news_other.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel.otherNews.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("DEBUG","Jumlah data: "+result.data.count())
                    adapterOther.setData(result.data)
                }
                is Result.Error -> {
                    toast(result.exception.toString())
                }
            }
        })
    }


    private fun openUrl(url: String) {
        var s = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            s = "http://$url"
        val browserIntent = Intent(ACTION_VIEW, Uri.parse(s))
        startActivity(browserIntent)
    }
}
