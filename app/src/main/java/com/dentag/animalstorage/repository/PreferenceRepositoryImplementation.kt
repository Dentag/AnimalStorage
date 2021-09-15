package com.dentag.animalstorage.repository

import androidx.preference.PreferenceManager
import com.dentag.App
import com.dentag.animalstorage.R

class PreferenceRepositoryImplementation : PreferenceRepository {
    private val context = App.instance
    private val preferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun checkIfRoom(): Boolean {
        return preferences.getBoolean(context.getString(R.string.roomKey), true)
    }

    override fun getSortBy(): String? {
        return preferences.getString(
            context.getString(R.string.filterKey),
            context.getString(R.string.nameField)
        )
    }
}
