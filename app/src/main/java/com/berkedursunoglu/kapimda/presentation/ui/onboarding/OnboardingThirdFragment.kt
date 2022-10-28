package com.berkedursunoglu.kapimda.presentation.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingThirdBinding


class OnboardingThirdFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingThirdBinding

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
    }

    private fun backwardView(view: View){
        val actions = OnboardingThirdFragmentDirections.actionOnboardingThirdFragmentToOnboardingSecondFragment()
        view.findNavController().navigate(actions)
    }

}