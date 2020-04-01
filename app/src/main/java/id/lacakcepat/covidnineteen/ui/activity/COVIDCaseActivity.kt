package id.lacakcepat.covidnineteen.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaAppRepository
import id.lacakcepat.covidnineteen.ui.adapter.ProvinceDataCaseAdapter
import id.lacakcepat.covidnineteen.utilities.RetrofitClientInstance
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import kotlinx.android.synthetic.main.activity_covidcase.*
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import retrofit2.Retrofit

class COVIDCaseActivity : AppCompatActivity() {

    private var repository = KawalCoronaAppRepository()
    private var viewModel = CovidCaseViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covidcase)

        rv_case_province.layoutManager = GridLayoutManager(this, 2)


        refreshData()
        viewModel.provinceCase.observe(this, Observer {
            rv_case_province.adapter = ProvinceDataCaseAdapter(it)
        })
        viewModel.countryCase.observe(this, Observer {
            tv_case_confirm.text = it?.positif + " Kasus terkonfirmasi"
            tv_case_pdp.text = (it?.positif!!.replace(",", "").toInt() - it.meninggal!!.replace(
                ",",
                ""
            ).toInt() - it.sembuh!!.replace(",", "").toInt()).toString()
            tv_case_cure.text = it.sembuh
            tv_case_death.text = it.meninggal
        })
    }


    fun refreshData() {
        viewModel.getCountry()
        viewModel.getProvince()
    }

}
