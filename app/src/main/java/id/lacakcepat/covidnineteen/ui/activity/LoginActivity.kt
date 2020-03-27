package id.lacakcepat.covidnineteen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.fragment.LoginNumberFragment

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_OPTION = "login_option"
    }

    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

        val option = intent?.getIntExtra(LOGIN_OPTION, 0)
        when(option){
            1 -> {
                val fragment = LoginNumberFragment()
                fragmentTransaction.add(R.id.fragment_container, fragment)
                fragmentTransaction.commit()
            }
        }
    }
}
