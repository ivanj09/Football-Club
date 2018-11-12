package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.ivanjt.footballclub.FootballClubAdapter.FootballAdapter
import com.ivanjt.footballclub.Model.FootballClub
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SecondActivityUI().setContentView(this)
    }

    inner class SecondActivityUI : AnkoComponent<SecondActivity> {
        override fun createView(ui: AnkoContext<SecondActivity>): View = with(ui) {
            val bundle: Bundle = intent.extras
            val club: FootballClub = bundle.getSerializable(FootballAdapter.EXTRA_CLUB) as FootballClub

            verticalLayout {
                padding = dip(8)

                imageView {
                    club.imageSrc?.let { setImageResource(it) }
                }.lparams(width = dip(72), height = dip(72)){
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                textView(club.name) {
                    textSize = dip(8).toFloat()
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(4)
                }

                textView(club.desc) {

                }.lparams(width = matchParent, height = wrapContent){
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    margin = dip(16)
                }
            }
        }
    }
}
