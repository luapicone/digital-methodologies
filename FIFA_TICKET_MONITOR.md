# FIFA Ticket Monitor

Monitor de navegador para FIFA Tickets pensado para correr sobre **tu propia sesión ya iniciada**.

## Qué hace

- corre dentro de la página en tu navegador
- deja configurar un **precio objetivo**
- permite modo **precio máximo** o **precio exacto**
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
- si la página cambia el HTML, probablemente haya que retocar selectores

## Webhook Discord

El webhook se pega completo en el campo correspondiente del panel.

Formato esperado:

`https://discord.com/api/webhooks/...`

## Notas técnicas

El script intenta encontrar:
- botones con texto tipo `comprar`, `buy`, `seleccionar`, `agregar`
- contenedores cercanos con texto de precio como `USD 120`, `US$ 120` o `$120`

Es una heurística razonable para empezar, pero seguramente haya que ajustarla contra el DOM real de FIFA Tickets si querés que quede más fino.
