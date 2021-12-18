package com.rakamin.alodokter.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.Helper
import com.rakamin.alodokter.core.utils.ID_ARTICLE
import com.rakamin.alodokter.databinding.FragmentDetailArticleBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModel()

    private var _binding: FragmentDetailArticleBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idArticle = requireArguments().getInt(ID_ARTICLE)
        showArticle(idArticle)

        binding?.ivBackButton?.setOnClickListener {
            findNavController()
                .navigateUp()
        }
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

                                val timeCreated = data.createdAt?.let { Helper.dateFormatter(it) }
                                val timeUpdated = data.updatedAt?.let { Helper.dateFormatter(it) }
                                binding?.tvArticleUpdated?.text = getString(R.string.article_updated, timeUpdated?.let { Helper.dateToDDMMYYY(it) })
                                binding?.tvArticleWriter?.text = (getString(R.string.article_writer, data.penulis, timeCreated?.let { Helper.dateToDDMMYYY(it) }))

                                binding?.ivArticlePhoto?.let {
                                    Glide.with(requireContext())
                                        .load(data.foto)
                                        .into(it)
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvEmptyStateArticleDesc?.text = getString(R.string.empty_state_article_error_desc)
                        binding?.ivEmptyStateArticle?.visibility = View.VISIBLE
                        binding?.tvEmptyStateArticleDesc?.visibility = View.VISIBLE
                    }
                    is Resource.Loading ->  binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}