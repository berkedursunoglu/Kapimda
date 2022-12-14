package com.berkedursunoglu.kapimda.presentation.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.MainActivity
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingSecondBinding
import com.berkedursunoglu.kapimda.presentation.ui.login.LoginActivity

class OnboardingSecondFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingSecondBinding
    private lateinit var shared:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOnboardingSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnForward.setOnClickListener {
            forwardView(it)
        }

        binding.btnBack.setOnClickListener {
            backwardView(it)
        }
        binding.btnClose.setOnClickListener {
            invisibleOnboardingActivity()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun forwardView(view: View){
        val actions = OnboardingSecondFragmentDirections.actionOnboardingSecondFragmentToOnboardingThirdFragment()
        view.findNavController().navigate(actions)
    }

    private fun backwardView(view: View){
        val actions = OnboardingSecondFragmentDirections.actionOnboardingSecondFragmentToOnboardingFirstFragment()
        view.findNavController().navigate(actions)
    }

    private fun invisibleOnboardingActivity(){
        shared = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        shared.edit().putBoolean("invisibleOnboarding",false).commit()
    }




}