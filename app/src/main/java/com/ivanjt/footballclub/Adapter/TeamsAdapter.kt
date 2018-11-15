package com.ivanjt.footballclub.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ivanjt.footballclub.Model.Team
import com.ivanjt.footballclub.R
import com.ivanjt.footballclub.View.TeamItemUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class TeamsAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {
    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamBadge: ImageView = itemView.findViewById(R.id.team_badge)
        private val teamName: TextView = itemView.findViewById(R.id.team_name)

        fun bindItem(team: Team) {
            Picasso.get().load(team.badge).into(teamBadge)
            teamName.text = team.name
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(TeamItemUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(teams[p1])
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}