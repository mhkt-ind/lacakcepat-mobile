package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.fragment.*
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_OPTION = "login_option"
    }

    private var fragment: Fragment? = null
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val model: LoginViewModel by viewModels()
        model.fragmentSate.observe(this, Observer { id ->
            replaceFragment(id)
        })

        if(model.fragmentSate.value == null) {
            model.fragmentSate.postValue(intent?.getIntExtra(LOGIN_OPTION, 0))
        } else {
            fragment = setFragment(model.fragmentSate.value)
        }
    }

    private fun setFragment(id: Int?): Fragment {
        return when(id){
            1 -> LoginNumberFragment.newInstance()
            2 -> LoginNumberVerificationFragment.newInstance()
            5 -> RegisterLacakFragment.newInstance()
            else -> LoginNumberFragment.newInstance()
        }
    }

    private fun replaceFragment(id: Int?) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        if(fragment != null) {
            fragmentTransaction.replace(R.id.fragment_container, setFragment(id))
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.add(R.id.fragment_container, setFragment(id))
            fragmentTransaction.commit()
            fragment = setFragment(id)
        }
    }
}
