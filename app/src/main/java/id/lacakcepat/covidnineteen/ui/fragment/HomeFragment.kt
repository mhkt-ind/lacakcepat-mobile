package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.HomeHeader
import id.lacakcepat.covidnineteen.databinding.FragmentHomeBinding
import id.lacakcepat.covidnineteen.ui.adapter.HomeHeaderAdapter
import id.lacakcepat.covidnineteen.ui.adapter.HomeNewsAdapter
import id.lacakcepat.covidnineteen.ui.base.BaseFragment
import id.lacakcepat.covidnineteen.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getContentView() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val headerTitle = resources.getStringArray(R.array.home_header_title)
        val headerOverview = resources.getStringArray(R.array.home_header_ov)
        val headerImage = resources.obtainTypedArray(R.array.home_header_img)

        val homeHeader = ArrayList<HomeHeader>()

        for (i in headerTitle.indices) {

            homeHeader.add(
                HomeHeader(
                    title = headerTitle[i],
                    overview = headerOverview[i],
                    icon = headerImage.getResourceId(i, 0)
                )
            )

        }
        headerImage.recycle()

        rv_home_header.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeHeaderAdapter(activity, homeHeader)
        }

        viewModel.getCovidCase("indonesia")?.observe(viewLifecycleOwner, Observer {
            getViewBinding().countryCase = it
        })

        rv_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getNews("id")?.observe(viewLifecycleOwner, Observer {
            rv_news.adapter = HomeNewsAdapter(activity, it.articles)
        })

    }

}