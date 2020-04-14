package id.lacakcepat.covidnineteen.ui.activity

import `in`.aabhasjindal.otptextview.OTPListener
import android.os.Bundle
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.User
import id.lacakcepat.covidnineteen.databinding.ActivityOtpVerificationBinding
import id.lacakcepat.covidnineteen.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_otp_verification.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class OtpVerificationActivity : BaseActivity<ActivityOtpVerificationBinding>() {

    override fun getContentView() = R.layout.activity_otp_verification

    override fun onCreated(savedInstanceState: Bundle?) {

        val user = intent?.getParcelableExtra<User>("USER")
        getViewBinding().user = user

        otv_otp_code.otpListener = object : OTPListener {

            override fun onInteractionListener() {
                btn_verify.let {
                    it.isEnabled = false
                    it.setBackgroundResource(R.drawable.bg_button_disabled)
                }
            }

            override fun onOTPComplete(otp: String) {
                btn_verify.let {
                    it.isEnabled = true
                    it.setBackgroundResource(R.drawable.bg_button_primary)
                    it.setOnClickListener {

                        if (user?.otpCode == otp.toInt()) {

                            sharedPreference.apply {
                                save("IS_LOGIN", true)
                                save("PHONE", "+62${user.phoneNumber}")
                                save("TOKEN", user.token.toString())
                                save("USER_ID", user.id.toString())
                            }
                            startActivity<MainActivity>()
                            finishAffinity()

                        } else
                            toast(getString(R.string.wrong_otp))

                    }
                }
            }

        }

    }

}
