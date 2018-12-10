package com.ivanjt.footballclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.ivanjt.footballclub.R
import com.ivanjt.footballclub.TeamDetailActivity
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.view.TeamItemUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class TeamsAdapter(private val context: Context, private val teams: MutableList<Team>) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>(), Filterable {
    private val filter: Filter = TeamsFilter(this, teams)

    companion object {
        const val EXTRA_TEAM_ID = "com.ivanjt.footballclub.EXTRA_TEAM_ID"
    }

    class TeamsFilter(private val adapter: TeamsAdapter, private val teams: MutableList<Team>) : Filter() {
        private val filteredList: MutableList<Team> = mutableListOf()
        private val old: MutableList<Team> = mutableListOf()
        private var firstTime = true

        fun notifyDataSetChanged() {
            firstTime = true
        }

        override fun performFiltering(p0: CharSequence?): FilterResults? {
            if (firstTime) {
                old.clear()
                old.addAll(teams)
            } else {
                teams.clear()
                teams.addAll(old)
            }

            firstTime = false

            filteredList.clear()
            val results = FilterResults()

            if (p0?.length == 0) {
                filteredList.addAll(old)
            } else {
                val query = p0?.toString()?.toLowerCase()

                for (team in teams) {
                    if (team.name.toString().toLowerCase().contains(query.toString())) {
                        filteredList.add(team)
                    }
                }
            }

            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            if (p1?.values == null) {
                return
            }

            adapter.teams.clear()
            adapter.teams.addAll(p1?.values as MutableList<Team>)

            adapter.notifyDataSetChanged()
        }

    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamBadge: ImageView = itemView.findViewById(R.id.team_badge)
        private val teamName: TextView = itemView.findViewById(R.id.team_name)

        fun bindItem(team: Team) {
            Picasso.get().load(team.badge).into(teamBadge)
            teamName.text = team.name

            itemView.setOnClickListener {
                val intent = Intent(context, TeamDetailActivity::class.java)
                intent.putExtra(EXTRA_TEAM_ID, team.id)

                context.startActivity(intent)
            }
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

    override fun getFilter(): Filter {
        return filter
    }
}