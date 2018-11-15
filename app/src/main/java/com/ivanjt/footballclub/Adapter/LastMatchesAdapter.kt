package com.ivanjt.footballclub.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ivanjt.footballclub.Model.Match

class LastMatchesAdapter(private val lastMatches: List<Match>) :
    RecyclerView.Adapter<LastMatchesAdapter.NextMatchesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NextMatchesViewHolder {
        return NextMatchesViewHolder(p0.getChildAt(p1))
    }

    override fun getItemCount(): Int {
        return lastMatches.size
    }

    override fun onBindViewHolder(p0: NextMatchesViewHolder, p1: Int) {
        p0.bindItem(lastMatches[p1])
    }

    inner class NextMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(match: Match) {

        }
    }

}
