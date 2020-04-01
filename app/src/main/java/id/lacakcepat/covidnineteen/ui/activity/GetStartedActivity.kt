package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.ui.activity.LoginActivity.Companion.LOGIN_OPTION
import kotlinx.android.synthetic.main.activity_get_started.*
import org.jetbrains.anko.intentFor

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        masuk_button.setOnClickListener {
            startActivity(intentFor<LoginActivity>(LOGIN_OPTION to 1))
        }

        daftar_button.setOnClickListener {
            startActivity(intentFor<LoginActivity>(LOGIN_OPTION to 5))
        }

        flags.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}
