package com.abanoub.newsify.presentation.article

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.abanoub.newsify.databinding.FragmentArticleBinding
import com.abanoub.newsify.domain.model.Article
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val args: ArticleFragmentArgs by navArgs()

    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()

        binding.fabAddToFavourite.setOnClickListener {
            onAddFavouriteClick(args.article)
        }

    }

    private fun onAddFavouriteClick(article: Article) {
        viewModel.addToFavourite(article)
        showSnackBar("Article Added To Favourites!")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.wvArticle.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    showProgressBar()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideProgressBar()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    hideProgressBar()
                    showErrorSnackBar(error.toString())
                }
            }

            settings.apply {
                javaScriptEnabled = true
            }

            loadUrl(args.article.url.toString())

        }
    }

    private fun showErrorSnackBar(message: String) {
        hideProgressBar()
        Snackbar.make(requireContext(), binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(requireContext(), binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        binding.progressBar.root.isVisible = true
    }

    private fun hideProgressBar() {
        binding.progressBar.root.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}