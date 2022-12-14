package com.berkedursunoglu.kapimda.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kapimda.presentation.ui.login.RegisterAuthListener
import com.berkedursunoglu.kapimda.presentation.ui.login.RegisterFireStoreListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragmentViewModel :ViewModel() {

   private lateinit var firebaseAuth: FirebaseAuth
   private lateinit var firebaseStore:FirebaseFirestore

   init {
       firebaseAuth = FirebaseAuth.getInstance()
       firebaseStore = FirebaseFirestore.getInstance()
   }

   fun register(email: String, password: String,registerListener: RegisterAuthListener){
       viewModelScope.launch(Dispatchers.IO) {
           firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
               registerListener.onRegisterSuccess(it)
           }.addOnFailureListener {
               registerListener.onRegisterFailure(it)
           }
       }
   }

    fun registerWithuserName(email:String,password: String,userName:String,userUid:String,registerListener: RegisterFireStoreListener){
        viewModelScope.launch(Dispatchers.IO) {
            val user = HashMap<String,Any>()
            user.put("email",email)
            user.put("password",password)
            user.put("username",userName)
            user.put("useruid",userUid)

            firebaseStore.collection("users").document(userUid).set(user).addOnSuccessListener {
                it.let {
                    registerListener.onSuccess()
                }

            }.addOnFailureListener {
                it.let {
                    registerListener.onFailure(it)
                }
            }
        }
    }
}

