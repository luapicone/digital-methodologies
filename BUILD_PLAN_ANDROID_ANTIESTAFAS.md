# Build Plan - Android Antiestafas

## 1. Flujo de usuario completo

## 1.1. Flujo A - Primera instalación

### Objetivo
Dejar a la persona protegida con la menor fricción posible.

### Pasos
1. Pantalla de bienvenida
2. Explicación simple de qué hace la app
3. Aceptación de permisos básicos
4. Configuración del contacto de confianza
5. Selección del nivel de intervención
6. Confirmación final
7. Llegada a Home

### Resultado esperado
El usuario termina el onboarding con:
- la app lista para analizar mensajes;
- al menos un contacto de confianza cargado;
- un nivel de intervención elegido;
- permisos mínimos concedidos.

---

## 1.2. Flujo B - Analizar un mensaje sospechoso

### Disparador
El usuario recibe un mensaje raro en WhatsApp, SMS o cualquier otro canal.

### Recorrido ideal
1. El usuario copia el texto o lo comparte a la app
2. La app lo recibe en la pantalla de análisis
3. El motor calcula score de riesgo
4. La app muestra:
   - nivel de riesgo
   - señales detectadas
   - recomendación clara
5. El usuario puede:
   - cerrar
   - marcar como falso positivo
   - pedir ayuda
   - avisar a un contacto de confianza

### Resultado esperado
La app interrumpe la reacción impulsiva y obliga a una verificación antes de actuar.

---

## 1.3. Flujo C - Llamada sospechosa

### Disparador
Entra una llamada no agendada o catalogada como riesgosa.

### Recorrido ideal
1. Android detecta la llamada entrante
2. La app evalúa si corresponde advertencia
3. Se muestra una alerta rápida:
   - número desconocido
   - número reportado
   - recordatorio de no compartir datos ni transferir dinero
4. Al terminar la llamada, la app puede mostrar una pantalla post-llamada:
   - ¿te pidieron dinero?
   - ¿te pidieron código?
   - ¿dijeron ser del banco o un familiar?
5. Si hay señales de riesgo, se sugiere contactar al familiar o registrar incidente

### Resultado esperado
Reducir la probabilidad de que el usuario obedezca durante la llamada sin chequear.

---

## 1.4. Flujo D - Pedir ayuda a contacto de confianza

### Disparador
El usuario no está seguro o siente presión.

### Recorrido ideal
1. Toca botón “Pedir ayuda”
2. La app muestra opciones:
   - llamar al contacto de confianza
   - enviar alerta por WhatsApp
   - enviar alerta por SMS
3. Se dispara mensaje predefinido con contexto breve
4. La app registra el evento

### Resultado esperado
El usuario nunca queda solo frente a una situación de presión.

---

## 1.5. Flujo E - Configuración del nivel de intervención

### Objetivo
Adaptar la protección al perfil de cada persona.

### Opciones
- Solo alertar
- Alertar + sugerir acción
- Alertar + permitir aviso rápido a contacto
- Alertar + sugerir aviso automático al contacto

### Resultado esperado
La app se ajusta al nivel de autonomía o vulnerabilidad de cada usuario.

---

# 2. Wireframes funcionales

## 2.1. Pantalla 1 - Bienvenida

### Contenido
- título simple
- subtítulo claro
- botón “Empezar”

### Copy sugerido
**Protección contra estafas**  
Te ayudamos a detectar mensajes y llamadas sospechosas antes de que actúes.

---

## 2.2. Pantalla 2 - Qué hace la app

### Contenido
- bloque 1: analiza mensajes sospechosos
- bloque 2: alerta ante llamadas riesgosas
- bloque 3: te conecta con una persona de confianza

### CTA
- Continuar

---

## 2.3. Pantalla 3 - Permisos

### Permisos iniciales posibles
- notificaciones
- acceso de compartir contenido
- lectura básica de llamadas según Android
- contactos, solo si se necesita para seleccionar persona de confianza

### Regla UX
Explicar cada permiso en lenguaje simple antes de pedirlo.

---

## 2.4. Pantalla 4 - Contacto de confianza

### Contenido
- nombre
- teléfono
- relación
- botón guardar

### Opción
Agregar segundo contacto opcional.

---

## 2.5. Pantalla 5 - Nivel de intervención

### Opciones visuales
- Modo suave
- Modo acompañado
- Modo reforzado

### Regla UX
No usar lenguaje técnico. Mostrar consecuencias concretas.

