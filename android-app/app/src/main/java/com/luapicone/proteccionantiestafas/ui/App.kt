package com.luapicone.proteccionantiestafas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luapicone.proteccionantiestafas.ui.theme.Danger
import com.luapicone.proteccionantiestafas.ui.theme.Navy700
import com.luapicone.proteccionantiestafas.ui.theme.Orange500
import com.luapicone.proteccionantiestafas.ui.theme.Success
import com.luapicone.proteccionantiestafas.ui.theme.Warning
import android.content.Intent
import android.net.Uri

@Composable
fun ProteccionAntiestafasApp(initialSharedText: String?, viewModel: AppViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (uiState.isOnboardingCompleted) Screen.Home.route else Screen.Onboarding.route,
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                state = uiState,
                onNameChange = viewModel::updateOnboardingName,
                onLevelSelected = viewModel::updateOnboardingLevel,
                onBack = viewModel::previousOnboardingStep,
                onNext = viewModel::nextOnboardingStep,
                onFinish = {
                    viewModel.completeOnboarding()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, state = uiState, activeContact = viewModel.activeContact(uiState))
        }
        composable(Screen.Analyze.route) {
            AnalyzeMessageScreen(
                navController = navController,
                initialSharedText = initialSharedText,
                activeContactName = viewModel.activeContact(uiState)?.name,
                onAnalysisCompleted = viewModel::registerMessageAnalysis,
            )
        }
        composable(Screen.Help.route) {
            HelpScreen(
                navController = navController,
                activeContact = viewModel.activeContact(uiState),
                onHelpTriggered = viewModel::registerHelpRequest,
            )
        }
        composable(Screen.Contacts.route) {
            ContactsScreen(
                navController = navController,
                state = uiState,
                onSelect = viewModel::selectContact,
                onSave = viewModel::addOrUpdateContact,
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController,
                state = uiState,
                onLevelChange = viewModel::updateInterventionLevel,
                onNotificationsChange = viewModel::updateNotifications,
                onSuggestContactChange = viewModel::updateSuggestContact,
                onCallWarningsChange = viewModel::updateCallWarnings,
                onSharedAnalysisChange = viewModel::updateSharedAnalysis,
            )
        }
        composable(Screen.History.route) {
            HistoryScreen(
                navController = navController,
                state = uiState,
                onFilterChange = viewModel::updateFilter,
            )
        }
        composable(Screen.CallCheck.route) {
            CallCheckScreen(navController = navController, onRegister = viewModel::registerCallCheck)
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
        containerColor = MaterialTheme.colorScheme.background,
        content = content,
    )
}

