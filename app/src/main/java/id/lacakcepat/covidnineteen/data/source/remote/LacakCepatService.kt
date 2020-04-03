package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.ConditionsResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.LoginResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

@Module
interface LacakCepatService {
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("fullname") fullName: String,
        @Field("phone_number") phoneNumber: String
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("rest/login")
    suspend fun loginUser(
        @Field("phone_number") phoneNumber: String
    ) : LoginResponse

    @FormUrlEncoded
    @POST("api/conditions")
    suspend fun sendConditions(
        @Field("health_condition") health: String,
        @Field("id_user") userId: String
    ) : ConditionsResponse
}