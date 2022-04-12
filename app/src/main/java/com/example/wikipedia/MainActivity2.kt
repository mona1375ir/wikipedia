package com.example.wikipedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.wikipedia.data.ItemPost
import com.example.wikipedia.databinding.ActivityMain2Binding
import com.example.wikipedia.fragment.SEND_DATA_TO_MAINACTIVITY2

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAsli)
        binding.collapsingMain.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//get Data
        val dataPost=intent.getParcelableExtra<ItemPost>(SEND_DATA_TO_MAINACTIVITY2)
        if (dataPost!=null){
            showData(dataPost)
        }


    }

    private fun showData(itemPost: ItemPost) {
       // Log.v("testData",itemPost.toString())
        Glide.with(this).load(itemPost.imgUrl).into(binding.imgDetail)
        binding.txtDetailTitle.text=itemPost.txtTitle
        binding.txtDetailSubtitle.text=itemPost.txtSubtitle
        binding.txtDetailText.text=itemPost.txtDetail
        binding.fabDetailOpenWikipedia.setOnClickListener({
            val url="https://en.wikipedia.org/wiki/Main_Page"
            val openWebsite=Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(openWebsite)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true

    }

}