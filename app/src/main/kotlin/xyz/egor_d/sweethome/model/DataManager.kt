package xyz.egor_d.sweethome.model

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import xyz.egor_d.sweethome.NewsMVP
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager
@Inject constructor()
    : NewsMVP.Model {
    @Inject
    lateinit var api: Api

    override fun loadNews(): Observable<List<NewsItem>> {
        return api.articles("techcrunch")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    response ->
                    response.articles?.map(Article::title)?.map(::NewsItem)
                }
    }
}

