package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.lacakcepat.covidnineteen.R
import org.jetbrains.anko.intentFor
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(timerTask {
            startActivity(intentFor<OnBoardingPageActivity>())
            finish()
        }, 2000)
    }
}