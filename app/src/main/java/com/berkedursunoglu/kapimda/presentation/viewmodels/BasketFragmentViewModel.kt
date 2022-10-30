package com.berkedursunoglu.kapimda.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkedursunoglu.kapimda.data.models.BasketModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BasketFragmentViewModel :ViewModel() {

    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid = firebaseAuth.uid

    var basketList = MutableLiveData<ArrayList<BasketModel>>()

    fun getBasket(){
        firebaseFirestore.collection("users").document(uid.toString()).collection("basket").addSnapshotListener { value, error ->
            var basket = ArrayList<BasketModel> ()
            value?.documents?.forEach {
                it.let {
                    basket.add(BasketModel(it.get("itemcount") as Long
                        ,it.get("itemid") as Long
                        ,it.getString("itemname").toString()
                        ,it.getString("itempic").toString()
                        ,it.getDouble("itemprice")!!
                        ,it.getDouble("itemtotalprice")!!))
                }
            }
            basketList.value = basket
        }
    }
}