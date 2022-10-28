package com.berkedursunoglu.kapimda.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.repository.FirebaseModule
import com.berkedursunoglu.kapimda.databinding.FragmentLoginBinding
import com.berkedursunoglu.kapimda.databinding.FragmentRegisterBinding
import com.berkedursunoglu.kapimda.presentation.ui.mainpage.MainPage


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var firebase:FirebaseModule



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        firebase = FirebaseModule()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnpasswordVisibility.setOnClickListener {
            passwordVisibility()
        }
    }


    fun login(){
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            firebase.login(email,password).addOnSuccessListener {
                Toast.makeText(requireContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), MainPage::class.java))
                requireActivity().finish()
            }.addOnFailureListener {
                Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun passwordVisibility(){
        if(binding.etPassword.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT
            binding.btnpasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }else{
            binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.btnpasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
    }
}