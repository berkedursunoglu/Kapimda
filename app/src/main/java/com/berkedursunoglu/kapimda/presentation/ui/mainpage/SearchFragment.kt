package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.databinding.FragmentSearchBinding
import com.berkedursunoglu.kapimda.presentation.adapter.SearchFragmentAdapter
import com.berkedursunoglu.kapimda.presentation.adapter.SearchSetOnClickListener
import com.berkedursunoglu.kapimda.presentation.viewmodels.SearchFragmentViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private val viewModel: SearchFragmentViewModel by viewModels()
    private lateinit var adapter:SearchFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewSearch.layoutManager = GridLayoutManager(requireContext(),2)
        viewModel.getAllProducts()
        binding.searchView.clearFocus()
        adapter = SearchFragmentAdapter(object : SearchSetOnClickListener{
            override fun goToDetail(item: ProductItem) {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
                val gson = Gson()
                action.arguments.putString("items",gson.toJson(item))
                requireView().findNavController().navigate(action)
            }
        })
        viewModel.getByElectronic()
        viewModel.getByJewelery()
        viewModel.getByMen()
        viewModel.getByWomen()
        viewModel.getProductItem.observe(viewLifecycleOwner, Observer {
            adapter.addItems(it)
            binding.recyclerviewSearch.adapter = adapter
        })


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0.let {
                    adapter.search(it!!)
                    Log.d("Search",p0!!)
                }
                return false
            }
        })

        binding.cbElectronics.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.getByCategoryElectronics.observe(viewLifecycleOwner, Observer {
                    adapter.addItems(it)
                    adapter.counter()

                })
            }else{
                viewModel.getByCategoryElectronics.observe(viewLifecycleOwner, Observer {
                    adapter.removeItems(it)
                    checkBoxListener()
                })
            }
        }

        binding.cbJewelery.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.getByCategoryJewelery.observe(viewLifecycleOwner, Observer {
                    adapter.addItems(it)
                    adapter.counter()
                })
            }else{
                viewModel.getByCategoryJewelery.observe(viewLifecycleOwner, Observer {
                    adapter.removeItems(it)
                    checkBoxListener()
                })
            }
        }

        binding.cbMen.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.getByCategoryMen.observe(viewLifecycleOwner, Observer {
                    adapter.addItems(it)
                    adapter.counter()
                })
            }else{
                viewModel.getByCategoryMen.observe(viewLifecycleOwner, Observer {
                    adapter.removeItems(it)
                    checkBoxListener()
                })
            }
        }

        binding.cbWomen.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.getByCategoryWomen.observe(viewLifecycleOwner, Observer {
                    adapter.addItems(it)
                    adapter.counter()
                })
            }else{
                viewModel.getByCategoryWomen.observe(viewLifecycleOwner, Observer {
                    adapter.removeItems(it)
                    checkBoxListener()
                })
            }
        }
    }

    fun checkBoxListener(){
        if (binding.cbWomen.isChecked.equals(false) && binding.cbMen.isChecked.equals(false) && binding.cbElectronics.isChecked.equals(false) && binding.cbJewelery.isChecked.equals(false)){
            viewModel.getProductItem.observe(viewLifecycleOwner, Observer {
                adapter.reset(it)
            })
        }
    }

}