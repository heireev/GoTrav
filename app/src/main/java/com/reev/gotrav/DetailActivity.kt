package com.reev.gotrav

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.reev.gotrav.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityDetailBinding

    private var dataWisata: Wisata? = null
    private var name: String? = null
    private var desc: String? = null
    private var address: String? = null
    private var photo: Int? = null

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.blue_700)))
        //buat hide bar atas
        supportActionBar?.hide()


        binding.btnMap.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)

        dataWisata = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_wisata", Wisata::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_wisata")
        }

        name = dataWisata?.name ?: "Data Unknown"
        desc = dataWisata?.description ?: "Data Unknown"
        address = dataWisata?.address ?: "Data Unknown"
        photo = dataWisata?.photo ?: 0

        binding.namaWisata.text = name
        binding.descWisata.text = desc

        binding.photoWisata.setImageResource(photo!!)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_map -> {
                //buat implisit intent ke GMap

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
                startActivity(intent)
            }
            R.id.btn_share -> {
                //Buat implisit intent untuk share link
                val intent = Intent(Intent.ACTION_SEND)
                val send = "$name\n\nDeskripsi:\n$desc\n\nLihat Map:\n$address"
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, name)
                intent.putExtra(Intent.EXTRA_TEXT, send)
                startActivity(Intent.createChooser(intent, "Bagikan Wisata"))
            }
        }
    }
}