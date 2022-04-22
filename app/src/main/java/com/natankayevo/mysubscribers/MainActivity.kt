package com.natankayevo.mysubscribers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.natankayevo.mysubscribers.databinding.ActivityMainBinding
import com.natankayevo.mysubscribers.databinding.SubscriberItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: SubscriberItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        //
        binding2 = SubscriberItemBinding.inflate(layoutInflater)
        val view2 = binding2.root

        binding2.
        //

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.appToolbar.setupWithNavController(navController, appBarConfiguration)

        setContentView(view)


    }
}