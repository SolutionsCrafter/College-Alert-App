package com.example.collegealerts

import CalendarFragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.collegealerts.fragments.AlertFragment
import com.example.collegealerts.fragments.HomeFragment
import com.example.collegealerts.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fab:FloatingActionButton
    private lateinit var appBar:Toolbar
    private lateinit var calendarTollBar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fragmentAndNavBarInit()
        appBar = findViewById(R.id.appBar)
        calendarTollBar = findViewById(R.id.calendarToolbar)
        calendarTollBar.visibility = View.INVISIBLE
        appBar.visibility = View.VISIBLE

//        fab = findViewById<FloatingActionButton>(R.id.fbtn)
//        fab.setOnClickListener {
//            startActivity(Intent(this,addTask::class.java))
//        }

        // Create a new fragment instance
        val homeFragment = HomeFragment()

        // Retrieve data passed via intent
        val taskTitle = intent.getStringExtra("task_title")
        val taskDate = intent.getStringExtra("task_date")
        val taskTime = intent.getStringExtra("task_time")

        // Pass data using a Bundle
        val bundle = Bundle()
        bundle.putString("task_title", taskTitle)
        bundle.putString("task_date", taskDate)
        bundle.putString("task_time", taskTime)

        // Set arguments to the Fragment
        homeFragment.arguments = bundle

        // Replace or add the Fragment to the container
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_view, homeFragment)
            .commit()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private  fun fragmentAndNavBarInit(){
        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        navBar.itemRippleColor = null
        val homeFragment=HomeFragment()
        val calendarFragment=CalendarFragment()
        val alertFragment=AlertFragment()
        val settingsFragment=SettingsFragment()
        //val title = findViewById<TextView>(R.id.pageTitle)
        //val pageBackBtn = findViewById<ImageView>(R.id.pageBackBtn)

        val toolbar = findViewById<Toolbar>(R.id.appBar)
        val greetingText = findViewById<TextView>(R.id.greetingText)
        greetingText.text = "Hello, Channa 👋"


        navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home->{
                    calendarTollBar.visibility = View.INVISIBLE
                    appBar.visibility = View.VISIBLE
                    setCurrentFragment(homeFragment)
                    //title.text = "Home"
                    //fab.visibility = View.GONE
                    //pageBackBtn.visibility = View.GONE
                }
                R.id.menu_calendar->{
                    appBar.visibility = View.INVISIBLE
                    calendarTollBar.visibility = View.VISIBLE
                    setCurrentFragment(calendarFragment)
                    //title.text = "Calendar"
                    //fab.visibility = View.GONE
                    //pageBackBtn.visibility = View.VISIBLE

                }
                R.id.menu_alerts->{
                    calendarTollBar.visibility = View.GONE
                    appBar.visibility = View.GONE
                    setCurrentFragment(alertFragment)
                    //title.text = "Alerts"
                    //fab.visibility = View.GONE
                    //pageBackBtn.visibility = View.VISIBLE
                }
                R.id.menu_settings->{
                    calendarTollBar.visibility = View.GONE
                    appBar.visibility = View.GONE
                    setCurrentFragment(settingsFragment)
                    //title.text = "Settings"
                    //fab.visibility = View.GONE
                    //pageBackBtn.visibility = View.VISIBLE
                }

            }
            true
        }

        setCurrentFragment(homeFragment)

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag_view,fragment)
            commit()
        }

}