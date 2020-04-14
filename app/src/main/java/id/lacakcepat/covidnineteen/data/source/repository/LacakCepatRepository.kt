package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.LacakCepatService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.LoginResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LacakCepatRepository @Inject constructor(@Named("LacakCepat") private val service: LacakCepatService) {

    suspend fun registerUser(map: MutableMap<String, Any?>): Result<RegisterResponse> =
        try {
            Result.Success(service.registerUser(map).body())
        } catch (e: Throwable) {
            Result.Error(e)
        }

    suspend fun loginUser(phoneNumber: String?): Result<LoginResponse> =
        try {
            Result.Success(service.loginUser(phoneNumber).body())
        } catch (e: Throwable) {
            Result.Error(e)
        }

//    suspend fun sendConditions(
//        health: String,
//        userId: String
//    ): Result<ConditionsResponse?> {
//        return try {
//            Result.Success(
//                service.sendConditions(
//                    health,
//                    userId
//                )
//            )
//        } catch (e: Exception) {
//            Result.Error(e)
//        }
//    }
}