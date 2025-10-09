package com.example.finsight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finsight.repo.NoteRepository
import com.example.finsight.ui.dashboard.DashboardFragment
import com.example.finsight.ui.notes.NotesFragment
import com.example.finsight.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // buat layout sesuai instruksi

        // init repository (SharedPreferences) with app context
        NoteRepository.init(applicationContext)

        replaceFragment(DashboardFragment())

        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_dashboard -> replaceFragment(DashboardFragment())
                R.id.nav_notes -> replaceFragment(NotesFragment())
                R.id.nav_settings -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
