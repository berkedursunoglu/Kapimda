package com.berkedursunoglu.kapimda.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kapimda.data.models.BasketModel
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.databinding.BasketAdapterRawBinding
import com.berkedursunoglu.kapimda.databinding.ProductAdapterRawBinding
import com.berkedursunoglu.kapimda.utils.Extensions.getImage

class BasketFragmentAdapter() : RecyclerView.Adapter<BasketViewHolder>() {

    private var items = ArrayList<BasketModel>()
    private lateinit var binding: BasketAdapterRawBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = BasketAdapterRawBinding.inflate(inflater,parent,false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(items[position],holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items:ArrayList<BasketModel>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

class BasketViewHolder(val binding: BasketAdapterRawBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BasketModel, context: Context){
        binding.tvProductName.text = item.itemname
        binding.tvProductSize.text = item.itemPrice.toString()
        binding.imageView.getImage(item.itemPic,context,binding.imageView)
        binding.tvProductCount.text = item.itemcount.toString()
    }
}