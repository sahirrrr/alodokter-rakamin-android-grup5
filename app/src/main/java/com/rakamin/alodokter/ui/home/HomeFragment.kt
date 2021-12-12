package com.rakamin.alodokter.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.EXTRA_DATA
import com.rakamin.alodokter.databinding.FragmentHomeBinding
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val viewModel : HomeViewModel by viewModel()
    private var binding : FragmentHomeBinding? = null
    private val articleAdapter = ArticleAdapter()
    private var root : View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        root = binding?.root
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idUser = sessionRepository.getIdUser()
        if (idUser == 0) idUser = requireArguments().getInt(EXTRA_DATA)

        binding?.tvName?.text = idUser.toString()
        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            activity?.finishAndRemoveTask()
        }

        showArticleList()
        showRvArticle()
        binding?.let { it.tvSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)


        } }
    }

    private fun showArticleList() {
        viewModel.getArticle().observe(viewLifecycleOwner,{ article ->
            if (article != null){
                when(article){
                    is Resource.Success -> {
                        val articles = article.data
                        articleAdapter.setArticle(articles)
                        binding?.let { it.progressBar.visibility = View.GONE }


                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Loading -> {
                        binding?.let { it.progressBar.visibility = View.VISIBLE }

                    }
                }

            }
        })



    }

    private fun showRvArticle(){
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