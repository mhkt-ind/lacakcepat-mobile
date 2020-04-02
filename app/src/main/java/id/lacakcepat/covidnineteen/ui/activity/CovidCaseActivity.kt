package id.lacakcepat.covidnineteen.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import id.lacakcepat.covidnineteen.data.source.repository.KawalCoronaAppRepository
import id.lacakcepat.covidnineteen.ui.adapter.ProvinceDataCaseAdapter
import id.lacakcepat.covidnineteen.utilities.setVisible
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import kotlinx.android.synthetic.main.activity_covidcase.*
import org.jetbrains.anko.configuration


class CovidCaseActivity : AppCompatActivity() {

    private var repository = KawalCoronaAppRepository()
    private var viewModel = CovidCaseViewModel(repository)

    private val adapter = ProvinceDataCaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covidcase)

        rv_case_province.adapter = adapter
        checkConfiguration(configuration)
        refreshData()

        searchView_Case.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                closeKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.equals("")){
                    view_caseTop.setVisible(true)
                    refreshData()
                }else{
                    view_caseTop.setVisible(false)
                    val list = viewModel.getFilterProvince(newText)!!
                    adapter.setData(list)
                    emptyList(list)
                }
                return true
            }
        })

        //Province
        viewModel.provinceCase.observe(this, Observer {
            adapter.setData(it)
            emptyList(it)
            showLoading(false, 1)
        })

        //Indonesia COVID-19 Case
        viewModel.countryCase.observe(this, Observer {
            val textConfirm = it?.positif.toString() + getString(R.string.case_confirmed)
            tv_case_confirm.text = textConfirm
            tv_case_pdp.text = (it?.positif!!.replace(",", "").toInt() - it.meninggal!!.replace(
                ",",
                ""
            ).toInt() - it.sembuh!!.replace(",", "").toInt()).toString()
            tv_case_cure.text = it.sembuh
            tv_case_death.text = it.meninggal
            showLoading(false, 0)
        })
    }


    private fun emptyList(list:List<ProvinceDataCase?>){
        val state = list.count()==0
        rv_case_province.setVisible(!state)
        view_case_oops.setVisible(state)
    }

    private fun refreshData() {
        showLoading(true, 0)
        showLoading(true, 1)
        viewModel.getCountry()
        viewModel.getProvince()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        checkConfiguration(newConfig)
    }
    private fun checkConfiguration(newConfig: Configuration) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rv_case_province.layoutManager = GridLayoutManager(this, 4)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_case_province.layoutManager = GridLayoutManager(this, 2)
        }
    }

    private fun showLoading(state: Boolean, type: Int) {
        if (type == 0) {
            view_case_data.setVisible(!state)
            pb_case_data.setVisible(state)
        } else {
            rv_case_province.setVisible(!state)
            pb_case_dataProvince.setVisible(state)
        }
    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
