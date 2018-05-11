package com.example.ccsa.marvelapp.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.ccsa.marvelapp.R
import com.example.ccsa.marvelapp.data.MarvelRepository
import com.example.ccsa.marvelapp.model.MarvelCharacter
import com.example.ccsa.marvelapp.presenter.MainPresenter
import com.example.ccsa.marvelapp.view.common.BaseActivityWithPresenter
import com.example.ccsa.marvelapp.view.common.bindToSwipeRefresh
import com.example.ccsa.marvelapp.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapters = items.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }
}
