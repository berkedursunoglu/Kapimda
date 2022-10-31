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


class SearchFragmentAdapter(var listener: SearchSetOnClickListener) : RecyclerView.Adapter<SearchViewHolder>() {

    private var items = ArrayList<ProductItem>()
    private lateinit var binding: ProductAdapterRawBinding
    private var counter = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ProductAdapterRawBinding.inflate(inflater,parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position],holder.itemView.context)
        holder.itemView.setOnClickListener {
            listener.goToDetail(items[position])
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items:ArrayList<ProductItem>){
        if (counter == 0 ){
            this.items.clear()
        }
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun counter(){
        counter++
    }

    fun removeItems(items:ArrayList<ProductItem>){
        this.items.removeAll(items)
        notifyDataSetChanged()
    }

    fun reset(items:ArrayList<ProductItem>){
        counter = 0
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class SearchViewHolder(val binding: ProductAdapterRawBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item:ProductItem,context: Context){
        binding.tvProductName.text = item.title
        binding.tvProductsPrice.text = item.price.toString()
        binding.ivProductPics.getImage(item.image,context,binding.ivProductPics)
    }
}

interface SearchSetOnClickListener{
    fun goToDetail(item:ProductItem)
}