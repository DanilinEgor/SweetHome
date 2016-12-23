package xyz.egor_d.sweethome

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView
import rx.Observable
import xyz.egor_d.sweethome.model.NewsItem

interface NewsMVP {
    interface View : MvpLceView<List<NewsItem>>

    interface Presenter : MvpPresenter<View> {
        fun loadNews(pullToRefresh: Boolean)
    }

    interface Model {
        fun loadNews(): Observable<List<NewsItem>>
    }
}