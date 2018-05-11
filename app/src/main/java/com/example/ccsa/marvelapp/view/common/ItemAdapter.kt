package com.example.ccsa.marvelapp.view.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemAdapter<T: RecyclerView.ViewHolder> (@LayoutRes open val layoutId: Int) {

    /**
     * Function used to create view holder
     */
    abstract fun onCreateViewHolder(itemView: View): T

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder()
    }

    /**
     * Function to set values on item view
     */
    abstract fun T.onBindViewHolder()
}