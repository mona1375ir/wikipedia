package com.example.wikipedia.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import android.widget.Toast as Toast1

class MainActivity : BaseActivity() {
    private lateinit var
            sharedPreferences: SharedPreferences
    private lateinit var mybinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mybinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mybinding.root)
        firstRun()
        val myfragment = supportFragmentManager.beginTransaction()

        myfragment.replace(R.id.from_container_main, Fragment_Profile()).commit()




        setSupportActionBar(mybinding.ToolbarMain)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            mybinding.drawerLayoutMain, mybinding.ToolbarMain,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        mybinding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        mybinding.NavigationviewMain.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_moviemaker -> {
                    // start is direction for closeDrawer
                    mybinding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    Snackbar
                        .make(mybinding.root, "No Internet", Snackbar.LENGTH_LONG)
                        .setAction("Retry") {
                            Toast1.makeText(this, "Checking Network", Toast1.LENGTH_SHORT).show()
                        }
                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                        .setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
                        .show()
                }
                R.id.menu_photographer -> {
                    //load fragment
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.from_container_main, FragmentPhotographer())
                    transaction.addToBackStack(null)
                    transaction.commit()
                    mybinding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    //checked item by groupMenu
                    mybinding.NavigationviewMain.setCheckedItem(R.id.group_menu)
                    // binding.NavigationviewMain.setCheckedItem(R.id.menu_photografer)

                }
                R.id.menu_translator -> {
                    val intent = Intent(this, MainActivity3::class.java)
                    startActivity(intent)

                }
                R.id.menu_writer -> {
                    mybinding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    val sweetAlert = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    sweetAlert.titleText = "SweetAlert!"
                    sweetAlert.confirmText = "Confirm"
                    sweetAlert.cancelText = "Cancel"
                    sweetAlert.contentText = "Wanna be a Writer?"
                    sweetAlert.dismiss()
                    sweetAlert.setConfirmClickListener {
                        sweetAlert.dismiss()
                        Toast1.makeText(this, "You can ,JUST WORK WORK WORK!", Toast1.LENGTH_SHORT)
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
        mybinding.bottomNavigationViewMain.setOnItemSelectedListener { it ->
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
            mybinding.NavigationviewMain.menu.forEach {
                if (it.isChecked) {
                    it.isChecked = false
                }
            }

            true
        }
        mybinding.bottomNavigationViewMain.setOnItemReselectedListener { }
    }

    private fun firstRun() {
        sharedPreferences =
            this.getSharedPreferences("MainSharedPref.xml", Context.MODE_PRIVATE)
        val firstrun = sharedPreferences.getBoolean("IsFirstRun", true)
        if (firstrun) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            sharedPreferences.edit().putBoolean("IsFirstRun", false).apply()
        }


    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)


    }

    /* private fun firstRun() {
         replaceFragment(Fragment_Profile())
         binding.bottomNavigationViewMain.selectedItemId = R.id.menu_explore
     }*/

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.from_container_main, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_Exit -> {
                onBackPressed()
            }
        }
        return true
    }

}
