package com.aashu.kai.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray

class UserPreferencesRepository(
    context: Context
) {

    private val dataStore = context.dataStore

    companion object {

        private val ONBOARDING_COMPLETED =
            booleanPreferencesKey("onboarding_completed")

        private val NAME =
            stringPreferencesKey("name")

        private val AGE =
            stringPreferencesKey("age")

        private val PHONE =
            stringPreferencesKey("phone")

        private val TRAITS =
            stringPreferencesKey("traits")
    }

    val userPreferences: Flow<UserPreferences> =
        dataStore.data.map { preferences ->

            val traitsJson = preferences[TRAITS] ?: "[]"

            val traits = mutableListOf<String>()
            val jsonArray = JSONArray(traitsJson)

            for (i in 0 until jsonArray.length()) {
                traits.add(jsonArray.getString(i))
            }

            UserPreferences(
                onboardingCompleted = preferences[ONBOARDING_COMPLETED] ?: false,
                userProfile = com.aashu.kai.model.UserProfile(
                    name = preferences[NAME] ?: "",
                    age = preferences[AGE] ?: "",
                    phone = preferences[PHONE] ?: "",
                    personalityTraits = traits
                )
            )
        }

    suspend fun saveUserPreferences(
        preferences: UserPreferences
    ) {

        dataStore.edit { prefs ->

            prefs[ONBOARDING_COMPLETED] =
                preferences.onboardingCompleted

            prefs[NAME] =
                preferences.userProfile.name

            prefs[AGE] =
                preferences.userProfile.age

            prefs[PHONE] =
                preferences.userProfile.phone

            prefs[TRAITS] =
                JSONArray(preferences.userProfile.personalityTraits).toString()
        }
    }
}