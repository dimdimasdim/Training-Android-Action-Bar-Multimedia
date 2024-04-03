package com.dimas.actionbarmultimedia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dimas.actionbarmultimedia.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var badgeTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        initializeBadgeProfile(menu)

        return true
    }

    private fun initializeBadgeProfile(menu: Menu?) {
        val menuItem = menu?.findItem(R.id.action_profile)
        val actionView = menuItem?.actionView
        val badgeTextView = actionView!!.findViewById<TextView>(R.id.badge)
        badgeTextView.text = "3"
        badgeTextView.visibility = View.VISIBLE
        actionView.setOnClickListener {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_history -> {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun initializeToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }
    }
}