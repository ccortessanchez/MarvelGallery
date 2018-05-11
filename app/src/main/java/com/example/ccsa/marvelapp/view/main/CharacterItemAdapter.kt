package com.example.ccsa.marvelapp.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.ccsa.marvelapp.R
import com.example.ccsa.marvelapp.model.MarvelCharacter
import com.example.ccsa.marvelapp.view.common.ItemAdapter
import com.example.ccsa.marvelapp.view.common.bindView
import com.example.ccsa.marvelapp.view.common.loadImage

class CharacterItemAdapter(
        val character: MarvelCharacter
): ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    /**
     * Function to setup views
     */
    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.textView)
        val imageView by bindView<ImageView>(R.id.imageView)
    }
}