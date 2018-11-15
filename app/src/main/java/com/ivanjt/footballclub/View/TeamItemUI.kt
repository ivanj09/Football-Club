package com.ivanjt.footballclub.View

import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ivanjt.footballclub.R
import org.jetbrains.anko.*

class TeamItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            background = with(TypedValue()) {
                context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                ContextCompat.getDrawable(context, resourceId)
            }
            isClickable = true
            isFocusable = true

            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams(width = dip(dimen(R.dimen.team_badge_size)), height = dip(dimen(R.dimen.team_badge_size)))

            textView {
                id = R.id.team_name
                textAppearance = R.style.TextAppearance_AppCompat_Body2
            }.lparams(width = matchParent, height = wrapContent) {
                marginStart = dip(32)
                gravity = Gravity.CENTER
            }
        }
    }
}
