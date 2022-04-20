package com.example.wikipedia.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.wikipedia.R
import com.example.wikipedia.databinding.ActivityMainBinding
import com.example.wikipedia.fragment.Fragment_Explore
import com.example.wikipedia.fragment.Fragment_Profile
import com.example.wikipedia.fragment.Fragment_Trend
import com.google.android.material.snackbar.Snackbar
import ir.dunijet.animation.ext.BaseActivity
import ir.dunijet.wikipedia.fragments.FragmentPhotographer

class MainActivity : BaseActivity() {
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
                    // start is direction for closeDrawer
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    Snackbar
                        .make(binding.root, "No Internet", Snackbar.LENGTH_LONG)
                        .setAction("Retry") {
                            Toast.makeText(this, "Checking Network", Toast.LENGTH_SHORT).show()
                        }
                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                        .setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
                        .show()
                }
                R.id.menu_photographer -> {
                    //load fragment
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(R.id.from_container_main, FragmentPhotographer())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    //checked item by groupMenu
                    binding.NavigationviewMain.setCheckedItem(R.id.group_menu)
                    // binding.NavigationviewMain.setCheckedItem(R.id.menu_photografer)

                }
                R.id.menu_translator -> {
                    val intent = Intent(this, MainActivity3::class.java)
                    startActivity(intent)

                }
                R.id.menu_writer -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    val sweetAlert = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    sweetAlert.titleText = "SweetAlert!"
                    sweetAlert.confirmText = "Confirm"
                    sweetAlert.cancelText = "Cancel"
                    sweetAlert.contentText = "Wanna be a Writer?"
                    sweetAlert.dismiss()
                    sweetAlert.setConfirmClickListener {
                        sweetAlert.dismiss()
                        Toast.makeText(this, "You can ,JUST WORK WORK WORK!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    sweetAlert.setCancelClickListener {
                        sweetAlert.dismiss()
                    }
                    sweetAlert.show()
                }

                R.id.menu_visit_wikipedia -> {
                    openWebsite("https://www.wikipedia.org/")
                }
                R.id.menu_visit_wikimedia -> {
                    openWebsite("https://www.wikimedia.org/")

                }

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
            binding.NavigationviewMain.menu.forEach {
                if (it.isChecked) {
                    it.isChecked = false
                }
            }

            true
        }
        binding.bottomNavigationViewMain.setOnItemReselectedListener { }
    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)


    }

    private fun firstRun() {
        replaceFragment(Fragment_Explore())
        binding.bottomNavigationViewMain.selectedItemId = R.id.menu_explore
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.from_container_main, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_Exit->{
                onBackPressed()
            }
        }
        return true
    }
}