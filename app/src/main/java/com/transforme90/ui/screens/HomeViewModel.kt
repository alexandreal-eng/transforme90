package com.transforme90.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.transforme90.data.AppDatabase
import com.transforme90.data.DayProgress
import com.transforme90.data.UserPrefs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

data class ProgramState(
    val currentDay: Int = 1,
    val doneDays: Set<Int> = emptySet()
) {
    val completed: Int get() = doneDays.size
    val currentDone: Boolean get() = currentDay in doneDays
}

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.get(app)
    private val prefs = UserPrefs(app)

    val state: StateFlow<ProgramState> = combine(
        prefs.startEpochDay,
        db.dayProgressDao().observeAll()
    ) { start, progress ->
        // Une journée manquée ne bloque rien : le jour courant suit simplement le calendrier.
        val day = if (start == null) 1
        else (LocalDate.now().toEpochDay() - start + 1).toInt().coerceIn(1, 90)
        ProgramState(day, progress.filter { it.status == "done" }.map { it.day }.toSet())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ProgramState())

    init {
        viewModelScope.launch { prefs.ensureStart() }
    }

    fun completeDay(day: Int) {
        viewModelScope.launch {
            db.dayProgressDao().upsert(
                DayProgress(day = day, status = "done", completedAt = System.currentTimeMillis())
            )
        }
    }
}
