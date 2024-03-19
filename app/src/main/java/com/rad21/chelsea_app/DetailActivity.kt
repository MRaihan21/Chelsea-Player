package com.rad21.chelsea_app

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.rad21.chelsea_app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dataPlayer: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataPlayer = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Player>("key_player", Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>("key_player")
        }

        val ivDetailPhoto: ImageView = findViewById(R.id.iv_photo_detail)
        val tvDetailName: TextView = findViewById(R.id.tv_name_detail)
        val tvDetailDesc: TextView = findViewById(R.id.tv_desc_detail)

        ivDetailPhoto.setImageResource(dataPlayer?.photo!!)
        tvDetailName.setText(dataPlayer?.name)
        tvDetailDesc.setText(dataPlayer?.desc)


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${dataPlayer?.name}\n\n${dataPlayer?.desc}")

                    if (dataPlayer != null && dataPlayer?.photo != null) {
                        val imageUri = Uri.parse(dataPlayer?.photo.toString())
                        type = "image/*"
                        putExtra(Intent.EXTRA_STREAM, imageUri)

                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    } else {
                        type = "text/plain"
                    }
                }

                startActivity(Intent.createChooser(sendIntent, "Send image using:"))

            }
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}