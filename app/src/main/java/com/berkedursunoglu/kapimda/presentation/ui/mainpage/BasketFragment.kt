package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.databinding.FragmentBasketBinding
import com.berkedursunoglu.kapimda.presentation.adapter.BasketFragmentAdapter
import com.berkedursunoglu.kapimda.presentation.viewmodels.BasketFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class BasketFragment : Fragment() {

    private lateinit var binding:FragmentBasketBinding
    private val viewModel: BasketFragmentViewModel by viewModels()
    private val adapter = BasketFragmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var totalPrice = 0.0
        viewModel.getBasket()
        viewModel.basketList.observe(viewLifecycleOwner, Observer {
            adapter.addItems(it)
            binding.basketRecyclerView.adapter = adapter
            it.forEach {
                totalPrice += it.itemtotalprice
            }
            binding.tvTotalPrice.text = totalPrice.toString()
        })
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("BasketFragment", "onDetach")
        var viewContainer:FragmentContainerView = requireActivity().findViewById(R.id.fragmentContainerViewBasket)
        viewContainer.visibility = View.GONE
        var viewTabLayout:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        viewTabLayout.visibility = View.VISIBLE
    }

}