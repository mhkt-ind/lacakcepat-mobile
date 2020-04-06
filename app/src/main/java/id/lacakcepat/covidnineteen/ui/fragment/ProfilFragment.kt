package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.utilities.SharedPreference
import kotlinx.android.synthetic.main.fragment_profil.*
import javax.inject.Inject

class ProfilFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtUsername.text = sharedPref.getValueString("FULLNAME")
        txtPhoneNumber.text = sharedPref.getValueString("PHONENUMBER")

    }

    companion object {
        fun newInstance(): ProfilFragment = ProfilFragment()
    }

}