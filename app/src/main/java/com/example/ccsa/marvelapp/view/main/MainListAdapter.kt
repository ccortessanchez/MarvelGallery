package com.example.ccsa.marvelapp.view.main

import com.example.ccsa.marvelapp.view.common.AnyItemAdapter
import com.example.ccsa.marvelapp.view.common.RecyclerListAdapter

class MainListAdapter(items: List<AnyItemAdapter>) : RecyclerListAdapter(items)

    /**
    fun add(itemAdapter: AnyItemAdapter) {
        val index = items.indexOf(itemAdapter)
        if (index == -1) return
        items += itemAdapter
        notifyItemInserted(index)
    }

    fun delete(itemAdapter: AnyItemAdapter) {
        val index = items.indexOf(itemAdapter)
        if (index == -1) return
        items -= itemAdapter
        notifyItemRemoved(index)
    }*/
