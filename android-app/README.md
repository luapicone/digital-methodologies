# Android App

Proyecto Android nativo del MVP antiestafas.

## Stack
- Kotlin
- Jetpack Compose
- Navigation Compose
- Material 3
- ViewModel local

## Cómo abrirlo
1. Abrir Android Studio.
2. Seleccionar la carpeta `android-app`.
3. Esperar sincronización de Gradle.
4. Ejecutar el módulo `app` en un emulador Android.

## Qué ya hace esta versión
- onboarding en pasos
- selección de nivel de intervención
- home con acciones principales
- analizador de mensajes con scoring local por reglas
- explicación de señales detectadas
- ayuda rápida hacia contacto de confianza
- alta simple de contactos locales
- configuración con toggles reales en estado
- chequeo post-llamada
- historial con tipos de evento y filtros
- persistencia local básica con SharedPreferences
- acciones reales para abrir WhatsApp o SMS hacia contacto de confianza

## Qué todavía falta para una versión productiva
- persistencia real local (Room / DataStore)
- integración con llamadas del sistema Android
- reputación de números
- envío real de alertas por WhatsApp o SMS
- share flows más robustos
- backend opcional

## Nota técnica
La app está planteada como MVP demostrable y local. No promete todavía integración profunda con WhatsApp ni escucha de audio de llamadas en tiempo real.

## Estado de terminación
Dentro de este repo, la app quedó avanzada hasta un MVP demo funcional local. Lo siguiente ya sería validación en Android Studio, corrección de compatibilidad sobre dispositivo/emulador y luego integraciones más profundas con Android.
