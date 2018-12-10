package com.ivanjt.footballclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.ivanjt.footballclub.MatchDetailActivity
import com.ivanjt.footballclub.R
import com.ivanjt.footballclub.model.Event
import java.text.SimpleDateFormat
import java.util.*


class LastMatchesAdapter(
    private val context: Context,
    private val lastEvents: MutableList<Event>,
    private val callFromFavourite: Boolean
) :
    RecyclerView.Adapter<LastMatchesAdapter.LastMatchesViewHolder>(), Filterable {
    companion object {
        const val EXTRA_EVENT = "com.ivanjt.footballclub.EXTRA_EVENT"
    }

    private val filter: Filter = EventFilter(this, lastEvents)

    class EventFilter(private val adapter: LastMatchesAdapter, private val events: MutableList<Event>) : Filter() {
        private val old: MutableList<Event> = mutableListOf()
        private val filteredList: MutableList<Event> = mutableListOf()
        private var firstTime = true

        fun notifyDataSetChanged() {
            firstTime = true
        }

        override fun performFiltering(p0: CharSequence?): FilterResults {
            if (firstTime) {
                old.clear()
                old.addAll(events)
            } else {
                events.clear()
                events.addAll(old)
            }

            firstTime = false

            filteredList.clear()
            val results = FilterResults()

            if (p0?.length == 0) {
                filteredList.addAll(old)
            } else {
                val query = p0?.toString()?.toLowerCase()

                for (event in events) {
                    if (event.homeTeam.toString().toLowerCase().contains(query.toString()) || event.awayTeam.toString().toLowerCase().contains(
                            query.toString()
                        )
                    ) {
                        filteredList.add(event)
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

            adapter.lastEvents.clear()
            adapter.lastEvents.addAll(p1?.values as MutableList<Event>)

            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchesViewHolder {
        return LastMatchesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, p0, false))
    }

    override fun getFilter(): Filter {
        return filter
    }

    override fun getItemCount(): Int {
        return lastEvents.size
    }

    override fun onBindViewHolder(p0: LastMatchesViewHolder, p1: Int) {
        if (callFromFavourite) {
            p0.bindFavourite(lastEvents[p1])
            return
        }
        p0.bindItem(lastEvents[p1])
    }

    inner class LastMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var home: TextView
        private lateinit var homeScore: TextView
        private lateinit var away: TextView
        private lateinit var awayScore: TextView
        private lateinit var date: TextView
        private lateinit var card: CardView
        private lateinit var time: TextView
        private lateinit var reminder: ImageView

        fun bindItem(event: Event) {
            card = itemView.findViewById(R.id.cv_item)
            home = itemView.findViewById(R.id.tv_home)
            homeScore = itemView.findViewById(R.id.tv_home_score)
            away = itemView.findViewById(R.id.tv_away)
            awayScore = itemView.findViewById(R.id.tv_away_score)
            date = itemView.findViewById(R.id.tv_date_event)
            time = itemView.findViewById(R.id.tv_time_event)
            reminder = itemView.findViewById(R.id.iv_reminder)

            home.text = event.homeTeam
            homeScore.text = event.homeScore
            away.text = event.awayTeam
            awayScore.text = event.awayScore

            var dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ssZZZZZ")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            var d = dateFormat.parse(event.date + " " + event.time)

            dateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            dateFormat.timeZone = TimeZone.getDefault()
            date.text = dateFormat.format(d)

            dateFormat = SimpleDateFormat("hh:mm:ssZZZZZ")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            d = dateFormat.parse(event.time)

            dateFormat = SimpleDateFormat("hh:mm")
            dateFormat.timeZone = TimeZone.getDefault()

            time.text = dateFormat.format(d)

            card.setOnClickListener {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.putExtra(EXTRA_EVENT, event.id)

                context.startActivity(intent)
            }

            reminder.setOnClickListener {
                val intent = Intent(Intent.ACTION_EDIT)

                val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ssZZZZZ")
                formatter.timeZone = TimeZone.getTimeZone("UTC")

                var date = formatter.parse(event.date + " " + event.time)
                formatter.timeZone = TimeZone.getDefault()
                val strDate = formatter.format(date)
                date = formatter.parse(strDate)

                intent.type = "vnd.android.cursor.item/event"

                intent.putExtra("beginTime", date.time)
                intent.putExtra("allDay", false)
                intent.putExtra("endTime", date.time + 90 * 60 * 1000)
                intent.putExtra("title", event.homeTeam + " vs " + event.awayTeam)

                context.startActivity(intent)
            }
        }

        fun bindFavourite(event: Event) {
            card = itemView.findViewById(R.id.cv_item)
            home = itemView.findViewById(R.id.tv_home)
            homeScore = itemView.findViewById(R.id.tv_home_score)
            away = itemView.findViewById(R.id.tv_away)
            awayScore = itemView.findViewById(R.id.tv_away_score)
            date = itemView.findViewById(R.id.tv_date_event)
            time = itemView.findViewById(R.id.tv_time_event)

            home.text = event.homeTeam
            homeScore.text = event.homeScore
            away.text = event.awayTeam
            awayScore.text = event.awayScore
            date.text = event.date
            time.text = event.time

            card.setOnClickListener {
                val intent = Intent(context, MatchDetailActivity::class.java)
                intent.putExtra(EXTRA_EVENT, event.id)

                context.startActivity(intent)
            }
        }
    }

}
