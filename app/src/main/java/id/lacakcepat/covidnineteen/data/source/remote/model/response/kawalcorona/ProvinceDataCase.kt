package id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona

import com.google.gson.annotations.SerializedName

data class ProvinceDataCase(

	@field:SerializedName("attributes")
	val provinceDataItem: ProvinceDataItem? = null
)