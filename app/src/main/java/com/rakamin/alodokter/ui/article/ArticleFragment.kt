package com.rakamin.alodokter.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentArticleBinding
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModel()
    private val articleAdapter = ArticleAdapter()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showArticleList()
    }

    private fun showArticleList() {
        viewModel.getArticle().observe(viewLifecycleOwner,{ article ->
            if (article != null) {
                when(article) {
                    is Resource.Success -> {
                        val articles = article.data
                        articleAdapter.setArticle(articles)
                        binding?.progressBar?.visibility = View.GONE
                        showRvArticle()
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showRvArticle(){
        with(binding?.rvArticle) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = articleAdapter
        }
        articleAdapter.setOnItemClickCallback(object: ArticleAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleModel) {
                val id = data.id as Int
                val action = ArticleFragmentDirections.actionArticleFragmentToArticleDetailFragment(id)
                findNavController().navigate(action)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}