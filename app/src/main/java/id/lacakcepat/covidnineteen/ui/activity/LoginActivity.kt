package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.fragment.LoginFragment
import id.lacakcepat.covidnineteen.ui.fragment.LoginVerificationFragment
import id.lacakcepat.covidnineteen.ui.fragment.RegisterFragment
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_OPTION = "login_option"
    }

    private var fragment: Fragment? = null

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @set:Inject
    lateinit var sharedPref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        viewModel.fragmentSate.observe(this, Observer { id ->
            replaceFragment(id)
        })

        if(viewModel.fragmentSate.value == null) {
            viewModel.fragmentSate.postValue(intent?.getIntExtra(LOGIN_OPTION, 0))
        } else {
            fragment = setFragment(viewModel.fragmentSate.value)
        }
    }

    private fun setFragment(id: Int?): Fragment {
        return when(id){
            1 -> LoginFragment.newInstance()
            2 -> LoginVerificationFragment.newInstance()
            5 -> RegisterFragment.newInstance()
            else -> LoginFragment.newInstance()
        }
    }

    private fun replaceFragment(id: Int?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(fragment != null) {
            fragmentTransaction.replace(R.id.fragment_container, setFragment(id))
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.add(R.id.fragment_container, setFragment(id))
            fragmentTransaction.commit()
            fragment = setFragment(id)
        }
    }

    override fun onBackPressed() {
        sharedPref.removeValue("OTP")
        super.onBackPressed()
    }
}
