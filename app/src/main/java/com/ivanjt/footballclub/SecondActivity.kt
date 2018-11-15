package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SecondActivityUI().setContentView(this)
    }

    class SecondActivityUI : AnkoComponent<SecondActivity> {
        override fun createView(ui: AnkoContext<SecondActivity>): View = with(ui) {
            TODO("not yet implemented")
        }
    }
}
