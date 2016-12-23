package xyz.egor_d.sweethome.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.egor_d.sweethome.R
import xyz.egor_d.sweethome.bindView
import xyz.egor_d.sweethome.model.NewsItem
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    val data = ArrayList<NewsItem>()

    fun setData(data: List<NewsItem>?) {
        this.data.clear()
        if (data != null) {
            this.data += data
        }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = NewsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.title?.text = data[position].title
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView by bindView<TextView>(R.id.item_news_title)
}
