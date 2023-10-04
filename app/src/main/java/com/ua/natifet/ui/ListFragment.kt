package com.ua.natifet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ua.natifet.databinding.FragmentListBinding
import com.ua.natifet.ui.adapter.GifAdapter
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var viewModel: GifViewModel
    private lateinit var gifAdapter: GifAdapter
    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GifViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        gifAdapter = GifAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = gifAdapter



        lifecycleScope.launch {
            val gifList = viewModel.fetchGifs(
                apiKey = "kfPZDyt0EdQhAhKzpwzL8RboqfcmjNGb",
                query = "cat",
                limit = 25,
                offset = 0,
                rating = "g",
                lang = "en"
            )
            gifAdapter.submitList(gifList)
        }
        return binding.root
    }
}
