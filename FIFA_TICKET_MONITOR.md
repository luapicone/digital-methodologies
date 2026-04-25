# FIFA Ticket Monitor

Monitor de navegador para FIFA Tickets pensado para correr sobre **tu propia sesión ya iniciada**.

## Qué hace

- corre dentro de la página en tu navegador
- deja configurar un **precio objetivo**
- permite modo **lista exacta**, **precio máximo** o **precio exacto**
- por defecto vigila específicamente:
  - **Categoría 4 = 60 USD**
  - **Categoría 3 = 350 USD**
- refresca la pestaña cada `N` segundos, por defecto `10`
- intenta detectar bloques con precio y botón de compra
- avisa con:
  - notificación local del navegador/userscript
  - webhook de Discord
- permite un filtro opcional por texto para zona/categoría

## Qué no hace

- no salta captchas ni colas virtuales
- no evade protecciones anti-bot
- no hace checkout automático
- no garantiza que los selectores actuales de FIFA no cambien

## Archivo

- `fifa-ticket-monitor.user.js`

## Instalación

### Opción recomendada
Usar **Tampermonkey** en Chrome/Edge.

1. Instalar Tampermonkey.
2. Crear un script nuevo.
3. Pegar el contenido de `fifa-ticket-monitor.user.js`.
4. Guardar.
5. Abrir la página del partido con tu sesión ya iniciada.

## Uso

1. Abrí la URL del partido en FIFA Tickets.
2. En la esquina inferior derecha aparece el panel `FIFA Ticket Monitor`.
3. Configurá:
   - `Precio objetivo`
   - `Precios a vigilar` (por defecto `60,350`)
   - `Reglas categoría:precio` (por defecto `Categoría 4:60,Categoría 3:350`)
   - `Modo`
   - `Refresh`
   - `Webhook Discord`
   - `Texto opcional para filtrar zona/categoría`
4. Tocá `Guardar`.
5. Tocá `Iniciar`.

## Recomendaciones

- primero probalo sin webhook
- después agregá el webhook
- si la web muestra varias zonas, usá el filtro de texto para reducir ruido
- esta v2 está ajustada a la pantalla de filas como `Categoría 1/2/3/4` y estado `Actualmente no disponible`
- la coincidencia más fina por defecto queda atada a `Categoría 4:60` y `Categoría 3:350`
- si la página cambia el HTML, probablemente haya que retocar selectores

## Webhook Discord

El webhook se pega completo en el campo correspondiente del panel.

Formato esperado:

`https://discord.com/api/webhooks/...`

## Notas técnicas

El script intenta encontrar filas con texto tipo:
- `Categoría 1`, `Categoría 2`, `Categoría 3`, `Categoría 4`
- `Zona ...`
- precio tipo `From 60.00 USD` o `From 350.00 USD`
- y descarta filas que sigan mostrando `Actualmente no disponible`
- en modo `Lista exacta`, puede además exigir combinación exacta `categoría:precio`

Esta v2 quedó ajustada específicamente a la captura real compartida para FIFA Tickets.
