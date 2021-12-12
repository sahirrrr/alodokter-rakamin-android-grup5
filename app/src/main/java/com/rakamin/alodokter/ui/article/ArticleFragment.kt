package com.rakamin.alodokter.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentArticleBinding
import com.rakamin.alodokter.databinding.FragmentHomeBinding
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ArticleFragment : Fragment() {
    private var binding: FragmentArticleBinding? = null
    private val viewModel: ArticleViewModel by viewModel()
    private val articleAdapter = ArticleAdapter()
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showArticleList()
        showRvArticle()
    }

    private fun showArticleList() {
        viewModel.getArticle().observe(viewLifecycleOwner, { article ->
            if (article != null) {
                when (article) {
                    is Resource.Success -> {
                        val articles = article.data
                        articleAdapter.setArticle(articles)
                        binding?.let { it.progressBar.visibility = View.GONE }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT)
                            .show()

                    }
                    is Resource.Loading -> {
                        binding?.let { it.progressBar.visibility = View.VISIBLE }

                    }
                }

            }
        })


    }

    private fun showRvArticle() {
        binding?.rvArticle?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = articleAdapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        root = null
    }
}