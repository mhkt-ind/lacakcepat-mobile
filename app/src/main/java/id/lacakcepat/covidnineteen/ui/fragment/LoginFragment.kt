package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.repository.Result
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import id.lacakcepat.covidnineteen.utilities.afterTextChanged
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class LoginFragment : Fragment() {

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @set:Inject
    lateinit var sharedPref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(LoginViewModel::class.java) }
        viewModel?.formState?.observe(this, Observer {
            lanjut_button.isEnabled = it.isDataValid

            if(it.isDataValid) {
                lanjut_button.setBackgroundResource(R.drawable.pill_button)
            } else {
                lanjut_button.setBackgroundResource(R.drawable.pill_button_disable)
            }

            if (it.phoneError != null) {
                phoneInput.error = getString(it.phoneError)
                //phoneError.text = getString(it.phoneError)
            }
        })

        viewModel?.loginData?.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    if(it.data?.code == 200) {
                        sharedPref.save("OTP", it.data.otpCode)
                        sharedPref.save("TOKEN", it.data.token)
                        sharedPref.save("ISLOGIN", true)
                        sharedPref.save("PHONENUMBER", phoneInput.text.toString())
                        viewModel.fragmentSate.postValue(2)
                        viewModel.loginData.postValue(Result.Empty("Cleared"))
                    } else {
                        lanjut_button.isEnabled = true
                        lanjut_button.setBackgroundResource(R.drawable.pill_button)
                        toast(it.data?.message.toString())
                    }
                }
                is Result.Error -> {
                    lanjut_button.isEnabled = true
                    lanjut_button.setBackgroundResource(R.drawable.pill_button)
                    toast(it.exception.toString())
                }
            }
        })

        phoneInput.afterTextChanged {
            viewModel?.loginDataChanged(
                phoneInput.text.toString()
            )
        }

        lanjut_button.setOnClickListener {
            lanjut_button.isEnabled = false
            lanjut_button.setBackgroundResource(R.drawable.pill_button_disable)
            viewModel?.loginUser(phoneInput.text.toString())
        }

        tidak_button.setOnClickListener {
            activity?.finish()
        }
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}
