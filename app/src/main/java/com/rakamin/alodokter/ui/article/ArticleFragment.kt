package com.rakamin.alodokter.ui.article

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.databinding.FragmentArticleBinding
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import com.rakamin.alodokter.ui.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModel()
    private val articleAdapter = ArticleAdapter()
    private val searchAdapter = SearchAdapter()

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
        articleSearch()
    }

    private fun articleSearch() {
        val searchManager  = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val svArticle = binding?.svArticle

        svArticle?.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        svArticle?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                svArticle.clearFocus()
                if (query != null){
                    search(query)
                }
                else {
                    Toast.makeText(requireContext(),"Empty Search!",Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


    }

    private fun search(query: String){
        viewModel.articleSearch(query).observe(viewLifecycleOwner,{ searchResult ->
            if (searchResult != null){
                when(searchResult){
                    is Resource.Success -> {
                        val result = searchResult.data
                        articleAdapter.setArticle(result)
                        articleAdapter.notifyDataSetChanged()
                        searchResult()
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT).show()
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
                Log.e("TAG", "onQueryTextSubmit: ${searchResult}")
                Log.e("TAG", "onQueryTextSubmit: ${searchResult.data}")

            }
        })
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
                Log.e("TAG", "tes: ${article}")
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

    private fun searchResult(){
        with(binding?.rvArticle) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = articleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }


}