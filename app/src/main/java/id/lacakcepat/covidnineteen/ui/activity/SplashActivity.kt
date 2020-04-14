package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import id.lacakcepat.covidnineteen.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private companion object {
        const val LOADING_TIME = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(LOADING_TIME) {

            startActivity<OnBoardingActivity>()
            finish()

        }

    }

}