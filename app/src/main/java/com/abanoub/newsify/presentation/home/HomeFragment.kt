package com.abanoub.newsify.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abanoub.newsify.databinding.FragmentHomeBinding
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ArticlesAdapter.OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var articlesAdapter: ArticlesAdapter

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeForNewsResponse()
        observeForSearchResponse()

        setupAdapter()
        setupRecyclerView()
        setupSearchView()

    }

    private fun observeForNewsResponse() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collect { resource ->
                    when (resource) {
                        is Resource.Empty -> Unit
                        is Resource.Error -> showErrorSnackBar(resource.message.toString())
                        is Resource.Loading -> isProgressBarVisible(true)
                        is Resource.Success -> submitNewsList(resource.data)
                    }
                }
            }
        }
    }

    private fun observeForSearchResponse() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.search.collect { resource ->
                    when (resource) {
                        is Resource.Empty -> Unit
                        is Resource.Error -> showErrorSnackBar(resource.message.toString())
                        is Resource.Loading -> isProgressBarVisible(true)
                        is Resource.Success -> submitNewsList(resource.data)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        articlesAdapter = ArticlesAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.rvArticles.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }
    }

    private fun showErrorSnackBar(message: String) {
        hideProgressBar()
        Snackbar.make(requireContext(), binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun isProgressBarVisible(isVisible: Boolean) {
        binding.progressBar.root.isVisible = isVisible
    }

    private fun hideProgressBar() {
        isProgressBarVisible(false)
    }

    private fun submitNewsList(news: List<Article>) {
        hideProgressBar()
        articlesAdapter.submitList(news)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    if (query!!.isEmpty() || query.isBlank()) {
                        // Empty Query, Clear Focus
                        binding.searchView.clearFocus()
                    } else {
                        // Query is Not Empty, Perform Search Query and Clear Focus
                        viewModel.searchNews(query)
                        binding.searchView.clearFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    if (newText!!.isNotEmpty() || newText.isNotBlank()) {
                        // Query is Not Empty, Perform Search Query
                        viewModel.searchNews(newText)
                    }
                }
                return true
            }
        })
    }

    override fun onArticleClick(article: Article) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}