@Composable
private fun OnboardingScreen(
    state: AppUiState,
    onNameChange: (String) -> Unit,
    onLevelSelected: (InterventionLevel) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    val onboarding = state.onboarding
    AppScaffold(title = "Configurar protección") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            StepBadge(step = onboarding.step + 1, total = 3)
            when (onboarding.step) {
                0 -> {
                    HeroCard(
                        title = "Protección Antiestafas",
                        body = "La app ayuda a detectar mensajes sospechosos, revisar llamadas riesgosas y pedir ayuda antes de actuar.",
                    )
                    FeatureCard("Mensajes", "Pegá o compartí un texto y la app te explica si hay señales de estafa.")
                    FeatureCard("Llamadas", "Mostramos alertas simples y chequeo post-llamada para no decidir bajo presión.")
                    FeatureCard("Ayuda", "Siempre podés escalar a un contacto de confianza con un toque.")
                    Button(onClick = onNext, modifier = Modifier.fillMaxWidth()) { Text("Continuar") }
                }

                1 -> {
                    Text("¿Cómo querés que te llamemos?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Esto nos ayuda a personalizar mensajes y alertas dentro de la app.")
                    OutlinedTextField(
                        value = onboarding.name,
                        onValueChange = onNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Nombre") },
                        singleLine = true,
                    )
                    Button(onClick = onNext, modifier = Modifier.fillMaxWidth(), enabled = onboarding.name.isNotBlank()) {
                        Text("Continuar")
                    }
                    OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
                }

                else -> {
                    Text("Elegí el nivel de intervención", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Después lo podés cambiar. La idea es adaptar la protección a cada persona.")
                    InterventionLevel.entries.forEach { level ->
                        SelectableCard(
                            title = level.label,
                            body = level.description,
                            selected = onboarding.interventionLevel == level,
                            onClick = { onLevelSelected(level) },
                        )
                    }
                    Button(onClick = onFinish, modifier = Modifier.fillMaxWidth()) { Text("Entrar a la app") }
                    OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(navController: NavHostController, state: AppUiState, activeContact: TrustedContactUi?) {
    AppScaffold(title = "Inicio") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                HeroCard(
                    title = "Hola, ${state.userName.ifBlank { "usuario" }}",
                    body = "Modo actual: ${state.interventionLevel.label}. ${activeContact?.name?.let { "Tu contacto principal es $it." } ?: "Todavía no hay contacto principal definido."}",
                )
            }
            item {
                PrimaryActionCard("Analizar mensaje", "Pegá o compartí un mensaje sospechoso para recibir una recomendación clara.") {
                    navController.navigate(Screen.Analyze.route)
                }
            }
            item {
                PrimaryActionCard("Chequeo post-llamada", "Si una llamada te apuró o te pidió dinero, revisala antes de actuar.") {
                    navController.navigate(Screen.CallCheck.route)
                }
            }
            item {
                PrimaryActionCard("Pedir ayuda", "Escalá rápido a un contacto de confianza sin quedarte solo frente a la duda.") {
                    navController.navigate(Screen.Help.route)
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SmallNavCard("Contactos", Modifier.weight(1f)) { navController.navigate(Screen.Contacts.route) }
                    SmallNavCard("Configuración", Modifier.weight(1f)) { navController.navigate(Screen.Settings.route) }
                }
            }
            item {
                SmallNavCard("Historial", Modifier.fillMaxWidth()) { navController.navigate(Screen.History.route) }
            }
        }
    }
}

@Composable
private fun AnalyzeMessageScreen(
    navController: NavHostController,
    initialSharedText: String?,
    activeContactName: String?,
    onAnalysisCompleted: (AnalysisResultUi) -> Unit,
) {
    var message by rememberSaveable { mutableStateOf(initialSharedText.orEmpty()) }
    var result by remember { mutableStateOf<AnalysisResultUi?>(null) }

    LaunchedEffect(initialSharedText) {
        if (!initialSharedText.isNullOrBlank() && result == null) {
            result = analyzeMessage(initialSharedText)
        }
    }

    AppScaffold(title = "Analizar mensaje") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text("Pegá el mensaje completo o compartilo desde otra app. La evaluación es local y sirve como ayuda, no como verdad absoluta.")
            }
            item {
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 7,
                    label = { Text("Mensaje sospechoso") },
                )
            }
            item {
                Button(
                    onClick = {
                        val analysis = analyzeMessage(message)
                        result = analysis
                        onAnalysisCompleted(analysis)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = message.isNotBlank(),
                ) {
                    Text("Analizar ahora")
                }
            }
            result?.let { analysis ->
                item {
                    AnalysisCard(analysis = analysis)
                }
                item {
                    ActionChecklist(actions = analysis.suggestedActions)
                }
                item {
                    Button(onClick = { navController.navigate(Screen.Help.route) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Avisar a ${activeContactName ?: "mi contacto"}")
                    }
                }
            }
        }
    }
}

