package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.adapter.OnBoardingPageAdapter
import id.lacakcepat.covidnineteen.ui.fragment.OnBoardingPageFragment
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class OnBoardingPageActivity : AppCompatActivity() {

    private var fragmentState: Int = 0

    @set:Inject
    lateinit var sharedPref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
            if(fragmentState == listFragment.size - 1) {
                sharedPref.save("ONBOARDING", true)
                startActivity(intentFor<GetStartedActivity>())
                finish()
            } else {
                view_pager.currentItem = fragmentState + 1
            }
        }

        skip_btn.setOnClickListener {
            sharedPref.save("ONBOARDING", true)
            startActivity(intentFor<GetStartedActivity>())
            finish()
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("OnBoardingPage", "onPageScrollStateChanged: $state")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d("OnBoardingPage", "onPageScrolled: $position")
                fragmentState = position

                if(fragmentState == listFragment.size - 1) {
                    next_btn.setImageResource(R.drawable.ic_check)
                } else {
                    next_btn.setImageResource(R.drawable.ic_next)
                }
            }

            override fun onPageSelected(position: Int) {
                Log.d("OnBoardingPage", "onPageSelected: $position")
            }
        })
    }
}
