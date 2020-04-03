package id.lacakcepat.covidnineteen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.ProvinceDataCase
import kotlinx.android.synthetic.main.item_case_province.view.*

class ProvinceDataCaseAdapter(private var list: List<ProvinceDataCase?> = arrayListOf()) :
    RecyclerView.Adapter<ProvinceDataCaseAdapter.ProvinceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_case_province,
                parent, false
            )
        )
    }

    fun setData(list: List<ProvinceDataCase?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val province = list[position]?.provinceDataItem

        val provinceText = "\u2022 " + province?.provinsi.toString()
        val confirm = "Konfirmasi : " + province?.kasusPosi.toString()
        val cure = "Sembuh : " + province?.kasusSemb.toString()
        val death = "Meninggal : " + province?.kasusMeni.toString()

        holder.tvProvince.text = provinceText
        holder.tvConfirm.text = confirm
        holder.tvCure.text = cure
        holder.tvDeath.text = death
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProvince: TextView = itemView.tv_case_province
        val tvConfirm: TextView = itemView.tv_case_confirm
        val tvCure: TextView = itemView.tv_case_cure
        val tvDeath: TextView = itemView.tv_case_death
    }
}