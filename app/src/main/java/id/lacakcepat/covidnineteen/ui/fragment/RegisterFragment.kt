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
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class RegisterFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(LoginViewModel::class.java) }
        viewModel?.formState?.observe(this, Observer {
            daftar_button.isEnabled = it.isDataValid

            if(it.isDataValid) {
                daftar_button.setBackgroundResource(R.drawable.pill_button)
            } else {
                daftar_button.setBackgroundResource(R.drawable.pill_button_disable)
            }

            if (it.nameError != null) {
                namaInput.error = getString(it.nameError)
                //namaError.text = getString(it.nameError)
            }
            if (it.phoneError != null) {
                phoneInput.error = getString(it.phoneError)
                //phoneError.text = getString(it.phoneError)
            }
        })

        viewModel?.registerData?.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    if(it.data?.code == 200) {
                        sharedPref.save("OTP", it.data.otpCode)
                        sharedPref.save("PHONENUMBER", phoneInput.text.toString())
                        viewModel.fragmentSate.postValue(2)
                        viewModel.registerData.postValue(Result.Empty("Cleared"))
                    } else {
                        daftar_button.isEnabled = true
                        daftar_button.setBackgroundResource(R.drawable.pill_button)
                        toast(it.data?.message.toString())
                    }
                }
                is Result.Error -> {
                    daftar_button.isEnabled = true
                    daftar_button.setBackgroundResource(R.drawable.pill_button)
                    toast(it.exception.toString())
                }
            }
        })

        namaInput.afterTextChanged {
            viewModel?.registerDataChanged(
                namaInput.text.toString(),
                phoneInput.text.toString()
            )
        }

        phoneInput.afterTextChanged {
            viewModel?.registerDataChanged(
                namaInput.text.toString(),
                phoneInput.text.toString()
            )
        }

        daftar_button.setOnClickListener {
            daftar_button.isEnabled = false
            daftar_button.setBackgroundResource(R.drawable.pill_button_disable)
            viewModel?.registerUser(namaInput.text.toString(), phoneInput.text.toString())
        }
    }

    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }

}
