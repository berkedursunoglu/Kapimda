package com.berkedursunoglu.kapimda.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.FragmentLoginBinding
import com.berkedursunoglu.kapimda.presentation.ui.mainpage.MainPage
import com.berkedursunoglu.kapimda.presentation.ui.viewmodels.LoginListener
import com.berkedursunoglu.kapimda.presentation.ui.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var viewModel:LoginViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater, container, false)
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
            viewModel.login(email,password,object  : LoginListener{
                override fun success() {
                    Toast.makeText(requireContext(),"Giriş Başarılı.",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(),MainPage::class.java))
                    requireActivity().finish()
                }
                override fun error(message: String) {
                    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
                }
            })
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