Ejemplo:
- **Modo suave:** solo te avisa
- **Modo acompañado:** te avisa y te sugiere pedir ayuda
- **Modo reforzado:** te avisa fuerte y prioriza contacto con tu persona de confianza

---

## 2.6. Pantalla 6 - Home

### Componentes principales
- botón grande: **Analizar mensaje**
- botón grande: **Pedir ayuda**
- botón grande: **Ver llamada sospechosa**
- acceso a historial
- acceso a configuración

### Regla UX
La home tiene que ser entendible en 3 segundos.

---

## 2.7. Pantalla 7 - Analizador de mensaje

### Componentes
- campo de texto pegado o compartido
- botón analizar
- tarjeta de resultado
- señales detectadas
- acción sugerida
- botón “Avisar a mi contacto”

### Estados posibles
- riesgo bajo
- riesgo medio
- riesgo alto

---

## 2.8. Pantalla 8 - Resultado de análisis

### Ejemplo de salida
**Riesgo alto**  
Este mensaje parece una estafa.

### Señales detectadas
- pedido urgente
- menciona transferencia
- dice ser familiar
- quiere que actúes sin verificar

### Acciones
- no responder ahora
- verificar por otro medio
- avisar a contacto de confianza

---

## 2.9. Pantalla 9 - Ayuda rápida

### Componentes
- botón llamar a contacto
- botón mandar mensaje de alerta
- botón copiar mensaje sospechoso
- botón volver

---

## 2.10. Pantalla 10 - Historial

### Lista de eventos
- mensajes analizados
- alertas disparadas
- llamadas marcadas como sospechosas
- fecha y resultado

---

## 2.11. Pantalla 11 - Configuración

### Secciones
- nivel de intervención
- contactos de confianza
- permisos
- textos de alerta
- privacidad

---

# 3. Modelo de datos inicial

## 3.1. Entidad User

```json
{
  "id": "uuid",
  "name": "string",
  "phone": "string",
  "ageRange": "30-45 | 46-60 | 60+",
  "interventionLevel": "soft | assisted | reinforced",
  "createdAt": "timestamp",
  "updatedAt": "timestamp"
}
```

## 3.2. Entidad TrustedContact

```json
{
  "id": "uuid",
  "userId": "uuid",
  "name": "string",
  "phone": "string",
  "relationship": "string",
  "priority": 1
}
```

## 3.3. Entidad MessageAnalysis

```json
{
  "id": "uuid",
  "userId": "uuid",
  "source": "whatsapp | sms | manual | other",
  "inputText": "string",
  "riskScore": 0,
  "riskLevel": "low | medium | high",
  "detectedSignals": ["urgency", "bank", "family", "transfer", "otp"],
  "recommendedAction": "string",
  "createdAt": "timestamp"
}
```

## 3.4. Entidad CallAlertEvent

```json
{
  "id": "uuid",
  "userId": "uuid",
  "phoneNumber": "string",
  "isKnownContact": false,
  "isReportedRisk": false,
  "warningShown": true,
  "postCallFlags": ["money_request", "bank_claim", "family_claim"],
  "createdAt": "timestamp"
}
```

## 3.5. Entidad HelpRequest

```json
{
  "id": "uuid",
  "userId": "uuid",
  "trustedContactId": "uuid",
  "trigger": "manual_help | high_risk_message | suspicious_call",
  "channel": "whatsapp | sms | call",
  "status": "sent | failed | cancelled",
  "createdAt": "timestamp"
}
```

## 3.6. Entidad Settings

```json
{
  "userId": "uuid",
  "alertsEnabled": true,
  "autoSuggestContact": true,
  "allowPostCallCheck": true,
  "shareAnalysisEnabled": true,
  "languageStyle": "simple",
  "updatedAt": "timestamp"
}
```

---

# 4. Arquitectura técnica detallada

## 4.1. Arquitectura propuesta para v1

### Cliente móvil
- Android nativo
- Kotlin
- Jetpack Compose
- MVVM

### Capa local
- Room para persistencia mínima local
- DataStore para preferencias
- WorkManager para tareas simples en background

### Servicios Android
- share target / share intent para recibir texto
- listener de telefonía según permisos y restricciones del sistema
- notificaciones locales

### Backend inicial
Opción recomendada:
- Firebase Authentication
- Firestore
- Cloud Functions opcionales
- Firebase Cloud Messaging

