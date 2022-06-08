package com.saddam.challengebinar5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.saddam.challengebinar5.R
import com.saddam.challengebinar5.databinding.FragmentProfileBinding
import com.saddam.challengebinar5.helper.Preferences

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            Preferences().clearLoggedInUser(requireContext())
            findNavController().navigate(
                R.id.action_profileFragment_to_loginFragment,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}