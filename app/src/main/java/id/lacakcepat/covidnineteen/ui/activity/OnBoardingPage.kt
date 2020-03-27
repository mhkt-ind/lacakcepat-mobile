package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.adapter.OnBoardingPageAdapter
import id.lacakcepat.covidnineteen.ui.fragment.OnBoardingPageFragment
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.intentFor

class OnBoardingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val listFragment = mutableListOf(
            OnBoardingPageFragment.newInstance(resources.getString(R.string.on_boarding1_title), resources.getString(R.string.on_boarding1_overview), R.drawable.ic_on_boarding_1),
            OnBoardingPageFragment.newInstance(resources.getString(R.string.on_boarding2_title), resources.getString(R.string.on_boarding2_overview), R.drawable.ic_on_boarding_2),
            OnBoardingPageFragment.newInstance(resources.getString(R.string.on_boarding3_title), resources.getString(R.string.on_boarding3_overview), R.drawable.ic_on_boarding_3)
        )

        val pagerAdapter =
            OnBoardingPageAdapter(
                supportFragmentManager,
                listFragment
            )
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)

        next_btn.setOnClickListener {
            startActivity(intentFor<GetStartedActivity>())
        }

        skip_btn.setOnClickListener {
            startActivity(intentFor<GetStartedActivity>())
        }
    }
}
