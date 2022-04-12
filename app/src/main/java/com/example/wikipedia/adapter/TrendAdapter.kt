package com.example.wikipedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikipedia.data.ItemPost
import com.example.wikipedia.databinding.ItemTrendBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class TrendAdapter(private val dataTrend: List<ItemPost>, val itemEvents: ItemEvents) :
    RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {
    lateinit var binding: ItemTrendBinding

    inner class TrendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(itemPost: ItemPost) {

            Glide.with(itemView.context).load(itemPost.imgUrl)
                .transform(RoundedCornersTransformation(32,8))
                .into(binding.imgTrendMain)

            binding.txtTrendTitle.text=itemPost.txtTitle
            binding.txtTrendSubtitle.text=itemPost.txtSubtitle
            binding.txtTrendInsight.text=itemPost.inSight
            binding.txtTrendNumber.text=(adapterPosition+1).toString()

            itemView.setOnClickListener {

                itemEvents.onItemClicked(itemPost)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        binding = ItemTrendBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return TrendViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bindView(dataTrend[position])
    }

    override fun getItemCount(): Int {
        return dataTrend.size
    }
}