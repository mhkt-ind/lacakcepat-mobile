package id.lacakcepat.covidnineteen.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.repository.Result
import id.lacakcepat.covidnineteen.ui.adapter.ProvinceDataCaseAdapter
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import kotlinx.android.synthetic.main.activity_covidcase.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class CovidCaseActivity : AppCompatActivity() {

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covidcase)

        rv_case_province.layoutManager = GridLayoutManager(this, 2)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(CovidCaseViewModel::class.java)
        viewModel.provinceCase.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    rv_case_province.adapter = ProvinceDataCaseAdapter(it.data)
                }
                is Result.Error -> {
                    toast(it.exception.toString())
                }
            }
        })
        viewModel.countryCase.observe(this, Observer {
            when(it) {
                is Result.Success -> {
                    tv_case_confirm.text = it.data[0]?.positif + " Kasus terkonfirmasi"
                    tv_case_pdp.text = (it.data[0]?.positif!!.replace(",", "").toInt() - it.data[0]?.meninggal!!.replace(
                        ",",
                        ""
                    ).toInt() - it.data[0]?.sembuh!!.replace(",", "").toInt()).toString()
                    tv_case_cure.text = it.data[0]?.sembuh
                    tv_case_death.text = it.data[0]?.meninggal
                }
                is Result.Error -> {
                    toast(it.exception.toString())
                }
            }
        })

        viewModel.getCountry()
        viewModel.getProvince()
    }
}
