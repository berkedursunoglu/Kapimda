package com.berkedursunoglu.kapimda.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPageViewModel:ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFireStore = FirebaseFirestore.getInstance()


    var price = MutableLiveData<Double>()


    fun getTotalPrice(){
        viewModelScope.launch(Dispatchers.IO){
            val uid = firebaseAuth.uid
            firebaseFireStore.collection("users").document(uid.toString()).collection("basket").addSnapshotListener { value, error ->
                var totalprice = 0.0
                value?.documents?.forEach {
                    it.let {
                        totalprice += it.getDouble("itemtotalprice")!!
                    }
                }
                price.value = totalprice
            }
        }
    }


}