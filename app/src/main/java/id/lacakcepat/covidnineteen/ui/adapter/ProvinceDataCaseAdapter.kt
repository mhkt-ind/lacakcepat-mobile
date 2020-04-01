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
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val province = list[position]?.provinceDataItem

        holder.tvProvince.text = "\u2022 "+province?.provinsi.toString()
        holder.tvConfirm.text = "Konfirmasi : "+province?.kasusPosi.toString()
        holder.tvCure.text = "Sembuh : "+ province?.kasusSemb.toString()
        holder.tvDeath.text = "Meninggal : "+province?.kasusMeni.toString()
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