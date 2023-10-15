package com.chelseaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var rvPlayer: RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Chelsea Player"


        rvPlayer = findViewById(R.id.rv_players)
        rvPlayer.setHasFixedSize(true)

        list.addAll(PlayerData.listData)
        showRecylerList()
    }


    //    Menampilkan Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

//    Menampilkan Aksi Menu di Klik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_about -> {
                val aboutIntent = Intent(this@MainActivity, About::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private fun getListPlayer(): List<Player>{
//            val dataName = resources.getStringArray(R.array.data_name)
//            val dataDescription = resources.getStringArray(R.array.data_description)
//            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//            val listPlayer = ArrayList<Player>()
//            for (i in dataName.indices){
//                val player = Player(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
//                listPlayer.add(player)
//            }
//            return listPlayer
//        }

    private fun showRecylerList(){
        rvPlayer.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvPlayer.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickCallback(object : ListPlayerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Player) {
                val moveIntentDetail = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntentDetail.putExtra("Data", data)
                startActivity(moveIntentDetail)
                showSelectedPlayer(data)
            }
        })
    }

    private fun showSelectedPlayer(player: Player){
        Toast.makeText(this, "Kamu memilih " + player.name, Toast.LENGTH_SHORT).show()
    }
}