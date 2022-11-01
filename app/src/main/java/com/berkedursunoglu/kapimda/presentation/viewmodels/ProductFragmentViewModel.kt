package com.berkedursunoglu.kapimda.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kapimda.data.models.Product
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductFragmentViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {


    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Handler", throwable.localizedMessage)
    }



    val getProductItem = MutableLiveData<Product>()
    val exception = MutableLiveData<String>()


    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getAllProducts().enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful){
                        response.body().let {
                            getProductItem.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    exception.value = t.localizedMessage
                }
            })
        }
    }
}

