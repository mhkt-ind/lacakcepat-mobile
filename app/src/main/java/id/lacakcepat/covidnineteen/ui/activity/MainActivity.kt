package id.lacakcepat.covidnineteen.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import id.lacakcepat.covidnineteen.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_condition.view.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv_main.setupWithNavController(Navigation.findNavController(this, R.id.f_main))

        val view = LayoutInflater.from(this).inflate(R.layout.popup_condition, null)
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .show()

        view.apply {
            btn_positive.setOnClickListener { dialog.dismiss() }
            btn_odp.setOnClickListener { dialog.dismiss() }
            btn_healthy.setOnClickListener { dialog.dismiss() }
        }

    }

}

//    private var isPopUp = true
//
//    @set:Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    @set:Inject
//    lateinit var sharedPref: SharedPreference
//
//    private lateinit var viewModel: MainViewModel
//
//    private var fragment: Fragment? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        overridePendingTransition(0, 0)
//        setContentView(R.layout.activity_main)
//
//        nav_view.menu.forEach { it.isEnabled = false }
//
//        val alpha = 100
//        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
//        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
//        colorAnimation.duration = 500
//        colorAnimation.addUpdateListener { animator ->
//            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
//        }
//        colorAnimation.start()
//
//        popup_window_view_with_border.alpha = 0f
//        popup_window_view_with_border.animate().alpha(1f).setDuration(500).setInterpolator(
//            DecelerateInterpolator()
//        ).start()
//
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        viewModel.fragmentSate.observe(this, Observer { id ->
//            replaceFragment(id)
//        })
//
//        if(viewModel.fragmentSate.value == null) {
//            viewModel.fragmentSate.postValue(R.id.beranda)
//        } else {
//            fragment = setFragment(viewModel.fragmentSate.value)
//        }
//
//        positive_button.setOnClickListener {
//            removePopUpHandler()
//            sharedPref.save("CONDITIONS", "POSITIF")
//            viewModel.sendConditions("POSITIF", sharedPref.getValueString("USERID").toString())
//        }
//
//        odp_button.setOnClickListener {
//            removePopUpHandler()
//            sharedPref.save("CONDITIONS", "ODP")
//            viewModel.sendConditions("ODP", sharedPref.getValueString("USERID").toString())
//        }
//
//        sehat_button.setOnClickListener {
//            removePopUpHandler()
//            sharedPref.save("CONDITIONS", "SEHAT")
//            viewModel.sendConditions("SEHAT", sharedPref.getValueString("USERID").toString())
//        }
//
//        nav_view.setOnNavigationItemSelectedListener {
//            item -> run {
//                replaceFragment(item.itemId)
//                return@setOnNavigationItemSelectedListener true
//            }
//        }
//    }
//
//    override fun onBackPressed() {
//        if(isPopUp) {
//            removePopUpHandler()
//        } else {
//            doubleBackHandler()
//        }
//    }
//
//    private var doubleBackToExitPressedOnce = false
//    private fun doubleBackHandler() {
//        if (doubleBackToExitPressedOnce) {
//            finishAffinity()
//            return
//        }
//
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//    }
//
//    private fun removePopUpHandler() {
//        val alpha = 100
//        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
//        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
//        colorAnimation.duration = 500
//        colorAnimation.addUpdateListener { animator ->
//            popup_window_background.setBackgroundColor(
//                animator.animatedValue as Int
//            )
//        }
//
//        popup_window_view_with_border.animate().alpha(0f).setDuration(500).setInterpolator(
//            DecelerateInterpolator()
//        ).start()
//
//        colorAnimation.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
//                isPopUp = false
//                nav_view.menu.forEach { it.isEnabled = true }
//                overridePendingTransition(0, 0)
//                popup_window_background.visibility = View.GONE
//            }
//        })
//        colorAnimation.start()
//    }
//
//    private fun setFragment(id: Int?): Fragment {
//        return when(id){
//            R.id.beranda -> BerandaFragment.newInstance()
//            R.id.riwayat_interaksi -> RiwayatFragment.newInstance()
//            R.id.notifikasi -> NotifikasiFragment.newInstance()
//            R.id.profil -> ProfilFragment.newInstance()
//            else -> BerandaFragment.newInstance()
//        }
//    }
//
//    private fun replaceFragment(id: Int?) {
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        if(fragment != null) {
//            fragmentTransaction.replace(R.id.fragment_container, setFragment(id))
//            fragmentTransaction.commit()
//        } else {
//            fragmentTransaction.add(R.id.fragment_container, setFragment(id))
//            fragmentTransaction.commit()
//            fragment = setFragment(id)
//        }
//    }
