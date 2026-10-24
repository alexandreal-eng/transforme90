package com.transforme90.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.transforme90.ui.screens.HomeScreen
import com.transforme90.ui.screens.ProgramScreen
import com.transforme90.ui.screens.UpcomingScreen

enum class Tab(val route: String, val label: String, val icon: ImageVector) {
    Home("accueil", "Accueil", Icons.Filled.Home),
    Program("programme", "Programme", Icons.Filled.DateRange),
    Routine("routine", "Routine", Icons.Filled.CheckCircle),
    Mental("mental", "Mental", Icons.Filled.Favorite),
    More("plus", "Plus", Icons.Filled.Menu)
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                Tab.values().forEach { tab ->
                    NavigationBarItem(
                        selected = current == tab.route,
                        onClick = {
                            nav.navigate(tab.route) {
                                popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(nav, startDestination = Tab.Home.route, modifier = Modifier.padding(padding)) {
            composable(Tab.Home.route) { HomeScreen() }
            composable(Tab.Program.route) { ProgramScreen() }
            composable(Tab.Routine.route) { UpcomingScreen("Ma routine", "étape 3") }
            composable(Tab.Mental.route) { UpcomingScreen("Mental", "étape 4") }
            composable(Tab.More.route) { UpcomingScreen("Finances, statistiques, paramètres", "étape 5") }
        }
    }
}
