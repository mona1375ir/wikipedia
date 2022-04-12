package com.example.wikipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.wikipedia.databinding.ActivityMainBinding
import com.example.wikipedia.fragment.Fragment_Explore
import com.example.wikipedia.fragment.Fragment_Profile
import com.example.wikipedia.fragment.Fragment_Trend

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.ToolbarMain)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this,
            binding.drawerLayoutMain, binding.ToolbarMain,
            R.string.openDrawer,
            R.string.closeDrawer)
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.NavigationviewMain.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_moviemaker -> {
                    Toast.makeText(this, "menu_moviemaker", Toast.LENGTH_SHORT).show()
                    // start is direction for closeDrawer
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_photografer -> {}
                R.id.menu_translator -> {}
                R.id.menu_writer -> {}
                R.id.menu_visit_wikipedia -> {}
                R.id.menu_visit_wikimedia -> {}

            }
            true
        }
        firstRun()
        binding.bottomNavigationViewMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_explore -> {
                    replaceFragment(Fragment_Explore())
                }
                R.id.menu_trend -> {
                    replaceFragment(Fragment_Trend())
                }
                R.id.menu_profile -> {
                    replaceFragment(Fragment_Profile())
                }
            }
            true
        }
        binding.bottomNavigationViewMain.setOnItemReselectedListener {  }
    }

    private fun firstRun() {
        replaceFragment(Fragment_Explore())
        binding.bottomNavigationViewMain.selectedItemId=R.id.menu_explore
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fram_container_main, fragment)
        transaction.commit()
    }

}