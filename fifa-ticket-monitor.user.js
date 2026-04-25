// ==UserScript==
// @name         FIFA Ticket Monitor (sesión local)
// @namespace    https://luapicone.com/
// @version      0.1.0
// @description  Monitorea disponibilidad/precios en la página de FIFA Tickets usando tu sesión actual, refresca cada 10 segundos y avisa por Discord cuando encuentra una coincidencia.
// @author       Max
// @match        https://fwc26-shop-usd.tickets.fifa.com/*
// @grant        GM_getValue
// @grant        GM_setValue
// @grant        GM_notification
// @grant        GM_xmlhttpRequest
// @connect      discord.com
// @connect      discordapp.com
// @run-at       document-idle
// ==/UserScript==

(function () {
  'use strict';

  const DEFAULTS = {
    enabled: false,
    refreshSeconds: 10,
    priceMode: 'max',
    targetPrice: 0,
    webhookUrl: '',
    lastAlertKey: '',
    selectedLabelHint: '',
  };

  const selectors = {
    buyButtons: [
      'button[data-testid*="buy"]',
      'button[data-testid*="add"]',
      'button[class*="buy"]',
      'button[class*="add"]',
      'button',
      'a[role="button"]',
    ],
    priceContainers: [
      '[data-testid*="price"]',
      '[class*="price"]',
      '[class*="ticket"]',
      '[class*="offer"]',
      '[class*="seat"]',
      'article',
      'li',
      'div',
    ],
  };

  const state = {
    config: loadConfig(),
    timerId: null,
    lastScan: null,
    lastCandidates: [],
  };

  init();

  function init() {
    injectStyles();
    createPanel();
    refreshPanel();
    if (state.config.enabled) {
      scheduleNextReload();
    }
    window.addEventListener('beforeunload', () => {
      if (state.timerId) clearTimeout(state.timerId);
    });
  }

  function loadConfig() {
    return {
      enabled: GM_getValue('enabled', DEFAULTS.enabled),
      refreshSeconds: Number(GM_getValue('refreshSeconds', DEFAULTS.refreshSeconds)),
      priceMode: GM_getValue('priceMode', DEFAULTS.priceMode),
      targetPrice: Number(GM_getValue('targetPrice', DEFAULTS.targetPrice)),
      webhookUrl: GM_getValue('webhookUrl', DEFAULTS.webhookUrl),
      lastAlertKey: GM_getValue('lastAlertKey', DEFAULTS.lastAlertKey),
      selectedLabelHint: GM_getValue('selectedLabelHint', DEFAULTS.selectedLabelHint),
    };
  }

  function saveConfig() {
    Object.entries(state.config).forEach(([key, value]) => GM_setValue(key, value));
  }

  function injectStyles() {
    const style = document.createElement('style');
    style.textContent = `
      #max-fifa-monitor {
        position: fixed;
        right: 16px;
        bottom: 16px;
        width: 340px;
        max-width: calc(100vw - 24px);
        background: #0f172a;
        color: #f8fafc;
        border: 1px solid rgba(255,255,255,.12);
        border-radius: 16px;
        z-index: 2147483647;
        box-shadow: 0 16px 44px rgba(0,0,0,.35);
        font-family: Inter, system-ui, sans-serif;
        overflow: hidden;
      }
      #max-fifa-monitor .hdr {
        background: linear-gradient(135deg, #ff8f3d, #ffb36c);
        color: #1f1307;
        padding: 12px 14px;
        font-weight: 800;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      #max-fifa-monitor .body {
        padding: 14px;
        display: grid;
        gap: 10px;
      }
      #max-fifa-monitor label {
        display: grid;
        gap: 6px;
        font-size: 12px;
        color: #cbd5e1;
      }
      #max-fifa-monitor input,
      #max-fifa-monitor select,
      #max-fifa-monitor textarea,
      #max-fifa-monitor button {
        width: 100%;
        border-radius: 10px;
        border: 1px solid rgba(255,255,255,.12);
        background: #111827;
        color: #f8fafc;
        padding: 10px 11px;
        font-size: 13px;
        box-sizing: border-box;
      }
      #max-fifa-monitor textarea {
        min-height: 74px;
        resize: vertical;
      }
      #max-fifa-monitor .row {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 10px;
      }
      #max-fifa-monitor .actions {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 10px;
      }
      #max-fifa-monitor button.primary {
        background: #ff8f3d;
        color: #1f1307;
        font-weight: 800;
        cursor: pointer;
      }
      #max-fifa-monitor button.secondary {
        cursor: pointer;
      }
      #max-fifa-monitor .status {
        padding: 10px 12px;
        border-radius: 10px;
        background: rgba(255,255,255,.05);
        font-size: 12px;
        line-height: 1.5;
        color: #dbeafe;
        white-space: pre-wrap;
      }
      #max-fifa-monitor .cand {
        border-radius: 10px;
        background: rgba(255,255,255,.04);
        padding: 10px 12px;
        font-size: 12px;
        line-height: 1.45;
        color: #e5e7eb;
      }
      #max-fifa-monitor .mini {
        font-size: 11px;
        color: #94a3b8;
      }
      #max-fifa-monitor .toggle {
        width: auto;
      }
    `;
    document.head.appendChild(style);
  }

  function createPanel() {
    const root = document.createElement('div');
    root.id = 'max-fifa-monitor';
    root.innerHTML = `
      <div class="hdr">
        <span>FIFA Ticket Monitor</span>
        <span id="max-monitor-running">${state.config.enabled ? 'ON' : 'OFF'}</span>
      </div>
      <div class="body">
        <label>
          Precio objetivo
          <input id="max-target-price" type="number" min="0" step="1" placeholder="Ej: 120" />
        </label>
        <div class="row">
          <label>
            Modo
            <select id="max-price-mode">
              <option value="max">Precio máximo</option>
              <option value="exact">Precio exacto</option>
            </select>
          </label>
          <label>
            Refresh (seg)
            <input id="max-refresh-seconds" type="number" min="5" step="1" />
          </label>
        </div>
        <label>
          Texto opcional para filtrar zona/categoría
          <input id="max-label-hint" type="text" placeholder="Ej: Category 3 / Behind Goal" />
        </label>
        <label>
          Webhook Discord
          <textarea id="max-webhook-url" placeholder="https://discord.com/api/webhooks/... "></textarea>
        </label>
        <div class="actions">
          <button id="max-save-config" class="secondary">Guardar</button>
          <button id="max-toggle-monitor" class="primary">${state.config.enabled ? 'Detener' : 'Iniciar'}</button>
        </div>
        <button id="max-scan-now" class="secondary">Escanear ahora</button>
        <div id="max-status" class="status">Esperando configuración…</div>
        <div id="max-candidates" class="cand">Todavía no hay coincidencias detectadas.</div>
        <div class="mini">Funciona con tu sesión ya iniciada. Refresca esta misma pestaña y avisa por Discord cuando encuentra una coincidencia por precio.</div>
      </div>
    `;
    document.body.appendChild(root);

    document.getElementById('max-save-config').addEventListener('click', saveFromPanel);
    document.getElementById('max-toggle-monitor').addEventListener('click', toggleMonitor);
    document.getElementById('max-scan-now').addEventListener('click', manualScan);

    document.getElementById('max-target-price').value = state.config.targetPrice || '';
    document.getElementById('max-price-mode').value = state.config.priceMode;
    document.getElementById('max-refresh-seconds').value = state.config.refreshSeconds;
    document.getElementById('max-webhook-url').value = state.config.webhookUrl;
    document.getElementById('max-label-hint').value = state.config.selectedLabelHint;
  }

  function saveFromPanel() {
    state.config.targetPrice = Number(document.getElementById('max-target-price').value || 0);
    state.config.priceMode = document.getElementById('max-price-mode').value;
    state.config.refreshSeconds = Math.max(5, Number(document.getElementById('max-refresh-seconds').value || 10));
    state.config.webhookUrl = document.getElementById('max-webhook-url').value.trim();
    state.config.selectedLabelHint = document.getElementById('max-label-hint').value.trim();
    saveConfig();
    setStatus('Configuración guardada.');
    refreshPanel();
  }

  function toggleMonitor() {
    saveFromPanel();
    state.config.enabled = !state.config.enabled;
    saveConfig();

    if (state.timerId) {
      clearTimeout(state.timerId);
      state.timerId = null;
    }

    if (state.config.enabled) {
      setStatus('Monitor activo. Voy a escanear y recargar esta pestaña automáticamente.');
      manualScan();
      scheduleNextReload();
    } else {
      setStatus('Monitor detenido.');
    }

    refreshPanel();
  }

  function refreshPanel() {
    const running = document.getElementById('max-monitor-running');
    const toggle = document.getElementById('max-toggle-monitor');
    if (running) running.textContent = state.config.enabled ? 'ON' : 'OFF';
    if (toggle) toggle.textContent = state.config.enabled ? 'Detener' : 'Iniciar';
  }

  function scheduleNextReload() {
    if (!state.config.enabled) return;
    if (state.timerId) clearTimeout(state.timerId);
    state.timerId = setTimeout(() => {
      window.location.reload();
    }, state.config.refreshSeconds * 1000);
  }

  function manualScan() {
    const candidates = scanPageForCandidates();
    state.lastScan = new Date();
    state.lastCandidates = candidates;

    if (!candidates.length) {
      renderCandidates([]);
      setStatus(`Último escaneo: ${formatTime(state.lastScan)}\nNo encontré coincidencias todavía.`);
      return;
    }

    renderCandidates(candidates);

    const best = candidates[0];
    const alertKey = `${location.pathname}|${best.price}|${best.label}`;
    setStatus(`Último escaneo: ${formatTime(state.lastScan)}\nEncontré ${candidates.length} coincidencia(s). Mejor coincidencia: ${best.label} · ${best.priceText}`);

    if (state.config.lastAlertKey !== alertKey) {
      state.config.lastAlertKey = alertKey;
      saveConfig();
      notifyAll(best, candidates);
    }
  }

  function scanPageForCandidates() {
    const allButtons = uniqueElements(selectors.buyButtons.flatMap((selector) => Array.from(document.querySelectorAll(selector))));
    const candidates = [];

    for (const button of allButtons) {
      const text = cleanText(button.textContent);
      if (!looksLikeBuyButton(text)) continue;

      const container = findNearestContainer(button);
      const rawText = cleanText(container?.innerText || button.innerText || '');
      const price = extractPrice(rawText);
      if (price == null) continue;

      const label = deriveLabel(container, button, rawText);
      if (!matchesLabelHint(label, rawText)) continue;
      if (!matchesTargetPrice(price)) continue;

      candidates.push({
        price,
        priceText: `$${price}`,
        label,
        contextText: rawText.slice(0, 320),
      });
    }

    return candidates.sort((a, b) => a.price - b.price);
  }

  function uniqueElements(list) {
    return Array.from(new Set(list));
  }

  function looksLikeBuyButton(text) {
    const normalized = text.toLowerCase();
    return ['comprar', 'buy', 'seleccionar', 'add to cart', 'agregar'].some((token) => normalized.includes(token));
  }

  function findNearestContainer(button) {
    let node = button;
    for (let i = 0; i < 6 && node; i += 1) {
      const text = cleanText(node.innerText || '');
      if (extractPrice(text) != null) return node;
      node = node.parentElement;
    }
    return button.parentElement || button;
  }

  function deriveLabel(container, button, rawText) {
    const heading = container?.querySelector('h1,h2,h3,h4,strong,b,[class*="title"],[class*="label"]');
    const label = cleanText(heading?.textContent || button.textContent || rawText);
    return label.slice(0, 120) || 'Entrada detectada';
  }

  function matchesLabelHint(label, rawText) {
    const hint = state.config.selectedLabelHint.trim().toLowerCase();
    if (!hint) return true;
    const target = `${label} ${rawText}`.toLowerCase();
    return target.includes(hint);
  }

  function matchesTargetPrice(price) {
    const target = Number(state.config.targetPrice || 0);
    if (!target) return true;
    if (state.config.priceMode === 'exact') return price === target;
    return price <= target;
  }

  function extractPrice(text) {
    if (!text) return null;
    const normalized = text.replace(/\u00a0/g, ' ');
    const regexes = [
      /USD\s*([0-9]{1,4}(?:[\.,][0-9]{2})?)/i,
      /US\$\s*([0-9]{1,4}(?:[\.,][0-9]{2})?)/i,
      /\$\s*([0-9]{1,4}(?:[\.,][0-9]{2})?)/,
    ];

    for (const regex of regexes) {
      const match = normalized.match(regex);
      if (match) {
        const value = parseFloat(match[1].replace(',', '.'));
        if (!Number.isNaN(value)) return Math.round(value);
      }
    }
    return null;
  }

  function notifyAll(best, all) {
    const title = `Entradas detectadas: ${best.priceText}`;
    const body = `${best.label}\n${location.href}`;

    try {
      GM_notification({
        title,
        text: body,
        timeout: 15000,
      });
    } catch (_) {}

    try {
      const audio = new Audio('data:audio/wav;base64,UklGRlQAAABXQVZFZm10IBAAAAABAAEAQB8AAEAfAAABAAgAZGF0YTAAAAA=' );
      audio.play().catch(() => {});
    } catch (_) {}

    if (state.config.webhookUrl) {
      sendDiscordWebhook(best, all);
    }
  }

  function sendDiscordWebhook(best, all) {
    const content = [
      '🎟️ **Coincidencia detectada en FIFA Tickets**',
      `**Precio:** ${best.priceText}`,
      `**Zona/label:** ${best.label}`,
      `**URL:** ${location.href}`,
      '',
      `Coincidencias encontradas: ${all.length}`,
    ].join('\n');

    GM_xmlhttpRequest({
      method: 'POST',
      url: state.config.webhookUrl,
      headers: { 'Content-Type': 'application/json' },
      data: JSON.stringify({ content }),
      onload: () => {},
      onerror: () => {
        setStatus(`Último escaneo: ${formatTime(state.lastScan)}\nEncontré coincidencias pero falló el webhook.`);
      },
    });
  }

  function renderCandidates(candidates) {
    const node = document.getElementById('max-candidates');
    if (!node) return;
    if (!candidates.length) {
      node.textContent = 'Todavía no hay coincidencias detectadas.';
      return;
    }
    node.innerHTML = candidates.slice(0, 3).map((item, idx) => {
      return `<div><strong>#${idx + 1} · ${escapeHtml(item.priceText)}</strong><br>${escapeHtml(item.label)}<br><span class="mini">${escapeHtml(item.contextText.slice(0, 180))}</span></div>`;
    }).join('<hr style="border:0;border-top:1px solid rgba(255,255,255,.08);margin:8px 0">');
  }

  function setStatus(text) {
    const node = document.getElementById('max-status');
    if (node) node.textContent = text;
  }

  function cleanText(value) {
    return (value || '').replace(/\s+/g, ' ').trim();
  }

  function formatTime(date) {
    return date ? date.toLocaleTimeString('es-AR') : '-';
  }

  function escapeHtml(str) {
    return String(str)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }
})();
