package com.rakamin.alodokter.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.utils.ID_ARTICLE
import com.rakamin.alodokter.databinding.ItemArticleBinding
import com.rakamin.alodokter.domain.model.ArticleModel
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
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

    override fun getItemCount(): Int = listArticle.size

    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            with(binding) {
                tvTag.text = article.kategori
                tvTitleArticle.text = article.judul
                Glide.with(itemView.context)
                    .load(article.foto)
                    .into(ivArticle)
            }
            itemView.setOnClickListener { view ->
                val mBundle = Bundle()
                article.id?.let { mBundle.putInt(ID_ARTICLE, it) }
                view.findNavController().navigate(R.id.action_articleFragment_to_articleDetailFragment, mBundle)
            }

        }
    }
}