### Módulo de análisis
Primera versión:
- reglas + scoring
- local o híbrido

Segunda versión:
- reglas + API de clasificación más rica

---

## 4.2. Arquitectura lógica

```text
Usuario
  ↓
App Android
  ↓
Módulo de análisis local / API de análisis
  ↓
Resultado de riesgo
  ↓
Acción sugerida / alerta / contacto de confianza
```

---

## 4.3. Módulos del sistema

### Módulo 1 - Onboarding y configuración
Responsable de:
- registrar usuario
- configurar preferencias
- guardar contactos de confianza

### Módulo 2 - Análisis de mensajes
Responsable de:
- recibir texto
- normalizarlo
- correr scoring
- devolver explicación

### Módulo 3 - Alertas de llamadas
Responsable de:
- detectar llamada entrante
- decidir si mostrar warning
- disparar flujo post-llamada

### Módulo 4 - Ayuda y contacto
Responsable de:
- enviar alertas
- permitir contacto rápido
- registrar eventos

### Módulo 5 - Historial
Responsable de:
- guardar análisis y alertas
- mostrar trazabilidad simple al usuario

---

# 5. Roadmap de desarrollo

## Etapa 1 - Producto base
### Objetivo
Cerrar definición funcional antes de programar.

### Entregables
- flujo de usuario
- wireframes
- modelo de datos
- arquitectura

### Estado
- en progreso con este documento

---

## Etapa 2 - Prototipo navegable
### Objetivo
Construir la app sin backend complejo, solo para probar experiencia.

### Alcance
- onboarding
- home
- análisis mock de mensajes
- ayuda rápida
- configuración básica

### Resultado
Primer prototipo usable.

---

## Etapa 3 - MVP funcional
### Objetivo
Agregar lógica real básica.

### Alcance
- scoring por reglas
- persistencia local
- contacto de confianza
- alertas básicas de llamada
- historial

### Resultado
MVP demostrable y testeable.

---

## Etapa 4 - Validación con usuarios
### Objetivo
Probar con usuarios reales.

### Alcance
- sesiones con usuarios 30+
- feedback de claridad
- comprensión de alertas
- uso de botón de ayuda

### Resultado
Ajustes de producto y UX.

---

## Etapa 5 - Backend y crecimiento
### Objetivo
Preparar el producto para mayor robustez.

### Alcance
- backend en Firebase
- analytics
- mejor scoring
- más configuración
- base de números reportados

---

# 6. Priorización técnica inmediata

## Lo primero que conviene construir
1. estructura Android base
2. onboarding
3. home
4. analizador de mensajes con reglas simples
5. configuración de contacto de confianza
6. ayuda rápida

## Lo segundo
7. persistencia local
8. historial
9. alertas básicas sobre llamadas

## Lo tercero
10. backend
11. mejoras de scoring
12. automatizaciones más sofisticadas

---

# 7. Sprint 1 sugerido

## Objetivo del Sprint 1
Llegar a una app Android navegable con flujo principal completo.

## Tareas
- crear proyecto Android en Kotlin + Compose
- definir theme visual accesible
- implementar onboarding
- implementar home
- implementar pantalla de análisis
- implementar resultado de riesgo mock
- implementar contacto de confianza local
- implementar pantalla de ayuda rápida

## Resultado esperado
Demo navegable del MVP, aunque todavía sin toda la inteligencia real.

---

# 8. Sprint 2 sugerido

## Objetivo del Sprint 2
Hacer funcional el corazón del producto.

## Tareas
- motor de reglas
- scoring
- señales detectadas
- historial local
- settings persistentes
- flujo de alertas a contacto

---

# 9. Riesgos de implementación

## Riesgo 1
Prometer integración profunda con WhatsApp antes de validar el canal real.

## Riesgo 2
Depender del análisis de audio de llamadas demasiado temprano.

## Riesgo 3
Sobrecargar la UX con demasiadas opciones para usuarios vulnerables.

## Riesgo 4
Diseñar para un usuario técnico y no para el usuario real.

## Riesgo 5
Intentar resolver todo en v1 en vez de demostrar valor rápido.

---

# 10. Siguiente paso exacto

Con este documento ya conviene pasar a una de estas dos cosas:

1. **crear la estructura real del proyecto Android**
2. **armar primero wireframes visuales más detallados pantalla por pantalla**

La recomendación es arrancar por la estructura real del proyecto y, en paralelo, ajustar wireframes sobre código.