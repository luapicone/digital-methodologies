# MVP Android - App antiestafas para usuarios vulnerables digitalmente

## 1. Definición del producto

### Nombre de trabajo
**Protección Antiestafas** (nombre provisorio)

### Problema que resuelve
Personas de 30 años en adelante con baja familiaridad tecnológica, especialmente adultos mayores o usuarios vulnerables, reciben mensajes, llamadas o pedidos urgentes que parecen reales y terminan compartiendo datos, transfiriendo dinero o actuando bajo presión sin verificar.

La app busca **bajar la exposición a estafas** mediante:
- alertas simples,
- análisis asistido de mensajes sospechosos,
- detección básica de llamadas riesgosas,
- y conexión rápida con un familiar o contacto de confianza.

### Público objetivo
- Personas de **30+** con bajo conocimiento tecnológico o bajo conocimiento sobre estafas.
- Adultos mayores como subgrupo especialmente prioritario.
- Usuarios que necesitan una interfaz clara, directa y sin complejidad.

### Modelo de uso definido
Se trabajará con **doble actor**:

1. **Usuario principal**
   - persona que recibe mensajes, llamadas o pedidos sospechosos.
   - usa la app para recibir alertas, analizar contenido y pedir ayuda.

2. **Contacto de confianza**
   - familiar, hijo/a, nieto/a o persona cercana.
   - recibe alertas o pedidos de ayuda según configuración.

---

## 2. Decisiones ya definidas

### Plataforma inicial
- **Solo Android**

### Enfoque del MVP
- El alcance inicial lo definimos nosotros, priorizando viabilidad técnica real.
- No se asume acceso total a WhatsApp ni análisis libre de audio de llamadas en tiempo real.
- Se prioriza construir una **versión realista, útil y demostrable**.

### Nivel de intervención
Debe ser **personalizable por usuario**.

Cada persona podrá configurar si la app:
- solo alerta,
- alerta y sugiere acción,
- alerta y avisa a contacto de confianza,
- muestra advertencias fuertes,
- permite activar ayuda manual.

---

## 3. Qué sí puede hacer el MVP v1

## 3.1. Llamadas sospechosas
La app podrá:
- detectar si entra una llamada de número desconocido;
- detectar si el número está en una base local o externa de spam/reportes, si luego integramos reputación;
- mostrar una **alerta visual simple** antes o durante el llamado, según permisos de Android;
- advertir con mensajes tipo:
  - “Número no agendado”
  - “Posible llamada riesgosa”
  - “No compartas códigos, datos bancarios ni hagas transferencias durante la llamada”

### Lo que no tomamos como base del MVP
- escuchar audio de la llamada en tiempo real como función principal;
- transcribir llamadas completas automáticamente;
- decidir con precisión total si la llamada es estafa solo por audio.

Eso puede explorarse después, pero no es base segura del MVP.

## 3.2. Mensajes sospechosos
La app podrá:
- recibir texto copiado o compartido desde otra app;
- analizar manualmente mensajes sospechosos;
- detectar patrones de riesgo como:
  - urgencia,
  - pedido de transferencia,
  - supuesto familiar,
  - supuesto banco,
  - pedido de código,
  - link sospechoso,
  - presión emocional;
- devolver una clasificación simple:
  - riesgo bajo,
  - riesgo medio,
  - riesgo alto;
- explicar por qué se marcó como sospechoso.

### Forma realista de integración con WhatsApp en v1
No se parte de leer chats privados automáticamente.

La mecánica del MVP será:
- copiar y pegar mensaje en la app, o
- compartir contenido hacia la app desde Android, si el flujo lo permite.

Esto evita arrancar con una promesa técnica difícil o inestable.

## 3.3. Contacto de confianza
La app podrá:
- guardar 1 o 2 contactos de confianza;
- permitir al usuario pedir ayuda rápido;
- enviar alerta manual a contacto de confianza;
- en configuraciones más activas, sugerir o ejecutar alerta automática según el nivel elegido.

## 3.4. Botón de ayuda
La app tendrá un botón claro tipo:
- **“No estoy seguro”**
- **“Pedir ayuda”**
- **“Analizar mensaje”**

Ese botón debe estar visible siempre y ser entendible sin aprendizaje previo.

## 3.5. Checklist antifraude simple
La app podrá mostrar un chequeo inmediato antes de actuar:
- ¿Te apuran?
- ¿Te piden transferir dinero?
- ¿Te piden código o clave?
- ¿Dicen ser un familiar o banco?
- ¿No pudiste verificar por otro medio?

Si se acumulan señales, la app eleva el riesgo y recomienda no avanzar.

---

## 4. Qué dejamos para v2

- integración más profunda con accesibilidad en Android;
- reglas más avanzadas por tipo de estafa;
- historial de incidentes;
- personalización más rica por perfil del usuario;
- panel del familiar de confianza;
- reputación de números con backend;
- mejores modelos de scoring;
- análisis semiautomático más contextual.

---

## 5. Qué dejamos para v3 o exploración técnica

- análisis de voz o audio de llamadas en tiempo real;
- detección de coerción conversacional por audio en vivo;
- automatización profunda sobre mensajería cerrada;
- integraciones sensibles que dependan de permisos restrictivos del sistema operativo.

---

## 6. Propuesta funcional del MVP

## 6.1. Pantallas mínimas

### A. Onboarding
- bienvenida
- explicación simple de qué hace la app
- pedido de permisos mínimos
- configuración de contacto de confianza
- elección de nivel de intervención

