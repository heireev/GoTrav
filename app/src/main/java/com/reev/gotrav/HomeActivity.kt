package com.reev.gotrav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    private lateinit var rvWisata: RecyclerView
    private val list = ArrayList<Wisata>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvWisata = findViewById(R.id.rv_wisata)
        rvWisata.setHasFixedSize(true)

        list.addAll(getListWisata())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_me -> {
                val moveIntent = Intent(this@HomeActivity, AboutMeActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListWisata(): ArrayList<Wisata> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val address = resources.getStringArray(R.array.link_map)
        val listWisata = ArrayList<Wisata>()
        for (i in dataName.indices) {
            val wisata = Wisata(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), address[i])
            listWisata.add(wisata)
        }
        return listWisata
    }

    private fun showRecyclerList() {
        rvWisata.layoutManager = LinearLayoutManager(this)
        val listWisataAdapter = ListWisataAdapter(list)
        rvWisata.adapter = listWisataAdapter

        listWisataAdapter.setOnItemClickCallback(object : ListWisataAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Wisata) {
                showSelectedWisata(data)
            }
        })
    }

    private fun showSelectedWisata(wisata: Wisata) {
        Toast.makeText(this, "Anda memilih " + wisata.name, Toast.LENGTH_SHORT).show()
    }// untuk menampilkan toast data setelah di klik
}