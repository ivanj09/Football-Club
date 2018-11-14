package com.ivanjt.footballclub.View

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ivanjt.footballclub.R
import org.jetbrains.anko.*

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams(width = dip(dimen(R.dimen.team_badge_size)), height = dip(dimen(R.dimen.team_badge_size)))

            textView {
                id = R.id.team_name
            }.lparams(width = matchParent, height = wrapContent) {
                marginStart = dip(32)
            }
        }
    }
}
