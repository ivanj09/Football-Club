package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SecondActivityUI().setContentView(this)
    }

    class SecondActivityUI : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
            TODO("not yet implemented")
        }
    }
}
