package com.saddam.challengebinar5.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.saddam.challengebinar5.R
import com.saddam.challengebinar5.adapter.MovieAdapter
import com.saddam.challengebinar5.databinding.FragmentHomeBinding
import com.saddam.challengebinar5.helper.Preferences
import com.saddam.challengebinar5.remote.response.Movie
import com.saddam.challengebinar5.viewmodel.MovieViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel by viewModels<MovieViewModel>()
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.tvUsername.text = "Welcome, ${args.username}!"
        binding.tvUsername.text = "Welcome, ${Preferences().getLoggedInUser(requireContext())}!"
        movieViewModel.movie.observe(viewLifecycleOwner) { setMovieData(it) }
        movieViewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
        binding.ibProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieData(movie: List<Movie>) {
        binding.apply {
            val movieAdapter = MovieAdapter(movie)
            rvMovies.setHasFixedSize(true)
            rvMovies.layoutManager = LinearLayoutManager(activity)
            rvMovies.adapter = movieAdapter
            movieAdapter.setOnItemClickCallback(object :
                MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            data
                        )
                    )
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}