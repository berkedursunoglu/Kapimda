package com.berkedursunoglu.kapimda.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kapimda.data.models.BasketModel
import com.berkedursunoglu.kapimda.databinding.BasketAdapterRawBinding
import com.berkedursunoglu.kapimda.utils.Extensions.getImage

class BasketFragmentAdapter(var listener: BasketItemOnClickListener) : RecyclerView.Adapter<BasketViewHolder>() {

    private var items = ArrayList<BasketModel>()
    private lateinit var binding: BasketAdapterRawBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = BasketAdapterRawBinding.inflate(inflater,parent,false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(items[position],holder.itemView.context)
        var count:Int = Integer.parseInt(binding.tvProductCount.text.toString())

        holder.binding.btnAddSize.setOnClickListener {
            count ++
            binding.tvProductCount.text = count.toString()

        }

        holder.binding.btnRemoveSize.setOnClickListener {
            if (count != 1){
                count --
                binding.tvProductCount.text = count.toString()
            }
        }

        holder.itemView.setOnClickListener {
            val basketModel = BasketModel(count.toLong(),items[position].itemid,items[position].itemname,items[position].itemPic,items[position].itemPrice,items[position].itemtotalprice)
            listener.onClick(basketModel,count)
        }
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

interface BasketItemOnClickListener{
    fun onClick(item: BasketModel, itemCount: Int)
}