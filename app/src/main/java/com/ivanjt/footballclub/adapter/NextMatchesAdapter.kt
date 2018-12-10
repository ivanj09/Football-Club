package com.ivanjt.footballclub.adapter

import android.content.Context
import android.content.Intent
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
import com.ivanjt.footballclub.adapter.LastMatchesAdapter.Companion.EXTRA_EVENT
import com.ivanjt.footballclub.model.Event
import java.text.SimpleDateFormat
import java.util.*

class NextMatchesAdapter(private val context: Context, private val nextEvents: MutableList<Event>) :
    RecyclerView.Adapter<NextMatchesAdapter.NextMatchesViewHolder>(), Filterable {

    private val filter: Filter = EventFilter(this, nextEvents)

    class EventFilter(private val adapter: NextMatchesAdapter, private val events: MutableList<Event>) : Filter() {
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

            adapter.nextEvents.clear()
            adapter.nextEvents.addAll(p1?.values as MutableList<Event>)

            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NextMatchesViewHolder {
        return NextMatchesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, p0, false))
    }

    override fun getItemCount(): Int {
        return nextEvents.size
    }

    override fun getFilter(): Filter {
        return filter
    }

    override fun onBindViewHolder(p0: NextMatchesViewHolder, p1: Int) {
        p0.bindItem(nextEvents[p1])
    }

    inner class NextMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var home: TextView
        private lateinit var away: TextView
        private lateinit var date: TextView
        private lateinit var time: TextView
        private lateinit var reminder: ImageView

        fun bindItem(event: Event) {
            home = itemView.findViewById(R.id.tv_home)
            away = itemView.findViewById(R.id.tv_away)
            date = itemView.findViewById(R.id.tv_date_event)
            time = itemView.findViewById(R.id.tv_time_event)
            reminder = itemView.findViewById(R.id.iv_reminder)

            home.text = event.homeTeam
            away.text = event.awayTeam

            var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZZ")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            var d = dateFormat.parse(event.date + " " + event.time)

            dateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            dateFormat.timeZone = TimeZone.getDefault()
            date.text = dateFormat.format(d)

            dateFormat = SimpleDateFormat("HH:mm:ssZZZZZ")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            d = dateFormat.parse(event.time)

            dateFormat = SimpleDateFormat("HH:mm")
            dateFormat.timeZone = TimeZone.getDefault()

            time.text = dateFormat.format(d)

            itemView.setOnClickListener {
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
    }
}
