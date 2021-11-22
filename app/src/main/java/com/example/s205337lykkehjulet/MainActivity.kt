package com.example.s205337lykkehjulet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.adapter.ItemAdapter
import com.example.s205337lykkehjulet.data.DataCategories
import com.example.s205337lykkehjulet.databinding.ActivityMainBinding

/**
 * https://developer.android.com/guide/navigation/navigation-getting-started#kotlin
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        // der skal benyttes binding med binding.root, men det virker ikke lige nu
        setContentView(R.layout.fragment_start)


        // hvor skal denne kode stå?
        val myDataset = DataCategories().loadButtonContext()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}