package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.User
import id.lacakcepat.covidnineteen.databinding.FragmentRegisterBinding
import id.lacakcepat.covidnineteen.extension.visible
import id.lacakcepat.covidnineteen.ui.activity.OtpVerificationActivity
import id.lacakcepat.covidnineteen.ui.base.BaseFragment
import id.lacakcepat.covidnineteen.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun getContentView() = R.layout.fragment_register

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getViewBinding().let {
            it.viewModel = viewModel
            it.user = User()
        }

        iv_back.setOnClickListener { findNavController().navigateUp() }

        viewModel.isEnableToRegister.observe(viewLifecycleOwner, Observer {
            btn_register.isEnabled = it
            if (btn_register.isEnabled)
                btn_register.setBackgroundResource(R.drawable.bg_button_primary)
            else {
                btn_register.setBackgroundResource(R.drawable.bg_button_disabled)
                tv_error_phone.text = getString(R.string.phone_error)
            }
        })

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {

            if (it != null)
                when (it.code) {

                    200 -> {
                        longToast("OTP: ${it.otpCode}")

                        val user = User(
                            id = it.userId,
                            phoneNumber = getViewBinding().user?.phoneNumber,
                            token = it.token,
                            otpCode = it.otpCode
                        )
                        startActivity<OtpVerificationActivity>("USER" to user)
                    }

                    502 -> {

                        tv_error_phone.let { tv ->
                            tv.text = getString(R.string.number_already_registered)
                            tv.visible()
                        }

                    }

                }
            else
                toast(getString(R.string.connect_to_server_failed))

        })

    }

}
