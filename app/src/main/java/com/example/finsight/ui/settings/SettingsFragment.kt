package com.example.finsight.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.finsight.R
import androidx.core.content.edit

class SettingsFragment : Fragment() {

    private lateinit var switchDarkMode: Switch

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        switchDarkMode = view.findViewById(R.id.switchDarkMode)

        val prefs = requireContext().getSharedPreferences("settings", 0)
        val darkEnabled = prefs.getBoolean("dark_mode", false)
        switchDarkMode.isChecked = darkEnabled

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit { putBoolean("dark_mode", isChecked) }
            setDarkMode(isChecked)
        }

        return view
    }

    private fun setDarkMode(enabled: Boolean) {
        val mode = if (enabled)
            AppCompatDelegate.MODE_NIGHT_YES
        else
            AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
        requireActivity().recreate() //  Tambahan penting biar langsung reload UI
    }
}
