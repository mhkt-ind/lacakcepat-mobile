package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider

import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_email_verification.*

class EmailVerificationFragment : Fragment() {

    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel?.fragmentSate?.postValue(5)
        }

        callback.isEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tidak_button.setOnClickListener {
            viewModel?.fragmentSate?.postValue(5)
        }
    }

    companion object {
        fun newInstance(): EmailVerificationFragment = EmailVerificationFragment()
    }
}
