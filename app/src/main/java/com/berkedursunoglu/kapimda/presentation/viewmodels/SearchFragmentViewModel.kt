package com.berkedursunoglu.kapimda.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kapimda.data.models.Product
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
class SearchFragmentViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Handler", throwable.localizedMessage)
    }

    val getProductItem = MutableLiveData<Product>()
    val getByCategoryJewelery = MutableLiveData<Product>()
    val getByCategoryMen = MutableLiveData<Product>()
    val getByCategoryWomen = MutableLiveData<Product>()
    val getByCategoryElectronics = MutableLiveData<Product>()
    val exception = MutableLiveData<String>()

    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getAllProducts().enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful) {
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

    fun getByElectronic() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getByCategory("electronics").enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    response.let {
                        getByCategoryElectronics.value = it.body()
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    exception.value = t.localizedMessage
                }
            })
        }
    }
    fun getByWomen() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getByCategory("women's clothing").enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    response.let {
                        getByCategoryWomen.value = it.body()
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    exception.value = t.localizedMessage
                }
            })
        }
    }
    fun getByMen() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getByCategory("men's clothing").enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    response.let {
                        getByCategoryMen.value = it.body()
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    exception.value = t.localizedMessage
                }
            })
        }
    }
    fun getByJewelery() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepository.getByCategory("jewelery").enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    response.let {
                        getByCategoryJewelery.value = it.body()
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    exception.value = t.localizedMessage
                }
            })
        }
    }
}

