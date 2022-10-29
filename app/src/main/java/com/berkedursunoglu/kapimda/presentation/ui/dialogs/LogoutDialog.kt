package com.berkedursunoglu.kapimda.presentation.ui.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.berkedursunoglu.kapimda.databinding.LogoutDialogBinding
import com.berkedursunoglu.kapimda.presentation.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class LogoutDialog : DialogFragment() {

    private lateinit var binding:LogoutDialogBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LogoutDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireContext(),LoginActivity::class.java))
            requireActivity().finish()
            dismiss()
        }

        binding.btnDecline.setOnClickListener {
            dismiss()
        }
    }
}