package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.databinding.FragmentProductsBinding
import com.berkedursunoglu.kapimda.presentation.adapter.ProductFragmentAdapter
import com.berkedursunoglu.kapimda.presentation.adapter.ProductSetOnClickListener
import com.berkedursunoglu.kapimda.presentation.viewmodels.ProductFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel:ProductFragmentViewModel by viewModels()
    private lateinit var productAdapter:ProductFragmentAdapter
    private lateinit var binding:FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductFragmentAdapter(object :ProductSetOnClickListener{
            override fun goToDetail(item: ProductItem) {
                val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment()
                val gson = Gson()
                action.arguments.putString("items",gson.toJson(item))
                requireView().findNavController().navigate(action)
                val view:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
                view.let {
                    view.visibility = View.GONE
                }
            }

        })
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(),2)
        viewModel.getAllProducts()
        viewModel.getProductItem.observe(viewLifecycleOwner, Observer{
            productAdapter.addItems(it)
            binding.rvProduct.adapter = productAdapter
        })
    }

}