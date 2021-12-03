package com.example.s205337lykkehjulet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.s205337lykkehjulet.databinding.ActivityMainBinding

/**
 * Inspiration til at benytte NavigationGraph er fundet fra Android CodeLab unit 3 pathway 4
 * https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-4
 *
 * Inspiration implementering af recycler view er fundet fra Android CodeLab unit 2 pathway 3
 * https://developer.android.com/courses/pathways/android-basics-kotlin-unit-2-pathway-3
 *
 * Billeder til appen er fundet igennem PNGWING
 * https://www.pngwing.com/
 */

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController



        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}