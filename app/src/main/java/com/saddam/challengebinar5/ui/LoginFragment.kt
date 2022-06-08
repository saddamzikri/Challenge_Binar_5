package com.saddam.challengebinar5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saddam.challengebinar5.R
import com.saddam.challengebinar5.database.UserRoomDatabase
import com.saddam.challengebinar5.databinding.FragmentLoginBinding
import com.saddam.challengebinar5.helper.Preferences
import com.saddam.challengebinar5.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Preferences().getLoggedInStatus(requireContext())) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.apply {
            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(), "Field cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    //Perform Query
                    val userRoomDatabase = UserRoomDatabase.getDatabase(requireContext())
                    val userDao = userRoomDatabase.userDao()
                    val user = userDao.checkUser(email, password)
                    if (user == null) {
                        Toast.makeText(
                            requireContext(),
                            "Incorrect email or password",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        val username = user.username
                        Preferences().setLoggedInUser(
                            requireContext(),
                            username
                        )
                        Preferences().setLoggedInStatus(requireContext(), true)
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                username
                            )
                        )
                    }
                }
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}