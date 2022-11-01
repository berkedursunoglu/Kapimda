package com.berkedursunoglu.kapimda.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.berkedursunoglu.kapimda.databinding.ProductAdapterRawBinding
import com.berkedursunoglu.kapimda.utils.Extensions.getImage
import com.berkedursunoglu.kapimda.utils.ProductSetOnClickListener

class ProductFragmentAdapter(val listener: ProductSetOnClickListener) : RecyclerView.Adapter<ProductViewHolder>() {

    private var items = ArrayList<ProductItem>()
    private lateinit var binding:ProductAdapterRawBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ProductAdapterRawBinding.inflate(inflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position],holder.itemView.context)
        holder.itemView.setOnClickListener {
            listener.goToDetail(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items:ArrayList<ProductItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


}

class ProductViewHolder(val binding:ProductAdapterRawBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item:ProductItem,context: Context){
        binding.tvProductName.text = item.title
        binding.tvProductsPrice.text = item.price.toString()
        binding.ivProductPics.getImage(item.image,context,binding.ivProductPics)
    }
}


