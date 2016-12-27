package xyz.egor_d.sweethome.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import xyz.egor_d.sweethome.*
import xyz.egor_d.sweethome.model.NewsItem
import xyz.egor_d.sweethome.presenter.NewsPresenter
import java.util.*
import javax.inject.Inject

class NewsFragment : MvpLceViewStateFragment<SwipeRefreshLayout, List<NewsItem>, NewsMVP.View, NewsMVP.Presenter>(), SwipeRefreshLayout.OnRefreshListener, NewsMVP.View {
    @Inject
    lateinit var newsPresenter: NewsPresenter
    val recyclerView: RecyclerView by bindView<RecyclerView>(R.id.recycler_view)

    private var adapter: NewsAdapter = NewsAdapter()

    init {
        isRetainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.setOnRefreshListener(this);

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun showContent() {
        super.showContent()
        contentView.isRefreshing = false
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        contentView.isRefreshing = false
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadNews(pullToRefresh);
    }

    override fun setData(data: List<NewsItem>?) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading(pullToRefresh: Boolean) {
        super.showLoading(pullToRefresh)
        contentView.isRefreshing = pullToRefresh
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.reset(this)
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun getData() = ArrayList(adapter.data)

    override fun createViewState(): LceViewState<List<NewsItem>, NewsMVP.View> {
        return RetainingLceViewState<List<NewsItem>, NewsMVP.View>()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean) = e?.message

    override fun createPresenter() = newsPresenter
}
