package id.lacakcepat.covidnineteen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import id.lacakcepat.covidnineteen.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel : NewsViewModel

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

    }
}
