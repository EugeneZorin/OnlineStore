package com.example.catalog.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.R
import com.example.catalog.databinding.CatalogItemBinding
import com.example.catalog.databinding.FragmentItemBinding

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.ItemHolder>() {
    class ItemHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = CatalogItemBinding.bind(item)
        fun bind() = with(binding){
            testThree
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.catalog_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

}