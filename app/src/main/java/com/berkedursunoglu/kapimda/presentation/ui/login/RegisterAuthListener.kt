package com.berkedursunoglu.kapimda.presentation.ui.login

import com.google.firebase.auth.AuthResult

interface RegisterAuthListener{
    fun onRegisterSuccess(result:AuthResult)
    fun onRegisterFailure(result:Exception)
}