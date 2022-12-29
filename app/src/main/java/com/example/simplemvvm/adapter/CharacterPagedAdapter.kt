package com.example.simplemvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.simplemvvm.R
import com.example.simplemvvm.models.ResultCharacter
import com.example.simplemvvm.adapter.CharacterPagedAdapter.MyViewHolder

class CharacterPagedAdapter : PagingDataAdapter<ResultCharacter, MyViewHolder>(diffCallback) {

    inner class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivCharacter: ImageView by lazy { itemView.findViewById(R.id.iv_character) }
        private val tvCharacterName: TextView by lazy { itemView.findViewById(R.id.tv_character_name) }
        private val cvItem: TextView by lazy { itemView.findViewById(R.id.cv_character) }

        internal fun bindData(listItem: ResultCharacter?) {

            Log.e("CharacterPagedAdapter", "bindData: $listItem")

            ivCharacter.load(listItem?.image) {
                crossfade(true)
                crossfade(1000)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_image_default)
            }
            tvCharacterName.text = listItem?.name
        }
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<ResultCharacter>() {

            override fun areItemsTheSame(
                oldItem: ResultCharacter,
                newItem: ResultCharacter
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultCharacter,
                newItem: ResultCharacter
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(getItem(position)) // getItem() from PagingDataAdapter
        Log.e("CharacterPagedAdapter", "onBindViewHolder: $position")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        )
    }
}