@Composable
private fun HelpScreen(
    navController: NavHostController,
    activeContact: TrustedContactUi?,
    onHelpTriggered: () -> Unit,
) {
    val context = LocalContext.current

    AppScaffold(title = "Pedir ayuda") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            HeroCard(
                title = "No actúes solo",
                body = "Si alguien te pide plata, códigos o actuar urgente, primero verificá con una persona de confianza.",
            )
            activeContact?.let {
                FeatureCard(it.name, "${it.relationship} · ${it.phone}")
            }
            Button(onClick = {
                onHelpTriggered()
                val message = Uri.encode("Necesito ayuda. Recibí una situación sospechosa y quiero verificar antes de actuar.")
                val phone = activeContact?.phone?.filter { it.isDigit() || it == '+' } ?: ""
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${phone.replace("+", "")}?text=$message"))
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()) { Text("Enviar alerta por WhatsApp") }
            OutlinedButton(onClick = {
                onHelpTriggered()
                val phone = activeContact?.phone ?: ""
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:$phone")
                    putExtra("sms_body", "Necesito ayuda. Recibí una situación sospechosa y quiero verificar antes de actuar.")
                }
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()) { Text("Enviar alerta por SMS") }
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
        }
    }
}

@Composable
private fun ContactsScreen(
    navController: NavHostController,
    state: AppUiState,
    onSelect: (String) -> Unit,
    onSave: (String, String, String) -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var relation by rememberSaveable { mutableStateOf("") }

    AppScaffold(title = "Contactos de confianza") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                Text("Elegí un contacto principal o agregá uno nuevo. En el MVP se permiten hasta dos contactos.")
            }
            items(state.contacts) { contact ->
                SelectableCard(
                    title = contact.name,
                    body = "${contact.relationship} · ${contact.phone}",
                    selected = state.selectedContactId == contact.id || (state.selectedContactId == null && state.contacts.first().id == contact.id),
                    onClick = { onSelect(contact.id) },
                )
            }
            item { HorizontalDivider() }
            item {
                Text("Agregar o reemplazar contacto", fontWeight = FontWeight.Bold)
            }
            item {
                OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Nombre") })
            }
            item {
                OutlinedTextField(value = phone, onValueChange = { phone = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Teléfono") })
            }
            item {
                OutlinedTextField(value = relation, onValueChange = { relation = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Relación") })
            }
            item {
                Button(onClick = {
                    onSave(name, phone, relation)
                    name = ""
                    phone = ""
                    relation = ""
                }, modifier = Modifier.fillMaxWidth()) { Text("Guardar contacto") }
            }
            item {
                OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    navController: NavHostController,
    state: AppUiState,
    onLevelChange: (InterventionLevel) -> Unit,
    onNotificationsChange: (Boolean) -> Unit,
    onSuggestContactChange: (Boolean) -> Unit,
    onCallWarningsChange: (Boolean) -> Unit,
    onSharedAnalysisChange: (Boolean) -> Unit,
) {
    AppScaffold(title = "Configuración") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                Text("Nivel de intervención", fontWeight = FontWeight.Bold)
            }
            items(InterventionLevel.entries) { level ->
                SelectableCard(
                    title = level.label,
                    body = level.description,
                    selected = state.interventionLevel == level,
                    onClick = { onLevelChange(level) },
                )
            }
            item { HorizontalDivider() }
            item {
                SwitchSetting("Notificaciones activas", state.settings.notificationsEnabled, onNotificationsChange)
            }
            item {
                SwitchSetting("Sugerir contacto en riesgo alto", state.settings.suggestContactOnHighRisk, onSuggestContactChange)
            }
            item {
                SwitchSetting("Mostrar advertencias de llamadas", state.settings.showCallWarnings, onCallWarningsChange)
            }
            item {
                SwitchSetting("Permitir análisis de texto compartido", state.settings.allowSharedTextAnalysis, onSharedAnalysisChange)
            }
            item {
                FeatureCard("Qué hace hoy la app", "Analiza texto de forma local, ofrece chequeo post-llamada y ayuda a pedir apoyo. Todavía no escucha audio ni se integra profundamente con WhatsApp.")
            }
            item {
                OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
            }
        }
    }
}

@Composable
private fun HistoryScreen(
    navController: NavHostController,
    state: AppUiState,
    onFilterChange: (EventType?) -> Unit,
) {
    val filtered = state.activeFilter?.let { type -> state.recentEvents.filter { it.type == type } } ?: state.recentEvents

    AppScaffold(title = "Historial") { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = state.activeFilter == null,
                        onClick = { onFilterChange(null) },
                        label = { Text("Todo") },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = Orange500.copy(alpha = 0.18f))
                    )
                    EventType.entries.forEach { type ->
                        FilterChip(
                            selected = state.activeFilter == type,
                            onClick = { onFilterChange(type) },
                            label = { Text(type.label) },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = Orange500.copy(alpha = 0.18f))
                        )
                    }
                }
            }
            items(filtered) { event ->
                FeatureCard(event.title, "${event.detail}\n${event.timestamp}")
            }
            item {
                OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
            }
        }
    }
}

