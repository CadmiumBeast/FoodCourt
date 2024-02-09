package com.example.foodcourt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourt.databinding.PopularItemBinding

class PopularAdapter(private val items: List<String>,private val price:List<String>, private val image: List<Int>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){




    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val prices = price[position]
        holder.bind(item, images, prices)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    class PopularViewHolder(private val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.imageView2

        fun bind(item: String, image: Int, price: String) {
            binding.foodnamepopular.text = item
            binding.pricepopular.text = price
            imageView.setImageResource(image)
        }

    }
}