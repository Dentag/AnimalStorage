package com.dentag.animalstorage.repository

interface PreferenceRepository {
    fun checkIfRoom(): Boolean
    fun getSortBy(): String?
}
