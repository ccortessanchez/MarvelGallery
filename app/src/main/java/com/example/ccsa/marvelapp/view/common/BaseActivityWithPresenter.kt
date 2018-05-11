package com.example.ccsa.marvelapp.view.common

import android.support.v7.app.AppCompatActivity
import com.example.ccsa.marvelapp.presenter.Presenter

abstract class BaseActivityWithPresenter: AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}