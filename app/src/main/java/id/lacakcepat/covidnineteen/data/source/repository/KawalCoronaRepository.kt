package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.kawalcorona.CountryCaseResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class KawalCoronaRepository @Inject constructor(@Named("KawalCorona") private val service: KawalCoronaService) {

    suspend fun getCountryCase(country: String?): Result<List<CountryCaseResponse>> =
        try {
            Result.Success(service.getCountryDataCase(country).body())
        } catch (e: Throwable) {
            Result.Error(e)
        }
//
//    suspend fun getProvince(country: String, province: String): Result<List<ProvinceDataCase?>> {
//        return try {
//            Result.Success(
//                service.getProvinceDataCase(
//                    country,
//                    province
//                )
//            )
//        } catch(e: Exception) {
//            Result.Error(e)
//        }
//    }
//
//    suspend fun getGlobal(): Result<List<GlobalDataCaseItem?>> {
//        return try {
//            Result.Success(
//                service.getGlobalDataCase()
//            )
//        } catch(e: Exception) {
//            Result.Error(e)
//        }
//    }
}