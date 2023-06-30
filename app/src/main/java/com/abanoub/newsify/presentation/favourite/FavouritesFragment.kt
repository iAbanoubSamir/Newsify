package com.abanoub.newsify.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abanoub.newsify.databinding.FragmentFavouritesBinding
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import com.abanoub.newsify.presentation.home.ArticlesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment(), ArticlesAdapter.OnItemClickListener {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by viewModels()

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeForFavourites()

        setupRecyclerView()
    }

    private fun observeForFavourites() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favourites.collect { resource ->
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
        binding.rvFavouriteArticles.apply {
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

    override fun onArticleClick(article: Article) {
        val action = FavouritesFragmentDirections.actionFavouritesFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}