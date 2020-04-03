package id.lacakcepat.covidnineteen.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import id.lacakcepat.covidnineteen.data.source.repository.Result
import id.lacakcepat.covidnineteen.ui.adapter.ProvinceDataCaseAdapter
import id.lacakcepat.covidnineteen.utilities.setVisible
import id.lacakcepat.covidnineteen.viewmodel.CovidCaseViewModel
import kotlinx.android.synthetic.main.activity_covidcase.*
import org.jetbrains.anko.configuration
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject

class CovidCaseActivity : AppCompatActivity() {

    private lateinit var listProvince: List<ProvinceDataCase?>
    private val adapter = ProvinceDataCaseAdapter()

    private lateinit var viewModel: CovidCaseViewModel

    @set:Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covidcase)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CovidCaseViewModel::class.java)

        rv_case_province.adapter = adapter
        checkConfiguration(configuration)
        refreshData()

        searchView_Case.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                closeKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    view_caseTop.setVisible(true)
                    refreshData()
                } else {
                    view_caseTop.setVisible(false)
                    val list = getFilterProvince(newText, listProvince)!!
                    adapter.setData(list)
                    emptyList(list)
                }
                return true
            }
        })

        viewModel.provinceCase.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    listProvince = it.data
                    adapter.setData(it.data)
                    emptyList(it.data)
                    showLoading(false, 1)
                }
                is Result.Error -> {
                    toast(it.exception.toString())
                }
            }
        })
        viewModel.countryCase.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    val textConfirm =
                        it.data[0]?.positif.toString() + getString(R.string.case_confirmed)
                    tv_case_confirm.text = textConfirm
                    tv_case_pdp.text = (it.data[0]?.positif!!.replace(",", "").toInt() - it.data[0]?.meninggal!!.replace(
                        ",",
                        ""
                    ).toInt() - it.data[0]?.sembuh!!.replace(",", "").toInt()).toString()
                    tv_case_cure.text = it.data[0]?.sembuh
                    tv_case_death.text = it.data[0]?.meninggal
                    showLoading(false, 0)
                }
                is Result.Error -> {
                    toast(it.exception.toString())
                }
            }
        })

        viewModel.getCountry()
        viewModel.getProvince()
    }


    private fun emptyList(list: List<ProvinceDataCase?>) {
        val state = list.count() == 0
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


    fun getFilterProvince(
        query: String?,
        provinceCase: List<ProvinceDataCase?>?
    ): List<ProvinceDataCase?>? {
        return provinceCase?.filter {
            it?.provinceDataItem?.provinsi!!.toLowerCase(Locale.getDefault()).contains(query!!)
        }
    }

}
