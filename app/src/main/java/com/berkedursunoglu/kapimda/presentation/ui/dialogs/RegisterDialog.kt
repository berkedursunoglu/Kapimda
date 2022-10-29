package com.berkedursunoglu.kapimda.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.berkedursunoglu.kapimda.databinding.RegisterDialogBinding

class RegisterDialog : DialogFragment() {



    private lateinit var  binding:RegisterDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)

        binding.btnOkey.setOnClickListener {
            dismiss()
        }
    }
    fun succes(){
        binding.registerProgresbar.visibility = View.GONE
        binding.succesImage.visibility = View.VISIBLE
        binding.tvResult.text = "Başarılı bir şekilde kayıt olundu."
    }

    fun error(error:String){
        binding.registerProgresbar.visibility = View.GONE
        binding.errorImage.visibility = View.VISIBLE
        binding.tvResult.text = error
    }
}