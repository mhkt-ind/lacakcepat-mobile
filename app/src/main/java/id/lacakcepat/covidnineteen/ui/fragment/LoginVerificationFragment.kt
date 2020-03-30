package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.activity.MainActivity
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_number_verification.*
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class LoginVerificationFragment : Fragment() {

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @set:Inject
    lateinit var sharedPref: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        val viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(LoginViewModel::class.java) }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel?.fragmentSate?.postValue(1)
        }

        callback.isEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_number_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textOverview = activity?.getString(R.string.login_number_verification_overview) + sharedPref.getValueString("PHONENUMBER")?.trim()
        overview.text = textOverview

        verifikasi_button.setOnClickListener {
            goToMain()
        }
    }

    private fun goToMain() {
        val viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(LoginViewModel::class.java) }

        if(otp_view.otp?.toInt() == sharedPref.getValueInt("OTP")) {
            if(sharedPref.getValueBoolean("ISLOGEDIN", false)) {
                sharedPref.removeValue("OTP")
                activity?.startActivity(intentFor<MainActivity>().singleTop())
                activity?.finish()
            } else {
                viewModel?.fragmentSate?.postValue(1)
            }
        } else {
            toast(R.string.otp_error)
        }
    }

    companion object {
        fun newInstance(): LoginVerificationFragment = LoginVerificationFragment()
    }
}
