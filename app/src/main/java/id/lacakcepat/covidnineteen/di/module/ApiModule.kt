package id.lacakcepat.covidnineteen.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.BuildConfig
import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.remote.LacakCepatService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    @Named("LacakCepat")
    fun provideLacakCepatInterceptor(): OkHttpClient {
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
    @Named("KawalCorona")
    fun provideKawalCoronaInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @Named("LacakCepat")
    fun provideLacakCepat(@Named("LacakCepat")client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LACAK_CEPAT_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("KawalCorona")
    fun provideKawalCorona(@Named("KawalCorona")client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.KAWAL_CORONA_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideLacakCepatService(@Named("LacakCepat")retrofit: Retrofit): LacakCepatService =
        retrofit.create(LacakCepatService::class.java)

    @Provides
    @Singleton
    fun provideKawalCoronaService(@Named("KawalCorona")retrofit: Retrofit): KawalCoronaService =
        retrofit.create(KawalCoronaService::class.java)
}