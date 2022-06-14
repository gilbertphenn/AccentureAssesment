package gilbert.assessment.gilbertaccentureassessment.io

import gilbert.assessment.gilbertaccentureassessment.BuildConfig
import gilbert.assessment.gilbertaccentureassessment.utils.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestClient @Inject constructor() {
    private val okClient = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
            return loggingInterceptor
        }

    init {
        okClient.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val method = original.method
                val body = original.body

                val request = original.newBuilder()
                    .header("Accept", "application/vnd.github.v3+json")
                    .header(
                        "Authorization", Config.ACCESS_TOKEN
                    )
                    .method(method, body)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(logger)

        retrofit = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(AdapterFactory())
            .build()

    }


    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}