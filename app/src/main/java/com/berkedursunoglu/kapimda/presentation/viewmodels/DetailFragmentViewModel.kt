package com.berkedursunoglu.kapimda.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashSet

class DetailFragmentViewModel:ViewModel() {

    private val firebaseFirestore:FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    val uid = firebaseAuth.uid


    fun addBasket(itemName:String,itemPrice:Double,itemPic:String,itemCount:Int,itemId:Int){
        viewModelScope.launch(Dispatchers.IO){
            val basketItem = HashMap<String,Any>()
            basketItem.put("itemname",itemName)
            basketItem.put("itemprice",itemPrice)
            basketItem.put("itempic",itemPic)
            basketItem.put("itemcount",itemCount)
            basketItem.put("itemid",itemId)
            basketItem.put("itemtotalprice",itemPrice*itemCount)

            firebaseFirestore.collection("users").document(uid.toString()).collection("basket").document(itemId.toString()).set(basketItem).addOnFailureListener {

            }.addOnFailureListener {

            }
        }

    }

    fun removeBasket(itemId:Int){
        viewModelScope.launch(Dispatchers.IO){
            val file = firebaseFirestore.collection("users").document(uid.toString()).collection("basket").document(itemId.toString())
            file.delete().addOnFailureListener {

            }.addOnFailureListener {

            }
        }
    }






}