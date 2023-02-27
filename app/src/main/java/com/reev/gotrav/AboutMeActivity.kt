package com.reev.gotrav

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.reev.gotrav.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAboutMeBinding
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.blue_700)))


        binding.btnInsta.setOnClickListener(this)
        binding.btnGithub.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_insta -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/heireev/"))
                startActivity(intent)
            }
            R.id.btn_github -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/heireev"))
                startActivity(intent)
            }
        }
    }
}