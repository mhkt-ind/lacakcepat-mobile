package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.lacakcepat.covidnineteen.R
import kotlinx.android.synthetic.main.fragment_get_started.*

class GetStartedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_get_started, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_login.setOnClickListener { findNavController().navigate(R.id.action_f_get_started_to_f_login) }
        btn_register.setOnClickListener { findNavController().navigate(R.id.action_f_get_started_to_f_register) }

    }

}