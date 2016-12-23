package xyz.egor_d.sweethome

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import xyz.egor_d.sweethome.model.Api
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun api(retrofit: Retrofit) = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun okHttpClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(Logger { mes -> Timber.d(mes) }).setLevel(BODY))
            .build()
}
