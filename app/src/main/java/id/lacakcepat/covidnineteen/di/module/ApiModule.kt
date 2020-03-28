package id.lacakcepat.covidnineteen.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.BuildConfig
import id.lacakcepat.covidnineteen.data.source.remote.EndPointService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("secret-key", BuildConfig.SECRET_KEY)
                it.proceed(requestBuilder.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideEndPointService(retrofit: Retrofit): EndPointService {
        return retrofit.create(EndPointService::class.java)
    }
}