### B. Home
- botón grande: **Analizar mensaje**
- botón grande: **Pedir ayuda**
- botón grande: **Verificar llamada sospechosa**
- estado actual de protección
- acceso a configuración

### C. Analizador de mensajes
- campo para pegar texto o contenido compartido
- resultado visual de riesgo
- explicación breve de señales detectadas
- acción sugerida
- botón para avisar a contacto

### D. Alerta de llamada sospechosa
- pantalla/interfaz liviana con advertencia
- número entrante
- motivo del warning
- recordatorio: “no compartas datos ni transfieras dinero”

### E. Contactos de confianza
- alta/edición de 1 o 2 contactos
- tipo de notificación
- qué acciones disparan alerta

### F. Configuración de intervención
- modo 1: solo alertar
- modo 2: alertar y sugerir ayuda
- modo 3: alertar y avisar a contacto de confianza
- toggles simples para personalización

### G. Historial básico
- mensajes analizados
- alertas enviadas
- llamadas marcadas como sospechosas

---

## 7. Niveles de intervención personalizables

## Nivel 1 - Solo alerta
La app:
- muestra advertencia visual
- clasifica riesgo
- no contacta a nadie automáticamente

## Nivel 2 - Alerta + sugerencia
La app:
- alerta
- recomienda no actuar
- sugiere contactar a una persona de confianza

## Nivel 3 - Alerta + contacto de confianza
La app:
- alerta
- muestra recomendación fuerte
- ofrece enviar aviso inmediato a contacto de confianza

## Nivel 4 - Protección reforzada
La app:
- alerta con mayor intensidad
- prioriza mensajes preventivos
- propone contacto inmediato con apoyo externo
- puede requerir confirmación extra antes de acciones dentro de la app

---

## 8. Arquitectura recomendada

## 8.1. Stack sugerido para MVP

### Frontend móvil
- **Kotlin + Jetpack Compose**

Motivo:
- Android nativo
- mejor control sobre permisos, llamadas y share intents
- buena base para un MVP serio

### Backend
Dos caminos posibles:

#### Opción A - MVP híbrido simple
- app Android + backend liviano
- backend con **Node.js + Express** o **NestJS**
- base de datos PostgreSQL

#### Opción B - MVP todavía más rápido
- app Android + Firebase
- Firestore
- Firebase Auth
- Cloud Functions si hace falta lógica simple

### Recomendación
Para el MVP inicial:
- **Android nativo en Kotlin**
- **Firebase** para acelerar

Luego, si crece:
- migrar o complementar con backend propio.

## 8.2. Componentes técnicos del MVP

### App Android
Responsable de:
- UI
- permisos
- share intents
- lectura básica de estado de llamadas si Android lo permite
- configuración del usuario
- análisis local o llamado a API

### Módulo de análisis de riesgo
Responsable de:
- evaluar texto sospechoso
- detectar patrones de urgencia/fraude
- devolver score + explicación

Puede arrancar como:
- motor simple por reglas, o
- reglas + clasificación liviana

### Base de datos
Guardar:
- usuario
- configuración de intervención
- contactos de confianza
- eventos de análisis
- historial de alertas

### Notificaciones
- push local
- aviso al familiar si corresponde

---

## 9. Motor de análisis inicial

## 9.1. Primer enfoque recomendado
Arrancar con un sistema por reglas y scoring.

Ejemplos de señales:
- menciona transferencia
- menciona urgencia
- menciona familiar
- menciona banco
- pide clave o código
- tiene link sospechoso
- usa presión emocional

Cada señal suma riesgo.

### Salida del motor
- score 0-100
- nivel de riesgo
- explicación breve
- recomendación concreta

## 9.2. Por qué conviene esto primero
Porque:
- es más transparente;
- más fácil de probar;
- más defendible académicamente;
- y mucho más rápido para MVP que entrenar algo complejo desde el día uno.

---

## 10. Restricciones reales que hay que respetar

## 10.1. WhatsApp
No asumir que podemos:
- leer todos los chats automáticamente;
- interceptar contenido privado libremente;
- operar como si tuviéramos integración nativa profunda.

## 10.2. Llamadas
No asumir que podemos:
- escuchar llamadas sin restricciones;
- procesar audio completo en tiempo real en todos los dispositivos;
- hacer esto igual en Android e iPhone.

## 10.3. Privacidad y legalidad
Hay que diseñarlo cuidando:
- consentimiento del usuario;
- permisos explícitos;
- mínima invasión;
- transparencia sobre qué se analiza y qué no.

---

## 11. Definición del MVP final

### MVP v1 definido
Aplicación Android para prevención de estafas orientada a usuarios vulnerables digitalmente, con foco en:
- alertas simples sobre llamadas sospechosas;
- análisis manual de mensajes sospechosos;
- conexión rápida con un contacto de confianza;
- nivel de intervención configurable;
- experiencia simple, directa y no técnica.

### Qué resuelve bien el MVP
- reduce impulsividad ante mensajes o llamadas sospechosas;
- genera una segunda instancia de verificación;
- incorpora una red de apoyo;
- hace visible el riesgo con lenguaje simple.

### Qué no promete todavía
- detener todas las estafas;
- leer WhatsApp automáticamente en profundidad;
- escuchar y comprender todas las llamadas en tiempo real;
- reemplazar criterio humano o soporte familiar.

---

## 12. Próximo paso concreto

El siguiente paso natural es bajar este documento a:

1. **flujo de usuario completo**
2. **wireframe de pantallas**
3. **modelo de datos**
4. **arquitectura técnica del MVP**
5. **roadmap de desarrollo en etapas**

Con eso ya queda listo para empezar a construir el proyecto real.
