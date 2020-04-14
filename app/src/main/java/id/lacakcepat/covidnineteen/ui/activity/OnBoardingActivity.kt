package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.databinding.ActivityOnBoardingBinding
import id.lacakcepat.covidnineteen.ui.adapter.OnBoardingAdapter
import id.lacakcepat.covidnineteen.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*
import org.jetbrains.anko.startActivity

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {

    private companion object {
        const val ANIMATE_SHOW = 1F
        const val ANIMATE_HIDE = 0F
        const val ANIMATE_TRANSITION = 100F
        const val ANIMATE_DEFAULT_TRANSITION = 0F
        const val ANIMATE_DURATION = 600L
    }

    override fun getContentView() = R.layout.activity_on_boarding

    override fun onCreated(savedInstanceState: Bundle?) {

        if (sharedPreference.getValueBoolean("ON_BOARDING", false))
            gotoGetStartedPage()

        di_on_boarding.setViewPager(vp_on_boarding.apply {
            adapter = OnBoardingAdapter(this@OnBoardingActivity)
        })

        btn_continue.setOnClickListener { vp_on_boarding.currentItem += 1 }

        tv_skip.setOnClickListener {
            sharedPreference.save("ON_BOARDING", true)
            gotoGetStartedPage()
        }

        btn_done.setOnClickListener {
            sharedPreference.save("ON_BOARDING", true)
            gotoGetStartedPage()
        }

        vp_on_boarding.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                when (position) {

                    2 -> {

                        // Button continue go down
                        btn_continue.animate()
                            .alpha(ANIMATE_HIDE)
                            .translationY(ANIMATE_TRANSITION)
                            .setDuration(ANIMATE_DURATION)
                            .start()

                        // Button done go up
                        btn_done.animate()
                            .alpha(ANIMATE_SHOW)
                            .translationY(ANIMATE_DEFAULT_TRANSITION)
                            .setDuration(ANIMATE_DURATION)
                            .setStartDelay(300)
                            .start()

                    }

                    else -> {

                        // Button done go down
                        btn_done.animate()
                            .alpha(ANIMATE_HIDE)
                            .translationY(ANIMATE_TRANSITION)
                            .setDuration(ANIMATE_DURATION)
                            .start()

                        // Button continue go up
                        btn_continue.animate()
                            .alpha(ANIMATE_SHOW)
                            .translationY(ANIMATE_DEFAULT_TRANSITION)
                            .setDuration(ANIMATE_DURATION)
                            .setStartDelay(300)
                            .start()

                    }

                }

            }

        })

    }

    private fun gotoGetStartedPage() {
        startActivity<AuthActivity>()
        finish()
    }

}
