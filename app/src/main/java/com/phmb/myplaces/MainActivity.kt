package com.phmb.myplaces

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.content.Context
import android.util.Log
import com.phmb.myplaces.ui.dialogs.categories.CategoriesAdapter
import com.phmb.myplaces.ui.dialogs.categories.CustomCategoriesDialog
import com.phmb.myplaces.ui.radar.RadarFragment
import com.phmb.myplaces.ui.list.ListFragment
import com.phmb.myplaces.ui.profile.ProfileFragment


class MainActivity : AppCompatActivity(), CategoriesAdapter.RecyclerViewItemClickListener {

    internal var customDialog: CustomCategoriesDialog? = null
    var profileName: String = ""
    var profileId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_radar, R.id.navigation_list, R.id.navigation_profile)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        profileName = intent.getStringExtra(EXTRA_NAME)
        profileId = intent.getStringExtra(EXTRA_ID)

        Log.d("MainActivity","Name: ${profileName}")
        Log.d("MainActivity","Id: ${profileId}")

        RadarFragment.newInstance(profileId)
    }

    companion object {

        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_ID = "EXTRA_ID"

        fun getStartIntent(context: Context, name: String, id: String): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_ID, id)
            }
        }
    }

    fun categoriesDialog() {

        val items = arrayOf("Todos", "Aeroportos", "Restaurantes", "Baladas", "Supermercados", "Shopping centers")

        val categoriesAdapter = CategoriesAdapter(items, this)

        customDialog = CustomCategoriesDialog(this@MainActivity, categoriesAdapter)

        customDialog!!.show()
        customDialog!!.setCanceledOnTouchOutside(false)
    }

    override fun clickOnItem(data: String) {

        if (customDialog != null) {
            customDialog!!.dismiss()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.filter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_categories_filter) {
            categoriesDialog()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

        when (menuItem.itemId) {
            R.id.navigation_radar -> {
                val fragment = RadarFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                val fragment = ListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
