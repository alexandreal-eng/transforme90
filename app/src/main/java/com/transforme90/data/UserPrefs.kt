package com.transforme90.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

private val Context.dataStore by preferencesDataStore(name = "settings")

class UserPrefs(private val context: Context) {
    private val startKey = longPreferencesKey("start_epoch_day")

    val startEpochDay: Flow<Long?> = context.dataStore.data.map { it[startKey] }

    /** Fixe le jour 1 à la première ouverture ; ne change plus ensuite. */
    suspend fun ensureStart() {
        context.dataStore.edit { prefs ->
            if (prefs[startKey] == null) prefs[startKey] = LocalDate.now().toEpochDay()
        }
    }
}
