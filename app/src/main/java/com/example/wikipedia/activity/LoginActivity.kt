package com.example.wikipedia.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.wikipedia.databinding.ActivityLoginBinding
import ir.dunijet.animation.ext.BaseActivity


class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            saveData()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }

    private fun saveData() {
        sharedPreferences =
            this.getSharedPreferences("profileSharedPref.xml", MODE_PRIVATE)
        val name = binding.edtTilone.text.toString()
        val lastName = binding.edtTiltwo.text.toString()

        //put data into shared... way1
        /* sharedPreferences.edit().putString("name", name).apply()
     sharedPreferences.edit().putString("Last_name", last_name).apply()

     if (binding.radioBtnMan.isChecked) {
         sharedPreferences.edit().putBoolean("is man", true).apply()
     } else {
         //is women
         sharedPreferences.edit().putBoolean("is man", false).apply()

     }*/

        //put data into shared... way2
        val editor = sharedPreferences.edit()

        editor.putString("KEYName", name)

        editor.putString("KEY_lastName", lastName)

        if (binding.radioBtnMan.isChecked) {
            editor.putBoolean("isMan", true)
        } else
            editor.putBoolean("isMan", false)

        editor.apply()

        //get data from shared...
        val myname = sharedPreferences.getString("KEYName", "") //default value
        val mylastName = sharedPreferences.getString("KEY_lastName", "")
        val isMan = sharedPreferences.getBoolean("isMan", false)
        binding.edtTilone.setText(myname)
        binding.edtTiltwo.setText(mylastName)
        if (isMan) {
            binding.radioBtnMan.isChecked = true
        } else {
            binding.radioBtnWomen.isChecked = true
        }
    }

}
