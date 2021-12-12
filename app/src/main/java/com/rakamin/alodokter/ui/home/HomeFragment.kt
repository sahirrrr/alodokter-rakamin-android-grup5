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
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.session.SessionRepository
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModel()
    private val sessionRepository: SessionRepository by inject()
    private val articleAdapter = ArticleAdapter()

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root

        val idUser = if (arguments != null) { requireArguments().getInt(EXTRA_DATA)
        } else sessionRepository.getIdUser()

        binding?.tvName?.text = idUser.toString()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            activity?.finishAndRemoveTask()
        }

        showArticleList()

        binding?.tvSeeMore?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
        }
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}