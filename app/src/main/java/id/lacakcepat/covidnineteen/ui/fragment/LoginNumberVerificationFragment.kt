package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider

import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.activity.MainActivity
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_number_verification.*
import org.jetbrains.anko.support.v4.intentFor

class LoginNumberVerificationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = activity?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }

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
        verifikasi_button.setOnClickListener {
            activity?.startActivity(intentFor<MainActivity>())
        }
    }

    companion object {
        fun newInstance(): LoginNumberVerificationFragment = LoginNumberVerificationFragment()
    }
}
