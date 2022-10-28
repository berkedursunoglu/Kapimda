package com.berkedursunoglu.kapimda.presentation.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingFirstBinding
import com.berkedursunoglu.kapimda.databinding.FragmentOnboardingSecondBinding

class OnboardingFirstFragment : Fragment() {

    private lateinit var binding:FragmentOnboardingFirstBinding


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
    }

    private fun forwardView(view :View){
        val actions = OnboardingFirstFragmentDirections.actionOnboardingFirstFragmentToOnboardingSecondFragment()
        view.findNavController().navigate(actions)
    }


}