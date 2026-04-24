package com.luapicone.proteccionantiestafas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ProteccionAntiestafasApp(initialSharedText: String?) {
    val navController = rememberNavController()
    val appState = remember { AppUiState() }

    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route,
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onContinue = { navController.navigate(Screen.Home.route) })
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, appState = appState)
        }
        composable(Screen.Analyze.route) {
            AnalyzeMessageScreen(navController = navController, initialSharedText = initialSharedText, appState = appState)
        }
        composable(Screen.Help.route) {
            HelpScreen(navController = navController, appState = appState)
        }
        composable(Screen.Contacts.route) {
            ContactsScreen(navController = navController, appState = appState)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController, appState = appState)
        }
        composable(Screen.History.route) {
            HistoryScreen(navController = navController, appState = appState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScaffold(title: String, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },
        content = content,
    )
}

@Composable
private fun OnboardingScreen(onContinue: () -> Unit) {
    AppScaffold(title = "Bienvenida") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Protección Antiestafas", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Te ayudamos a detectar mensajes y llamadas sospechosas antes de actuar.")
            FeatureCard("Analizá mensajes", "Pegá o compartí contenido sospechoso y recibí una recomendación simple.")
            FeatureCard("Pedí ayuda rápido", "Contactá a una persona de confianza sin perder tiempo.")
            FeatureCard("Recibí alertas claras", "La app muestra advertencias fáciles de entender para personas no técnicas.")
            Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) {
                Text("Empezar")
            }
        }
    }
}

@Composable
private fun HomeScreen(navController: NavHostController, appState: AppUiState) {
    AppScaffold(title = "Inicio") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Modo actual: ${appState.interventionLevel}", fontWeight = FontWeight.Bold)
                    Text("Contacto de confianza: ${appState.contact.name} (${appState.contact.relationship})")
                }
            }
            Button(onClick = { navController.navigate(Screen.Analyze.route) }, modifier = Modifier.fillMaxWidth()) { Text("Analizar mensaje") }
            Button(onClick = { navController.navigate(Screen.Help.route) }, modifier = Modifier.fillMaxWidth()) { Text("Pedir ayuda") }
            OutlinedButton(onClick = { navController.navigate(Screen.Contacts.route) }, modifier = Modifier.fillMaxWidth()) { Text("Contactos de confianza") }
            OutlinedButton(onClick = { navController.navigate(Screen.Settings.route) }, modifier = Modifier.fillMaxWidth()) { Text("Configuración") }
            OutlinedButton(onClick = { navController.navigate(Screen.History.route) }, modifier = Modifier.fillMaxWidth()) { Text("Historial") }
        }
    }
}

@Composable
private fun AnalyzeMessageScreen(navController: NavHostController, initialSharedText: String?, appState: AppUiState) {
    var message by rememberSaveable { mutableStateOf(initialSharedText.orEmpty()) }
    var result by remember(message) { mutableStateOf(if (message.isNotBlank()) analyzeMessage(message) else null) }

    AppScaffold(title = "Analizar mensaje") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text("Pegá o compartí un mensaje sospechoso para evaluarlo.")
            }
            item {
                OutlinedTextField(
                    value = message,
                    onValueChange = {
                        message = it
                        result = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6,
                    label = { Text("Mensaje sospechoso") },
                )
            }
            item {
                Button(
                    onClick = { result = analyzeMessage(message) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Analizar")
                }
            }
            result?.let { analysis ->
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(analysis.riskLabel, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            Text("Score: ${analysis.score}/100")
                            Text("Señales detectadas:", fontWeight = FontWeight.SemiBold)
                            analysis.signals.forEach { Text("• $it") }
                            Text("Recomendación:", fontWeight = FontWeight.SemiBold)
                            Text(analysis.recommendation)
                            Button(onClick = { navController.navigate(Screen.Help.route) }, modifier = Modifier.fillMaxWidth()) {
                                Text("Avisar a ${appState.contact.name}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HelpScreen(navController: NavHostController, appState: AppUiState) {
    AppScaffold(title = "Pedir ayuda") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text("Si no estás seguro, no actúes solo.")
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Contacto principal", fontWeight = FontWeight.Bold)
                    Text(appState.contact.name)
                    Text(appState.contact.phone)
                    Text(appState.contact.relationship)
                }
            }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) { Text("Enviar alerta por WhatsApp") }
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) { Text("Enviar alerta por SMS") }
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
        }
    }
}

@Composable
private fun ContactsScreen(navController: NavHostController, appState: AppUiState) {
    AppScaffold(title = "Contactos de confianza") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FeatureCard(appState.contact.name, "${appState.contact.relationship} · ${appState.contact.phone}")
            OutlinedButton(onClick = { }, modifier = Modifier.fillMaxWidth()) { Text("Agregar segundo contacto") }
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
        }
    }
}

@Composable
private fun SettingsScreen(navController: NavHostController, appState: AppUiState) {
    AppScaffold(title = "Configuración") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FeatureCard("Nivel de intervención", appState.interventionLevel)
            FeatureCard("Alertas", "Notificaciones activas y sugerencia de contacto habilitada.")
            FeatureCard("Privacidad", "La app analiza contenido que el usuario pega o comparte manualmente.")
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
        }
    }
}

@Composable
private fun HistoryScreen(navController: NavHostController, appState: AppUiState) {
    AppScaffold(title = "Historial") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(appState.recentEvents) { event ->
                FeatureCard(event, "Registro simple del MVP. Más adelante tendrá fecha, score y detalle.")
            }
            item {
                OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Volver")
                }
            }
        }
    }
}

@Composable
private fun FeatureCard(title: String, body: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(body)
        }
    }
}
