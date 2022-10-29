package com.berkedursunoglu.kapimda.presentation.ui.login

interface RegisterFireStoreListener {
    fun onSuccess()
    fun onFailure(result:Exception)
}