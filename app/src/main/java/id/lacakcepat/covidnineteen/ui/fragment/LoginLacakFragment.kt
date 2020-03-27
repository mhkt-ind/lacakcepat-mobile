package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.activity.MainActivity
import id.lacakcepat.covidnineteen.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_lacak.*
import org.jetbrains.anko.support.v4.intentFor

class LoginLacakFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_lacak, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = activity?.let { ViewModelProvider(it).get(LoginViewModel::class.java) }

        tidak_button.setOnClickListener {
            activity?.finish()
        }

        masuk_button.setOnClickListener {
            activity?.startActivity(intentFor<MainActivity>())
        }

        daftar_button.setOnClickListener {
            viewModel?.fragmentSate?.postValue(5)
        }
    }
    
    companion object {
        fun newInstance(): LoginLacakFragment = LoginLacakFragment()
    }
}
