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
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : Fragment() {

    private lateinit var binding:FragmentOnboardingFirstBinding
    private lateinit var shared: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOnboardingFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnForward.setOnClickListener {
            forwardView(it)
        }
        binding.btnClose.setOnClickListener {
            invisibleOnboardingActivity()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun forwardView(view :View){
        val actions = OnboardingFirstFragmentDirections.actionOnboardingFirstFragmentToOnboardingSecondFragment()
        view.findNavController().navigate(actions)
    }

    private fun invisibleOnboardingActivity(){
        shared = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        shared.edit().putBoolean("invisibleOnboarding",false).commit()
    }


}