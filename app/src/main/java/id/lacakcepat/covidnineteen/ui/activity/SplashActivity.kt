package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import org.jetbrains.anko.intentFor
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    @set:Inject
    lateinit var sharedPref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(timerTask {
            when {
                /*sharedPref.getValueBoolean("GETSTARTED", false) -> {
                    startActivity(intentFor<MainActivity>())
                }*/
                sharedPref.getValueBoolean("ONBOARDING", false) -> {
                    startActivity(intentFor<GetStartedActivity>())
                }
                else -> {
                    startActivity(intentFor<OnBoardingPageActivity>())
                }
            }
            finish()
        }, 2000)
    }
}