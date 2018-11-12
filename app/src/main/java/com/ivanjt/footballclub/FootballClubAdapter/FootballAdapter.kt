package com.ivanjt.footballclub.FootballClubAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanjt.footballclub.Model.FootballClub
import com.ivanjt.footballclub.R
import com.ivanjt.footballclub.SecondActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_football_club.view.*
import org.jetbrains.anko.startActivity

class FootballAdapter(private val context: Context, private val footBallClubList: ArrayList<FootballClub>) :
    RecyclerView.Adapter<FootballAdapter.FootballHolder>() {
    companion object {
        val EXTRA_CLUB = "club"
    }

    inner class FootballHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: FootballClub) {
            itemView.tv_club.text = item.name
            itemView.setOnClickListener {
                context.startActivity<SecondActivity>(EXTRA_CLUB to footBallClubList[adapterPosition])
            }

            item.imageSrc?.let { Picasso.get().load(it).into(itemView.iv_club) }
        }
    }

    override fun getItemCount(): Int {
        return footBallClubList.size
    }

    override fun onBindViewHolder(p0: FootballHolder, p1: Int) {
        p0.bindItem(footBallClubList[p1])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FootballHolder {
        return FootballHolder(LayoutInflater.from(context).inflate(R.layout.item_football_club, p0, false))
    }
}
