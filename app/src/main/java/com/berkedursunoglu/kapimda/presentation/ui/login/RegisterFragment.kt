package com.berkedursunoglu.kapimda.presentation.ui.login

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.repository.FirebaseModule
import com.berkedursunoglu.kapimda.databinding.FragmentRegisterBinding
import com.berkedursunoglu.kapimda.presentation.ui.viewmodel.RegisterFragmentViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterFragmentViewModel
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordConfirmation: String
    private lateinit var username: String
    private lateinit var firebase: FirebaseModule
    private val dialog: RegisterDialog = RegisterDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterFragmentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnpasswordVisibility.setOnClickListener {
            passwordVisibility()
        }

        binding.btnpasswordConfirmVisibility.setOnClickListener {
            passwordConfirmVisibility()
        }

        binding.btnRegister.setOnClickListener {
            email = binding.etEmail.text.toString()
            username = binding.etUserName.text.toString()
            password = binding.etPassword.text.toString()
            passwordConfirmation = binding.etPasswordAgain.text.toString()

            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()) {
                if (password == passwordConfirmation) {
                    dialog.show(requireActivity().supportFragmentManager, "RegisterDialog")
                    firebase = FirebaseModule()
                    firebase.register(email, password).addOnSuccessListener {
                        it.let {
                            if (it.user != null) {
                                firebase.registerWithUsername(username,
                                    it.user!!.uid,
                                    email,
                                    password).addOnSuccessListener {
                                    dialog.succes()
                                    navigate()
                                }
                            }
                        }


                    }.addOnFailureListener {

                        it.localizedMessage?.let { it1 -> dialog.error(it1) }

                    }
                } else {
                    Toast.makeText(requireContext(), getString(R.string.passwords_field_not_equal), Toast.LENGTH_LONG)
                        .show()
                }
            } else {

                Toast.makeText(requireContext(), getString(R.string.is_not_empty_fields), Toast.LENGTH_LONG)
                    .show()

            }
        }
    }

    private fun navigate() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        this@RegisterFragment.findNavController().navigate(action)
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

    private fun passwordConfirmVisibility(){
        if(binding.etPasswordAgain.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            binding.etPasswordAgain.inputType = InputType.TYPE_CLASS_TEXT
            binding.btnpasswordConfirmVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }else{
            binding.etPasswordAgain.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.btnpasswordConfirmVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
    }
}