package upc.edu.pe.starwarsguide.ViewController.Activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import upc.edu.pe.starwarsguide.R
import upc.edu.pe.starwarsguide.ViewController.Fragments.CharactersFragment
import upc.edu.pe.starwarsguide.ViewController.Fragments.FilmsFragment
import upc.edu.pe.starwarsguide.ViewController.Fragments.SpeciesFragment
import upc.edu.pe.starwarsguide.ViewController.Fragments.StarShipsFragment


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_characters
    }
    private fun fragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_characters -> {
                return CharactersFragment()
            }
            /*R.id.navigation_planets -> {
                return PlanetsFragment()
            }*/
            R.id.navigation_starships -> {
                return StarShipsFragment()
            }
            /*R.id.navigation_vehicles -> {
                return VehiclesFragment()
            }*/
            R.id.navigation_species -> {
                return SpeciesFragment()
            }
            R.id.navigation_films -> {
                return FilmsFragment()
            }
        }
        return CharactersFragment()
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.setChecked(true)
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmentFor(item))
            .commit() > 0
    }
}
