package com.techjammers.coronahealthtrack.activities

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.techjammers.coronahealthtrack.Fragment.MainScreenFragment
import com.techjammers.coronahealthtrack.R
import com.techjammers.coronahealthtrack.adapters.NavigationDrawerAdapter

class MainActivity : AppCompatActivity() {

    /*The list for storing the names of the items in list of navigation drawer*/
    var navigationDrawerIconsList: ArrayList<String> = arrayListOf()

    var images_for_navdrawer = intArrayOf(R.drawable.navigation_favorites,
        R.drawable.navigation_aboutus, R.drawable.navigation_allsongs,
        R.drawable.navigation_settings)
    object Statified {
        var drawerLayout: DrawerLayout? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*This syntax is used to access the objects inside the class*/
        MainActivity.Statified.drawerLayout = findViewById(R.id.drawer_layout)

        /*Adding names of the titles using the add() function of ArrayList*/
        navigationDrawerIconsList.add("Health")
        navigationDrawerIconsList.add("Alarm")
        navigationDrawerIconsList.add("Language preferance")
        navigationDrawerIconsList.add("Settings")

        val toggle = ActionBarDrawerToggle(this@MainActivity,
            MainActivity.Statified.drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        MainActivity.Statified.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        val mainScreenFragment = MainScreenFragment()
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.details_fragment, mainScreenFragment, "MainScreenFragment")
            .commit()

        val _navigationAdapter = NavigationDrawerAdapter(navigationDrawerIconsList,
            images_for_navdrawer, this)

        _navigationAdapter.notifyDataSetChanged()

        val navigation_recycler_view =
            findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager = LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator = DefaultItemAnimator()
        navigation_recycler_view.adapter = _navigationAdapter
        navigation_recycler_view.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
    }
}