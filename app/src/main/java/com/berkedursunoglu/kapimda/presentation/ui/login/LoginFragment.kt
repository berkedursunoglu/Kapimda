package com.berkedursunoglu.kapimda.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.FragmentLoginBinding
import com.berkedursunoglu.kapimda.presentation.ui.mainpage.MainPage
import com.berkedursunoglu.kapimda.presentation.viewmodels.LoginListener
import com.berkedursunoglu.kapimda.presentation.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var viewModel: LoginViewModel



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
        email = binding.etEmail.text?.trim().toString()
        password = binding.etPassword.text?.trim().toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            viewModel.login(email,password,object  : LoginListener {
                override fun success() {
                    Toast.makeText(requireContext(),"Giriş Başarılı.",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(),MainPage::class.java))
                    requireActivity().finish()
                }
                override fun error(message: String) {
                     errorToasFlow(message)
                }
            })
        }
    }

    fun errorToasFlow(message: String){
        if(message.equals("There is no user record corresponding to this identifier. The user may have been deleted.")) {
            Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        }else if (message.equals(getString(R.string.error_user_en))){
            Toast.makeText(requireContext(),getString(R.string.error_user_tr),Toast.LENGTH_SHORT).show()
        }else if (message.equals(getString(R.string.error_eposta_en))) {
            Toast.makeText(requireContext(), getString(R.string.error_eposta_tr), Toast.LENGTH_SHORT).show()
        }else if (message.equals(getString(R.string.error_password_en))) {
            Toast.makeText(requireContext(), getString(R.string.error_password_tr), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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