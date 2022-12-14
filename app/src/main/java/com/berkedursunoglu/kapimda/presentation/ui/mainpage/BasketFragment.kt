package com.berkedursunoglu.kapimda.presentation.ui.mainpage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.berkedursunoglu.kapimda.R
import com.berkedursunoglu.kapimda.data.models.BasketModel
import com.berkedursunoglu.kapimda.databinding.FragmentBasketBinding
import com.berkedursunoglu.kapimda.presentation.adapter.BasketFragmentAdapter
import com.berkedursunoglu.kapimda.presentation.ui.dialogs.BasketDialog
import com.berkedursunoglu.kapimda.presentation.ui.dialogs.DialogListener
import com.berkedursunoglu.kapimda.presentation.viewmodels.BasketFragmentViewModel
import com.berkedursunoglu.kapimda.utils.BasketItemOnClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class BasketFragment : Fragment()  {

    private lateinit var binding:FragmentBasketBinding
    private val viewModel: BasketFragmentViewModel by viewModels()
    private lateinit var adapter: BasketFragmentAdapter
    private lateinit var dialog:BasketDialog

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
        adapter = BasketFragmentAdapter(object : BasketItemOnClickListener{
            override fun onClick(item: BasketModel, itemCount: Int) {
                viewModel.updateBasket(item,itemCount)
            }

        })

        var totalPrice = 0.0
        viewModel.getBasket()
        viewModel.basketList.observe(viewLifecycleOwner, Observer {
            totalPrice = 0.0
            adapter.addItems(it)
            binding.basketRecyclerView.adapter = adapter
            it.forEach {
                totalPrice += it.itemtotalprice
            }
            binding.tvTotalPrice.text = totalPrice.toString()
        })

        binding.btnLogout.setOnClickListener {
            dialog.show(requireActivity().supportFragmentManager, "BasketDialog")
        }

        dialog = BasketDialog(object : DialogListener{
            override fun dialogOnClick() {
                viewModel.deleteBasket()
                binding.tvTotalPrice.visibility = View.GONE
                binding.textView11.visibility = View.GONE
                Toast.makeText(requireContext(),getString(R.string.succes_order), Toast.LENGTH_SHORT).show()
                val action = BasketFragmentDirections.actionBasketFragmentToProductsFragment("1")
                view.findNavController().navigate(action)
            }

        })

        viewModel.exception.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),getString(R.string.error_message),Toast.LENGTH_SHORT).show()
        })

    }
}