package xyz.egor_d.sweethome

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.Subscription
import timber.log.Timber
import javax.inject.Inject

class NewsPresenter
@Inject constructor()
    : MvpBasePresenter<NewsView>() {
    @Inject
    lateinit var dataManager: DataManager

    var sub: Subscription? = null

    fun loadNews() {
        view?.showLoading(false)
        sub = dataManager.loadNews()
                .subscribe({ news ->
                    view?.setData(news)
                    view?.showContent()
                }, { error ->
                    Timber.e(error)
                    view?.showError(error, false)
                })
    }

    override fun detachView(retainInstance: Boolean) {
        sub?.unsubscribe()
        super.detachView(retainInstance)
    }
}