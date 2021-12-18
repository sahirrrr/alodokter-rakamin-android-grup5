package com.rakamin.alodokter.ui.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.EXTRA_DATA
import com.rakamin.alodokter.core.utils.EXTRA_QUERY
import com.rakamin.alodokter.databinding.FragmentHomeBinding
import com.rakamin.alodokter.session.SessionRepository
import com.rakamin.alodokter.ui.adapter.ArticleAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val articleAdapter = ArticleAdapter()
    private val viewModel : HomeViewModel by viewModel()
    private val sessionRepository: SessionRepository by inject()

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val idUser = sessionRepository.getIdUser()

        showProfile(idUser)
        doctorSearch()
        showArticleList()

        binding?.tvSeeMore?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
        }

        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            activity?.finishAndRemoveTask()
        }
    }

    private fun showProfile(idUser: Int) {
        viewModel.userProfile(idUser.toString()).observe(viewLifecycleOwner,{ showProfile ->
            if (showProfile != null) {
                when(showProfile) {
                    is Resource.Success -> {
                        val dataArray = showProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.tvName?.text = data.nama
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvName?.text = getString(R.string.guest_user)
                        Toast.makeText(requireContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun doctorSearch() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val svListDoctor = binding?.svDoctor

        svListDoctor?.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        svListDoctor?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svListDoctor.clearFocus()
                if (query != null) {
                    val mBundle = Bundle()
                    mBundle.putString(EXTRA_QUERY, query)
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_booking, mBundle)
                } else {
                    Toast.makeText(requireContext(), getString(R.string.empty_search), Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
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
                        binding?.tvEmptyStateArticleDesc?.text = getString(R.string.empty_state_article_error_desc)
                        binding?.ivEmptyStateArticle?.visibility = View.VISIBLE
                        binding?.tvEmptyStateArticleDesc?.visibility = View.VISIBLE
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