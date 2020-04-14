package id.lacakcepat.covidnineteen.di.module

import dagger.Module
import dagger.Provides
import id.lacakcepat.covidnineteen.BuildConfig
import id.lacakcepat.covidnineteen.data.source.remote.KawalCoronaService
import id.lacakcepat.covidnineteen.data.source.remote.LacakCepatService
import id.lacakcepat.covidnineteen.data.source.remote.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @Named("LacakCepat")
    fun provideLacakCepatInterceptor(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .addHeader("secret-key", BuildConfig.SECRET_KEY)
                        .build()
                )
            }
            .build()

    @Provides
    @Singleton
    @Named("KawalCorona")
    fun provideKawalCoronaInterceptor(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    @Named("News")
    fun provideNewsInterceptor(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .addHeader("X-API-KEY", BuildConfig.NEWS_KEY)
                        .build()
                )
            }
            .build()

    @Provides
    @Singleton
    fun provideLacakCepat(@Named("LacakCepat") client: OkHttpClient): LacakCepatService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.LACAK_CEPAT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LacakCepatService::class.java)

    @Provides
    @Singleton
    fun provideKawalCorona(@Named("KawalCorona") client: OkHttpClient): KawalCoronaService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.KAWAL_CORONA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(KawalCoronaService::class.java)

    @Provides
    @Singleton
    fun provideNews(@Named("News") client: OkHttpClient): NewsService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsService::class.java)

}