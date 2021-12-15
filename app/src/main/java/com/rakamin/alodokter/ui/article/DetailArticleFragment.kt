package com.rakamin.alodokter.ui.article

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.ID_ARTICLE
import com.rakamin.alodokter.databinding.FragmentArticleBinding
import com.rakamin.alodokter.databinding.FragmentDetailArticleBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class DetailArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModel()

    private var _binding: FragmentDetailArticleBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idArticle = requireArguments().getInt(ID_ARTICLE)
        showArticle(idArticle)
    }

    private fun showArticle(id: Int) {
        viewModel.getArticleById(id).observe(viewLifecycleOwner, { article ->
            if (article != null) {
                when (article) {
                    is Resource.Success -> {
                        val articles = article.data
                        binding?.progressBar?.visibility = View.GONE
                        if (articles != null) {
                            for (data in articles) {
                                binding?.tvArticleTitle?.text = data.judul
                                binding?.tvArticleContent?.text = data.konten
                            }
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Fetch Article Failed", Toast.LENGTH_SHORT)
                            .show()
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}