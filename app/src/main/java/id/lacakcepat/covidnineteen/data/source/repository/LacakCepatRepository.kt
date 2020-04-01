package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.LacakCepatService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.LoginResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class LacakCepatRepository @Inject constructor(@Named("LacakCepat") private val service: LacakCepatService) {

    suspend fun loginUser(phoneNumber: String): Result<LoginResponse?> {
        return try {
            Result.Success(
                service.loginUser(
                    phoneNumber
                )
            )
        } catch(e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun registerUser(
        fullName: String,
        phoneNumber: String
    ): Result<RegisterResponse?> {
        return try {
            Result.Success(
                service.registerUser(
                    fullName,
                    phoneNumber
                )
            )
        } catch(e: Exception) {
            Result.Error(e)
        }
    }
}