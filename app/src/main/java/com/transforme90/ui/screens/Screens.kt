package com.transforme90.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.transforme90.ui.theme.Gold
import com.transforme90.ui.theme.Navy
import com.transforme90.ui.theme.NavySurface

@Composable
fun HomeScreen(vm: HomeViewModel = viewModel()) {
    val state by vm.state.collectAsStateWithLifecycle()
    Column(
        Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("TRANSFORME 90", style = MaterialTheme.typography.headlineMedium, color = Gold)
        Text(
            "90 jours pour construire une meilleure version de soi.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Jour ${state.currentDay} sur 90", style = MaterialTheme.typography.titleLarge)
                LinearProgressIndicator(
                    progress = { state.completed / 90f },
                    modifier = Modifier.fillMaxWidth(),
                    color = Gold
                )
                Text("${state.completed} journée(s) terminée(s)", style = MaterialTheme.typography.bodyMedium)
                if (state.currentDone) {
                    Text("Journée ${state.currentDay} terminée. Bravo !", color = Gold)
                } else {
                    Button(onClick = { vm.completeDay(state.currentDay) }) {
                        Text("Terminer la journée ${state.currentDay}")
                    }
                }
            }
        }
    }
}

@Composable
fun ProgramScreen(vm: HomeViewModel = viewModel()) {
    val state by vm.state.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Mon programme", style = MaterialTheme.typography.headlineSmall, color = Gold)
        Spacer(Modifier.height(4.dp))
        Text(
            "Phase 1 : jours 1 à 30  ·  Phase 2 : 31 à 60  ·  Phase 3 : 61 à 90",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items((1..90).toList()) { day ->
                val done = day in state.doneDays
                val current = day == state.currentDay
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (done) Gold else NavySurface,
                    border = if (current) BorderStroke(2.dp, Gold) else null,
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            "$day",
                            color = if (done) Navy else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UpcomingScreen(title: String, step: String) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.headlineSmall, color = Gold)
        Spacer(Modifier.height(8.dp))
        Text(
            "Cette section sera développée à l'$step.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
