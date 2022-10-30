package com.berkedursunoglu.kapimda.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragmentViewModel:ViewModel() {

    private val firebase:FirebaseAuth = FirebaseAuth.getInstance()
    private val fireBaseStore:FirebaseFirestore = FirebaseFirestore.getInstance()

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()

    fun setField(){
        viewModelScope.launch(Dispatchers.IO) {
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


}