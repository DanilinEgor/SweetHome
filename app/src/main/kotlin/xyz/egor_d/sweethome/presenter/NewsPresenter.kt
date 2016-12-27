package xyz.egor_d.sweethome.presenter

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter
import rx.Subscription
import timber.log.Timber
import xyz.egor_d.sweethome.NewsMVP
import xyz.egor_d.sweethome.model.DataManager
import javax.inject.Inject

class NewsPresenter
@Inject constructor()
    : MvpNullObjectBasePresenter<NewsMVP.View>(), NewsMVP.Presenter {
    @Inject
    lateinit var model: DataManager

    var sub: Subscription? = null

    override fun loadNews(pullToRefresh: Boolean) {
        view.showLoading(pullToRefresh)
        sub = model.loadNews()
                .subscribe({ news ->
                    view.setData(news)
                    view.showContent()
                }, { error ->
                    Timber.e(error)
                    view.showError(error, pullToRefresh)
                })
    }
}