@Composable
private fun CallCheckScreen(navController: NavHostController, onRegister: (String) -> Unit) {
    var requestedMoney by rememberSaveable { mutableStateOf(false) }
    var requestedCode by rememberSaveable { mutableStateOf(false) }
    var claimedBank by rememberSaveable { mutableStateOf(false) }
    var claimedFamily by rememberSaveable { mutableStateOf(false) }
    var pressure by rememberSaveable { mutableStateOf(false) }

    AppScaffold(title = "Chequeo post-llamada") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            HeroCard(
                title = "¿Qué pasó en la llamada?",
                body = "Marcá las señales que aparecieron. Si hubo presión o pedido de plata, no actúes todavía.",
            )
            SwitchSetting("Te pidieron dinero o transferencia", requestedMoney) { requestedMoney = it }
            SwitchSetting("Te pidieron código, token o clave", requestedCode) { requestedCode = it }
            SwitchSetting("Dijeron ser del banco", claimedBank) { claimedBank = it }
            SwitchSetting("Dijeron ser un familiar", claimedFamily) { claimedFamily = it }
            SwitchSetting("Te apuraron para actuar ya", pressure) { pressure = it }
            Button(onClick = {
                val summary = buildList {
                    if (requestedMoney) add("pedido de dinero")
                    if (requestedCode) add("pedido de código")
                    if (claimedBank) add("supuesto banco")
                    if (claimedFamily) add("supuesto familiar")
                    if (pressure) add("presión y urgencia")
                }.joinToString().ifBlank { "sin señales fuertes" }
                onRegister(summary)
                navController.navigate(Screen.Help.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar chequeo y pedir ayuda")
            }
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Volver")
            }
        }
    }
}

@Composable
private fun HeroCard(title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Navy700),
        shape = RoundedCornerShape(24.dp),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun PrimaryActionCard(title: String, body: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Orange500.copy(alpha = 0.14f)),
        shape = RoundedCornerShape(24.dp),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SmallNavCard(title: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Box(Modifier.padding(18.dp), contentAlignment = Alignment.Center) {
            Text(title, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun AnalysisCard(analysis: AnalysisResultUi) {
    val accent = when (analysis.riskColor) {
        "danger" -> Danger
        "warning" -> Warning
        else -> Success
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = accent.copy(alpha = 0.14f)),
        shape = RoundedCornerShape(24.dp),
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.size(14.dp).background(accent, CircleShape))
                Text(analysis.riskLabel, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
            Text("Score de riesgo: ${analysis.score}/100")
            Text("Señales detectadas", fontWeight = FontWeight.SemiBold)
            analysis.signals.forEach { Text("• $it") }
            Text("Recomendación", fontWeight = FontWeight.SemiBold)
            Text(analysis.recommendation)
        }
    }
}

@Composable
private fun ActionChecklist(actions: List<String>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Qué hacer ahora", fontWeight = FontWeight.Bold)
            actions.forEach { Text("• $it") }
        }
    }
}

@Composable
private fun SelectableCard(title: String, body: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Orange500.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, if (selected) Orange500 else Color.Transparent),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun FeatureCard(title: String, body: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SwitchSetting(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(title, modifier = Modifier.weight(1f))
            Spacer(Modifier.size(12.dp))
            Switch(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}

@Composable
private fun StepBadge(step: Int, total: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .background(Orange500.copy(alpha = 0.16f), RoundedCornerShape(999.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text("Paso $step de $total", fontWeight = FontWeight.Bold)
        }
    }
}
