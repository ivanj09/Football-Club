package com.ivanjt.footballclub.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivanjt.footballclub.DetailActivity
import com.ivanjt.footballclub.Model.Event
import com.ivanjt.footballclub.R

class LastMatchesAdapter(private val context: Context, private val lastEvents: List<Event>) :
    RecyclerView.Adapter<LastMatchesAdapter.LastMatchesViewHolder>() {
    companion object {
        const val EXTRA_EVENT = "com.ivanjt.footballclub.EXTRA_EVENT"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchesViewHolder {
        return LastMatchesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, p0, false))
    }

    override fun getItemCount(): Int {
        return lastEvents.size
    }

    override fun onBindViewHolder(p0: LastMatchesViewHolder, p1: Int) {
        p0.bindItem(lastEvents[p1])
    }

    inner class LastMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var home: TextView
        private lateinit var homeScore: TextView
        private lateinit var away: TextView
        private lateinit var awayScore: TextView
        private lateinit var date: TextView

        fun bindItem(event: Event) {
            home = itemView.findViewById(R.id.tv_home)
            homeScore = itemView.findViewById(R.id.tv_home_score)
            away = itemView.findViewById(R.id.tv_away)
            awayScore = itemView.findViewById(R.id.tv_away_score)
            date = itemView.findViewById(R.id.tv_date_event)

            home.text = event.homeTeam
            homeScore.text = event.homeScore
            away.text = event.awayTeam
            awayScore.text = event.awayScore
            date.text = event.date

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_EVENT, event.id)

                context.startActivity(intent)
            }
        }
    }

}
