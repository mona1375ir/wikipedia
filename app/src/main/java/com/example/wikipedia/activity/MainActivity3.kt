package com.example.wikipedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wikipedia.R
import com.example.wikipedia.databinding.ActivityMain3Binding
import ir.dunijet.animation.ext.BaseActivity

class MainActivity3 : BaseActivity() {
    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.ToolbarMain3)
        Glide.with(this).load(R.drawable.img_translatore).into(binding.imageView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main3,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_explore3 ->{
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            }
    }
        return true
    }
}