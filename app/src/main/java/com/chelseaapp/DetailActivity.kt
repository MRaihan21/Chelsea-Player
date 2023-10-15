package com.chelseaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chelseaapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.title = "Detail Member"

        val data = intent.getParcelableExtra<Player>("Data")
        Log.d("Detail Data", data.toString())

        Glide.with(this)
            .load(data?.photo)
            .apply(RequestOptions().override(800, 600))
            .into(detailBinding.imgItemPhoto)
        detailBinding.tvItemName.text = data?.name
        detailBinding.tvItemDesc.text = data?.description

    }
}