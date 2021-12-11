package com.rakamin.alodokter.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentHomeBinding
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val viewModel : HomeViewModel by viewModel()
    private lateinit var binding : FragmentHomeBinding
    private val articleAdapter = ArticleAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showArticleList()
        showRvArticle()

    }

    private fun showArticleList() {
        viewModel.getArticle().observe(viewLifecycleOwner,{ article ->
            if (article != null){
                when(article){
                    is Resource.Success -> {
                        val articles = article.data
                        articleAdapter.setArticle(articles)
                        binding.progressBar.visibility = View.GONE


                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE

                    }
                }

            }
        })



    }

    private fun showRvArticle(){
        with(binding.rvArticle){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articleAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}