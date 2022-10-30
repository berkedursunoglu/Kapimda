package com.berkedursunoglu.kapimda.data.models

data class BasketModel(
    var itemcount:Long,
    var itemid:Long,
    var itemname:String,
    var itemPic:String,
    var itemPrice:Double,
    var itemtotalprice:Double,
) {
}