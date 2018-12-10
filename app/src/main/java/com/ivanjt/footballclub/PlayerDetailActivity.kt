package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.ivanjt.footballclub.adapter.PlayersAdapter
import com.ivanjt.footballclub.model.Player
import com.squareup.picasso.Picasso

class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerDesc: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playerThumb: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        //Get player from intent
        val player = intent.extras.get(PlayersAdapter.EXTRA_PLAYER) as Player

        //Set player name
        val actionBar = supportActionBar
        if (supportActionBar != null) {
            actionBar?.title = player.name
        }

        //Reference to xml view
        playerHeight = findViewById(R.id.tv_height)
        playerWeight = findViewById(R.id.tv_weight)
        playerDesc = findViewById(R.id.tv_player_desc)
        playerThumb = findViewById(R.id.iv_thumb)
        playerPosition = findViewById(R.id.tv_player_position)

        playerHeight.text = player.height
        playerWeight.text = player.weight
        playerDesc.text = player.desc
        playerPosition.text = player.position
        Picasso.get().load(player.thumb).into(playerThumb)
    }
}