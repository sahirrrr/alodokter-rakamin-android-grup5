package com.rakamin.alodokter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.databinding.ItemArticleBinding
import com.rakamin.alodokter.domain.model.ArticleModel
import java.util.ArrayList

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val listArticle = ArrayList<ArticleModel>()

    fun setArticle(article: List<ArticleModel>?) {
        if (article == null) return
        this.listArticle.clear()
        this.listArticle.addAll(article)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = listArticle[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            with(binding) {
                tvTag.text = article.judul
                tvTitleArticle.text = article.konten
                Glide.with(itemView.context)
                    .load(R.drawable.ic_article_image)
                    .into(ivArticle)
            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context,"You Clicked ${article.judul}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}