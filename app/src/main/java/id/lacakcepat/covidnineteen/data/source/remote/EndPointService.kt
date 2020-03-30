package id.lacakcepat.covidnineteen.data.source.remote

import dagger.Module
import id.lacakcepat.covidnineteen.data.source.remote.model.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

@Module
interface EndPointService {
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("fullname") fullName: String,
        @Field("phone_number") phoneNumber: String
    ) : RegisterResponse
}