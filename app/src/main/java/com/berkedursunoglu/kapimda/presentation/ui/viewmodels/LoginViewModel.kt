package com.berkedursunoglu.kapimda.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {

    private var firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()


    fun login(email:String, password:String,loginListener: LoginListener){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            loginListener.success()
        }.addOnFailureListener {
            it.localizedMessage?.let { it1 -> loginListener.error(it1) }
        }
    }
}

interface LoginListener{
    fun success()
    fun error(message:String)
}