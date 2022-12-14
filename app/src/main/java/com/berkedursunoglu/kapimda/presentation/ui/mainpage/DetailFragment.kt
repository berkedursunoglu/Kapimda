package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.databinding.FragmentDetailBinding
import com.berkedursunoglu.kapimda.presentation.viewmodels.DetailFragmentViewModel
import com.berkedursunoglu.kapimda.presentation.viewmodels.MainPageViewModel
import com.berkedursunoglu.kapimda.utils.Extensions.getImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding
    private var itemCount = 1
    private val viewModel: DetailFragmentViewModel by viewModels()
    private lateinit var sharedViewModel:MainPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[MainPageViewModel::class.java]
        binding.tvProductSize.text = "1"
        var item = getJson()
        setField(item)

        binding.btnAddSize.setOnClickListener {
            itemCount ++
            binding.tvProductSize.text = itemCount.toString()
        }
        binding.btnRemoveSize.setOnClickListener {
            if (itemCount != 1) {
                itemCount --
                binding.tvProductSize.text = itemCount.toString()
            }
        }

        binding.btnAddBasket.setOnClickListener {
            var btnText = binding.btnAddBasket.text
            if (btnText.equals("Sepete Ekle")){
                binding.swtchBasket.isChecked = true
                binding.btnAddBasket.text = "Sepetten ????kar"
                Toast.makeText(requireContext(),"Sepete Eklendi.", Toast.LENGTH_SHORT).show()
            }else{
                binding.swtchBasket.isChecked = false
                binding.btnAddBasket.text = "Sepete Ekle"
                Toast.makeText(requireContext(),"Sepetten ????kar??ld??", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swtchBasket.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                val size = itemCount
                viewModel.addBasket(item.title,item.price,item.image,size,item.id)
            }else{
                viewModel.removeBasket(item.id)
            }
        }

        viewModel.exception.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),getString(R.string.error_message),Toast.LENGTH_SHORT).show()
        })

        sharedViewModel.price.observe(viewLifecycleOwner, Observer {
            binding.tvBasketBalanceFromDetail.text = it.toString()
        })

        binding.btnBasketFromDetail.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToBasketFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun getJson() : ProductItem{
        var gson = Gson()
        var string = arguments?.getString("items")
        var item = gson.fromJson(string, ProductItem::class.java)
        return item
    }


    override fun onDestroyView() {
        super.onDestroyView()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.let {
           if (view.visibility == View.GONE){
               view.visibility = View.VISIBLE
           }
        }
    }

    fun setField(item: ProductItem){
        binding.ivProductPics.getImage(item.image,requireContext(),binding.ivProductPics)
        binding.tvProductCategory.text = item.category
        binding.tvProductComments.text = item.rating.count.toString()
        binding.tvProductDesp.text = item.description
        binding.tvProductName.text = item.title
        binding.tvProductRate.text = item.rating.rate.toString()
        binding.tvProductsPrice.text = item.price.toString()
    }


}