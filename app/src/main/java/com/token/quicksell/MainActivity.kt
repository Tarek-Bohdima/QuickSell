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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.token.quicksell.databinding.ActivityMainBinding
import com.token.quicksell.utils.ContextUtils
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        drawerLayout = binding.drawerLayout
        binding.navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            composeEmail()
            return@setNavigationItemSelectedListener true
        }

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == controller.graph.startDestinationId ||
                destination.id == R.id.quickSellFragment
            ) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
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
        val preferences = newBase.getSharedPreferences(
            newBase.getString(R.string.shared_preference_key),
            Context.MODE_PRIVATE)
        val language = preferences.getString(
            newBase.getString(R.string.saved_language_key), "en")
        val local = Locale(language.toString())
        Timber.d("attachBaseContext: lang = $language")
        Timber.d("---------------------------")
        val localUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase, local)
        super.attachBaseContext(localUpdatedContext)
    }
}