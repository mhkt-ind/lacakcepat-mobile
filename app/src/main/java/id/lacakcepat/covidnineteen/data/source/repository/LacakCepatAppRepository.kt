package id.lacakcepat.covidnineteen.data.source.repository

import id.lacakcepat.covidnineteen.data.source.remote.EndPointService
import id.lacakcepat.covidnineteen.data.source.remote.model.response.LoginResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.RegisterResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LacakCepatAppRepository @Inject constructor(private val service: EndPointService) {
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