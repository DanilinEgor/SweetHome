package xyz.egor_d.sweethome

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment
import javax.inject.Inject

class NewsFragment : MvpLceFragment<RecyclerView, List<NewsItem>, NewsView, NewsPresenter>(), NewsView {
    @Inject
    lateinit var newsPresenter: NewsPresenter

    private var adapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.layoutManager = LinearLayoutManager(activity)
        contentView.adapter = adapter
        loadData(false)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadNews();
    }

    override fun setData(data: List<NewsItem>?) {
        adapter.setData(data)
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean) = e?.message

    override fun createPresenter() = newsPresenter
}
