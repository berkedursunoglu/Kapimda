package com.berkedursunoglu.kapimda.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkedursunoglu.kapimda.data.models.BasketModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BasketFragmentViewModel() :ViewModel() {


    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid = firebaseAuth.uid
    var basket = ArrayList<BasketModel> ()
    var exception = MutableLiveData<String>()

    var basketList = MutableLiveData<ArrayList<BasketModel>>()

    fun getBasket(){
        firebaseFirestore.collection("users").document(uid.toString()).collection("basket").addSnapshotListener { value, error ->
            basket.clear()
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

    fun updateBasket(model:BasketModel,itemCount:Int){
        val updateBasket = HashMap<String, Any>()
        updateBasket.put("itemname",model.itemname)
        updateBasket.put("itemprice",model.itemPrice)
        updateBasket.put("itempic",model.itemPic)
        updateBasket.put("itemcount",itemCount)
        updateBasket.put("itemid",model.itemid)
        updateBasket.put("itemtotalprice",model.itemPrice*itemCount)

        firebaseFirestore.collection("users").document(uid.toString()).collection("basket").document(model.itemid.toString()).set(updateBasket).addOnFailureListener {
            exception.value = it.localizedMessage
        }
    }

    fun deleteBasket(){
        basket.forEach {
            firebaseFirestore.collection("users").document(uid.toString()).collection("basket").document(it.itemid.toString()).delete().addOnFailureListener {
                exception.value = it.localizedMessage
            }
        }
    }
}