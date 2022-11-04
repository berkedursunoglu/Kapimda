package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.FragmentProfileBinding
import com.berkedursunoglu.kapimda.presentation.ui.dialogs.LogoutDialog
import com.berkedursunoglu.kapimda.presentation.viewmodels.MainPageViewModel
import com.berkedursunoglu.kapimda.presentation.viewmodels.ProfileFragmentViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: ProfileFragmentViewModel
    private lateinit var dialog:LogoutDialog
    private lateinit var sharedViewModel: MainPageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[ProfileFragmentViewModel::class.java]
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        dialog = LogoutDialog()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[MainPageViewModel::class.java]
        viewModel.setField()

        binding.btnExit.setOnClickListener {
            dialog.show(requireActivity().supportFragmentManager, "LogoutDialog")
        }

        viewModel.userEmail.observe(viewLifecycleOwner, Observer {
            binding.tvUserEmail.text = it
        })

        viewModel.userName.observe(viewLifecycleOwner, Observer {
            binding.tvUserName.text = it
        })

        viewModel.exception.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        })

        sharedViewModel.price.observe(viewLifecycleOwner, Observer {
            binding.tvBasketBalanceFromProfile.text = it.toString()
        })

        binding.btnBasketFromProfile.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToBasketFragment()
            it.findNavController().navigate(action)
        }


    }


}