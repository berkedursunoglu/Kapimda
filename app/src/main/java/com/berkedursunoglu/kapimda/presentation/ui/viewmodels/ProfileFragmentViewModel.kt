package com.berkedursunoglu.kapimda.presentation.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragmentViewModel:ViewModel() {

    private val firebase:FirebaseAuth = FirebaseAuth.getInstance()
    private val fireBaseStore:FirebaseFirestore = FirebaseFirestore.getInstance()

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()

    fun setField(){
        firebase.currentUser?.let {
            fireBaseStore.collection("users").document(it.uid).get().addOnSuccessListener {
                userName.value = it.getString("username")
                userEmail.value = it.getString("email")
            }.addOnFailureListener {
                userName.value = "null"
                userEmail.value = "null"
            }
        }
    }


}