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
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingThirdBinding


class OnboardingThirdFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingThirdBinding
    private lateinit var shared:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOnboardingThirdBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            backwardView(it)
        }
        binding.btnClose.setOnClickListener {
            closeView(it)
        }

        binding.btnStop.setOnClickListener {
            closeView(it)
        }

    }

    private fun backwardView(view: View){
        val actions = OnboardingThirdFragmentDirections.actionOnboardingThirdFragmentToOnboardingSecondFragment()
        view.findNavController().navigate(actions)
    }

    private fun closeView(view: View){
        startActivity(Intent(requireContext(),MainActivity::class.java))
        requireActivity().finish()
        invisibleOnboardingActivity()
    }

    private fun invisibleOnboardingActivity(){
        shared = requireContext().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        shared.edit().putBoolean("invisibleOnboarding",false).commit()
    }



}