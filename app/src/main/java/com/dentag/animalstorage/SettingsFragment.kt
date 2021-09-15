package com.dentag.animalstorage

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.prefs)

        val okButton = findPreference<Preference>(getString(R.string.exitPrefsKey))
        okButton?.setOnPreferenceClickListener {
            parentFragmentManager.popBackStack()
            true
        }
    }
}
