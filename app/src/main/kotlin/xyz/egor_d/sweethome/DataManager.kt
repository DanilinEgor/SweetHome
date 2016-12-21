package xyz.egor_d.sweethome

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager
@Inject constructor() {
    @Inject
    lateinit var api: Api

    fun loadNews(): Observable<List<NewsItem>> {
        return api.articles("techcrunch")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    response ->
                    response.articles?.map(Article::title)?.map(::NewsItem)
                }
    }
}

