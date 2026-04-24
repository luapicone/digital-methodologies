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

## Qué todavía falta para una versión productiva
- persistencia real local (Room / DataStore)
- integración con llamadas del sistema Android
- reputación de números
- envío real de alertas por WhatsApp o SMS
- share flows más robustos
- backend opcional

## Nota técnica
La app está planteada como MVP demostrable y local. No promete todavía integración profunda con WhatsApp ni escucha de audio de llamadas en tiempo real.
