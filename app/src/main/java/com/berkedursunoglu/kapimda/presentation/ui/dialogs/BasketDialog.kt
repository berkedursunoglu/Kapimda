package com.berkedursunoglu.kapimda.presentation.ui.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.berkedursunoglu.kapimda.databinding.BasketDialogBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BasketDialog(var listener: DialogListener) : DialogFragment() {

    private lateinit var binding: BasketDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
        binding.btnLogout.setOnClickListener {
            listener.dialogOnClick()
            dismiss()
        }
        binding.btnDecline.setOnClickListener {
            dismiss()
        }
    }
}

interface DialogListener{
    fun dialogOnClick()
}