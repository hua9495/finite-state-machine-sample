package com.alexchan.finite_state_machine.ui

import android.content.res.Resources
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexchan.finite_state_machine.R
import com.alexchan.finite_state_machine.databinding.ItemPhotoBinding
import com.alexchan.finite_state_machine.model.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    val displayMetrics: DisplayMetrics by lazy {
        Resources.getSystem().displayMetrics
    }

    var items: List<Photo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var binding: ItemPhotoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        binding = ItemPhotoBinding.bind(layoutInflater)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Photo) = with(binding) {
            cardView.setCardBackgroundColor(Color.parseColor(item.color))
            val ratio = (item.width.toFloat() / item.height.toFloat())
            imageView.layoutParams.height =
                (displayMetrics.widthPixels / ratio / 2).toInt()
            imageView.layoutParams.width = displayMetrics.widthPixels / 2
            Glide.with(itemView.context)
                .load(item.urls?.regular)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)
        }
    }
}
