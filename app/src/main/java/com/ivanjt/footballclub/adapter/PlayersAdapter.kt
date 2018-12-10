package com.ivanjt.footballclub.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ivanjt.footballclub.PlayerDetailActivity
import com.ivanjt.footballclub.R
import com.ivanjt.footballclub.model.Player
import com.squareup.picasso.Picasso

class PlayersAdapter(private val context: Context, private val playerList: List<Player>) :
    RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {
    companion object {
        const val EXTRA_PLAYER = "com.ivanjt.footballclub.EXTRA_PLAYER"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, p0, false))
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(playerList[p1])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imageView: ImageView
        private lateinit var playerName: TextView
        private lateinit var playerPosition: TextView

        fun bindItem(player: Player) {
            imageView = itemView.findViewById(R.id.iv_player)
            playerName = itemView.findViewById(R.id.tv_player_name)
            playerPosition = itemView.findViewById(R.id.tv_player_position)

            Picasso.get().load(player.cutOut).into(imageView)
            playerName.text = player.name
            playerPosition.text = player.position

            itemView.setOnClickListener {
                val intent = Intent(context, PlayerDetailActivity::class.java)
                intent.putExtra(EXTRA_PLAYER, player)
                context.startActivity(intent)
            }
        }
    }
}
