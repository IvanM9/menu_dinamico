package com.ivanmanzaba.men_dinmico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject


class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var navigationView:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this);

        cargarItems();
    }

    fun cargarItems(){

        var dinamic_menu = navigationView.menu
        val usuario = navigationView.getHeaderView(0).findViewById<TextView>(R.id.txtUsuario)
        val avatar = navigationView.getHeaderView(0).findViewById<CircleImageView>(R.id.profile_image)
        val bundle = this.getIntent().getExtras()
        Picasso.get().load(bundle?.getString("avatar")).into(avatar)
        usuario.text=bundle?.getString("user")
        if (bundle != null) {
            bundle.getStringArrayList("items")?.forEach {
                dinamic_menu.add(it)
            }
        }

    }

    var drawerLayout: DrawerLayout? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {

                drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

                    drawerLayout?.openDrawer(GravityCompat.START)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun showFragment(fragment: Fragment){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.content_frame,fragment)
        fram.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            else -> showFragment(fragmentExample())
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
data class ejemplo(
    val rol:String,
    val items: Array<String>
)