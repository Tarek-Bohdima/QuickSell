package com.token.quicksell

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.token.quicksell.databinding.ActivityMainBinding
import com.token.quicksell.utils.setAppLocale

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    var savedLang = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        drawerLayout = binding.drawerLayout
        binding.navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            composeEmail()
            return@setNavigationItemSelectedListener true
        }
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val selectedLanguage = prefs.getString("LANGUAGE", null)
        savedLang = selectedLanguage.toString()
    }

    private fun composeEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data =
                Uri.parse("mailto:contact@tokeninc.com?subject=" + getString(R.string.email_subject))
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun attachBaseContext(newBase: Context) {
//        savedLang = prefs.getString("LANGUAGE", "en")!!
        super.attachBaseContext(ContextWrapper(newBase.setAppLocale(savedLang)))
    }

    /* override fun onAttachedToWindow() {
         super.onAttachedToWindow()
         // Will give the direction of the layout depending of the Locale you've just set
         window.decorView.layoutDirection = Locale.getDefault().layoutDirection
     }*/
}