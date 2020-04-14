package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.util.SharedPreference
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (sharedPreference.getValueBoolean("IS_LOGIN", false)) {
            startActivity<MainActivity>()
            finish()
        }

    }
}
