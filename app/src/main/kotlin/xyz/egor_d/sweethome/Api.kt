package xyz.egor_d.sweethome

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

interface Api {
    @Headers("X-Api-Key: " + BuildConfig.API_KEY)
    @GET("/v1/articles")
    fun articles(@Query("source") source: String): Observable<NewsResponse>
}

class NewsResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("sortBy")
    @Expose
    var sortBy: String? = null
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
}

class Article {
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null
}