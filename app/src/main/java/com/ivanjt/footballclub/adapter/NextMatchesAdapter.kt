package com.ivanjt.footballclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivanjt.footballclub.adapter.LastMatchesAdapter.Companion.EXTRA_EVENT
import com.ivanjt.footballclub.DetailActivity
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.R

class NextMatchesAdapter(private val context: Context, private val nextEvents: MutableList<Event>) :
    RecyclerView.Adapter<NextMatchesAdapter.NextMatchesViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NextMatchesViewHolder {
        return NextMatchesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, p0, false))
    }

    override fun getItemCount(): Int {
        return nextEvents.size
    }

    override fun onBindViewHolder(p0: NextMatchesViewHolder, p1: Int) {
        p0.bindItem(nextEvents[p1])
    }

    inner class NextMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var home: TextView
        private lateinit var away: TextView
        private lateinit var date: TextView

        fun bindItem(event: Event) {
            home = itemView.findViewById(R.id.tv_home)
            away = itemView.findViewById(R.id.tv_away)
            date = itemView.findViewById(R.id.tv_date_event)

            home.text = event.homeTeam
            away.text = event.awayTeam
            date.text = event.date

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_EVENT, event.id)

                context.startActivity(intent)
            }
        }
    }
}
