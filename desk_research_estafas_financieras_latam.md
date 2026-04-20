# Desk Research sobre estafas financieras en LATAM
## Discovery y validación de problema para startup real

**Repositorio:** `digital-methodologies`  
**Idioma:** español  
**Tono:** académico-profesional  
**Alcance geográfico principal:** Argentina  
**Expansión considerada:** LATAM, con foco en México, Brasil y Colombia  
**Dominio acotado:** prevención de estafas financieras asociadas a déficit de educación financiera, ciberseguridad básica e ingeniería social.

---

## Introducción metodológica

El presente documento desarrolla un desk research orientado a identificar problemas reales, económicamente relevantes y tecnológicamente defendibles dentro del universo de las estafas financieras en América Latina. El objetivo no es producir una enumeración amplia de fraudes, sino seleccionar problemas con suficiente evidencia externa, presencia regional, comprador identificable y potencial de convertirse en una startup escalable.

El análisis se estructura en dos fases, siguiendo la consigna recibida:

1. **Fase 1. Research de problemas reales con willingness to pay**.
2. **Fase 2. Validación crítica con scoring comparativo y priorización**.

A diferencia de un ejercicio ideacional, este trabajo parte de la premisa de que la empatía social no basta para justificar un emprendimiento. En consecuencia, cada problema fue evaluado a partir de cuatro exigencias centrales: **evidencia oficial o institucional**, **comprador con presupuesto o gasto observable**, **ventaja tecnológica defendible** y **escalabilidad/replicabilidad**.

### Criterios de evidencia utilizados

Se priorizaron fuentes **Tier 1** y **Tier 2**, en línea con la consigna:

- **Tier 1:** FBI IC3 2024 Internet Crime Report, Europol, Interpol, Verizon DBIR, CONDUSEF y otras fuentes institucionales o reportes ampliamente reconocidos.
- **Tier 2:** publicaciones especializadas y medios con atribución de fuente institucional, únicamente como apoyo contextual.

### Limitaciones metodológicas

- No todos los organismos de Argentina y LATAM publican bases homogéneas y comparables para cada tipología de estafa.
- En algunos casos, la evidencia cuantitativa más sólida disponible es global o de Estados Unidos, pero fue utilizada solo cuando la tipología presenta **alta transferibilidad regional** y además existen señales regulatorias o institucionales en LATAM.
- Cuando una cifra no pudo verificarse con fuente oficial accesible y clara, **no se la incorporó como hecho validado**.

---

# FASE 1. Research de problemas reales con willingness to pay

## Output requerido en formato JSON

```json
[
  {
    "problem_id": "P001",
    "vertical": "FinTech",
    "sub_segment": "Prevención de estafas por ingeniería social en banca y billeteras",
    "scam_typology": "phishing",
    "primary_vulnerability": "combinada",
    "target_victim_profile": "Usuarios minoristas de banca digital y billeteras en Argentina y LATAM, especialmente personas con baja alfabetización digital y financiera",
    "geography_primary": "Argentina",
    "geography_expansion": ["México", "Brasil"],
    "pain_point_one_sentence": "Usuarios de servicios financieros digitales son inducidos por phishing, smishing y suplantación de identidad a entregar credenciales, OTPs o autorizar transferencias, generando pérdidas para clientes y costos de reintegro, soporte y reputación para bancos y fintechs.",
    "evidence": {
      "description": "El FBI IC3 reportó USD 16,6 mil millones en pérdidas por ciberdelito en 2024; la categoría de phishing/spoofing siguió siendo una de las más reportadas. CONDUSEF también alertó sobre el peso del fraude digital sobre usuarios vulnerables y adultos mayores en México.",
      "source_type": "tier_1",
      "source_name": "FBI IC3 2024 Internet Crime Report; CONDUSEF",
      "year": "2024-2025"
    },
    "who_pays_today": "Bancos, fintechs, procesadores de pago y emisores que absorben reintegros, costos operativos de fraude, atención al cliente y campañas preventivas.",
    "estimated_annual_cost_usd": "Multimillonario; IC3 reporta pérdidas agregadas por ciberdelito de USD 16,6 mil millones en 2024 y phishing/spoofing se mantiene como vector masivo.",
    "why_current_solution_fails": "Las soluciones actuales descansan en educación estática, advertencias genéricas y autenticación tradicional, pero el ataque ocurre en tiempo real y explota sesgos conductuales y urgencia fabricada.",
    "triple_impact_mapping": {
      "economic": "Reduce pérdidas por fraude, chargebacks, reintegros y costo operativo en equipos antifraude.",
      "social": "Protege a usuarios vulnerables y reduce exclusión financiera provocada por miedo o pérdida patrimonial.",
      "environmental": "No aplica de forma directa; el impacto ambiental es marginal e indirecto.",
      "dominant_dimension": "economic"
    },
    "technologies": ["AI Agents", "NLP", "scoring comportamental"],
    "tech_rationale": "La detección efectiva requiere analizar señales conversacionales, contexto transaccional y patrones de manipulación en tiempo real; un enfoque puramente manual o educativo no alcanza.",
    "tech_fit_niche": "ai_agents_antifraude",
    "buyer": "Bancos medianos, billeteras digitales, fintechs regionales",
    "end_user": "Usuario final de banca digital",
    "blocker": "Acceso a señales transaccionales e integración con flujos de autenticación y mensajería",
    "mvp_feasibility_5_person_team": "alta",
    "time_to_first_customer_months": 4,
    "scalability_rationale": "Un motor de scoring y alertas se distribuye como software y escala por integración, no por atención humana 1 a 1.",
    "replicability_rationale": "El problema se replica en banca, wallets y pagos instantáneos en múltiples países de LATAM.",
    "competitive_landscape_latam": "Players antifraude transaccionales y vendors de behavioral biometrics, pero con menos foco explícito en ingeniería social conversacional previa a la transferencia.",
    "complexity_justifies_5_person_year": true
  },
  {
    "problem_id": "P002",
    "vertical": "FoW",
    "sub_segment": "Protección de PyMEs contra BEC y fraude de transferencias",
    "scam_typology": "BEC_CEO_fraud",
    "primary_vulnerability": "ingenieria_social",
    "target_victim_profile": "PyMEs y empresas medianas de 10 a 200 empleados sin área madura de seguridad o fraude",
    "geography_primary": "Argentina",
    "geography_expansion": ["México", "Colombia"],
    "pain_point_one_sentence": "Las PyMEs pierden dinero por correos y mensajes que simulan instrucciones legítimas de pago, cambio de cuenta o aprobación urgente, sin tener controles suficientes para detectar BEC antes de transferir fondos.",
    "evidence": {
      "description": "El FBI IC3 reportó 21.442 denuncias de BEC en 2024 con pérdidas por aproximadamente USD 2,77 mil millones; entre 2022 y 2024 las pérdidas acumuladas rondaron USD 8,5 mil millones. Verizon DBIR 2025 también mantiene al pretexting y al factor humano como elementos centrales del riesgo empresarial.",
      "source_type": "tier_1",
      "source_name": "FBI IC3 2024 Internet Crime Report; Verizon DBIR 2025",
      "year": "2024-2025"
    },
    "who_pays_today": "Empresas afectadas, aseguradoras cyber, MSSPs, áreas de compliance y seguridad; además existen gastos en controles, capacitación y recuperación.",
    "estimated_annual_cost_usd": "USD 2,77 mil millones en pérdidas reportadas por BEC en 2024 según IC3.",
    "why_current_solution_fails": "Las PyMEs suelen depender de capacitación básica y controles manuales; el fraude avanza por canales ya confiables y con alto realismo contextual.",
    "triple_impact_mapping": {
      "economic": "Evita pérdidas directas por transferencias fraudulentas y costo de interrupción operativa.",
      "social": "Protege empleo y continuidad de PyMEs que pueden quedar críticamente afectadas por un único incidente.",
      "environmental": "No aplica de manera directa.",
      "dominant_dimension": "economic"
    },
    "technologies": ["detección de anomalías", "NLP", "integraciones email/ERP"],
    "tech_rationale": "El problema requiere analizar contexto de comunicaciones, desvíos respecto de patrones aprobatorios y señales de riesgo previas al pago, no solo filtrar spam.",
    "tech_fit_niche": "scoring_comportamental",
    "buyer": "PyMEs, brokers de seguros cyber, cámaras empresarias, MSSPs",
    "end_user": "Equipos de administración, finanzas y tesorería",
    "blocker": "Acceso a correo, flujos de aprobación y datos históricos mínimos para calibración",
    "mvp_feasibility_5_person_team": "alta",
    "time_to_first_customer_months": 3,
    "scalability_rationale": "Se comercializa como SaaS B2B con integraciones relativamente estándar a correo y flujos internos.",
    "replicability_rationale": "Es replicable en múltiples sectores y países porque la lógica del BEC es transnacional.",
    "competitive_landscape_latam": "Existen vendors globales de email security y antifraude, pero las PyMEs regionales siguen subatendidas y sobreindexadas en controles insuficientes.",
    "complexity_justifies_5_person_year": true
  },
  {
    "problem_id": "P003",
    "vertical": "FinTech",
    "sub_segment": "Protección a onboarding y retail crypto contra pig butchering y falsas inversiones",
    "scam_typology": "pig_butchering",
    "primary_vulnerability": "combinada",
    "target_victim_profile": "Usuarios nuevos o de baja experiencia en crypto e inversiones digitales en LATAM",
    "geography_primary": "Argentina",
    "geography_expansion": ["México", "Brasil"],
    "pain_point_one_sentence": "Usuarios que ingresan al ecosistema crypto son manipulados por estafadores a través de relaciones de confianza y falsas oportunidades de inversión, moviendo fondos a direcciones ilícitas sin que el exchange intervenga preventivamente a tiempo.",
    "evidence": {
      "description": "Chainalysis reportó que los ingresos de pig butchering crecieron casi 40% interanual en 2024 y que el número de depósitos aumentó cerca de 210%; Europol e Interpol también destacan el peso creciente del fraude de inversión y del uso de criptoactivos en esquemas globales de fraude financiero.",
      "source_type": "tier_1",
      "source_name": "Chainalysis 2025 Crypto Crime findings on 2024; Europol; Interpol Global Financial Fraud Assessment",
      "year": "2024-2025"
    },
    "who_pays_today": "Exchanges, plataformas crypto, wallets custodiales, programas de compliance y soporte, además del propio usuario víctima aunque éste no sea el comprador ideal.",
    "estimated_annual_cost_usd": "Parte de un ecosistema de scams cripto de miles de millones de dólares; pig butchering ya representa una proporción relevante del scam revenue cripto global.",
    "why_current_solution_fails": "Los exchanges suelen actuar tarde, una vez realizada la transferencia; el onboarding educa pero no personaliza el riesgo ni detecta manipulación contextual.",
    "triple_impact_mapping": {
      "economic": "Reduce pérdidas del usuario y churn reputacional/costo de soporte para exchanges y plataformas.",
      "social": "Protege a nuevos inversores y mejora inclusión financiera segura en activos digitales.",
      "environmental": "No aplica directamente.",
      "dominant_dimension": "economic"
    },
    "technologies": ["AI Agents", "graph analytics", "wallet risk intelligence"],
    "tech_rationale": "La prevención exige combinar señales conductuales, análisis conversacional y evaluación de destinatarios/direcciones de alto riesgo antes de la transferencia.",
    "tech_fit_niche": "infra_compartida",
    "buyer": "Exchanges regionales, fintechs con cripto, custodios, wallets",
    "end_user": "Usuario retail crypto",
    "blocker": "Acceso de producto al momento previo al retiro/transferencia y validación de UX sin fricción excesiva",
    "mvp_feasibility_5_person_team": "media",
    "time_to_first_customer_months": 5,
    "scalability_rationale": "El producto puede ofrecerse como capa de riesgo/API para múltiples plataformas con un mismo core.",
    "replicability_rationale": "Es replicable en distintos mercados donde existan exchanges retail y wallets con alto volumen de onboarding.",
    "competitive_landscape_latam": "Hay vendors de blockchain intelligence, pero menos soluciones orientadas a prevención temprana de manipulación del usuario retail antes del envío.",
    "complexity_justifies_5_person_year": true
  },
  {
    "problem_id": "P004",
    "vertical": "HealthTech",
    "sub_segment": "Protección antifraude para adultos mayores y familias cuidadoras",
    "scam_typology": "falso_familiar",
    "primary_vulnerability": "ingenieria_social",
    "target_victim_profile": "Adultos mayores y familias cuidadoras expuestas a fraude por WhatsApp, llamadas, vishing y suplantación de identidad",
    "geography_primary": "Argentina",
    "geography_expansion": ["México", "Colombia"],
    "pain_point_one_sentence": "Los adultos mayores son blancos desproporcionados de estafas emocionales y de urgencia, y las familias carecen de herramientas preventivas en tiempo real que permitan intervenir antes de la transferencia o entrega de datos.",
    "evidence": {
      "description": "El FBI IC3 informó que las pérdidas por elder fraud llegaron a casi USD 4,9 mil millones en 2024, con 147.127 denuncias y un aumento interanual del 43% en pérdidas. CONDUSEF también reportó una fuerte afectación de adultos mayores en reclamaciones por fraude digital en México.",
      "source_type": "tier_1",
      "source_name": "FBI IC3 2024 Internet Crime Report; CONDUSEF",
      "year": "2024-2025"
    },
    "who_pays_today": "Familias, bancos con programas de protección, aseguradoras, telcos y potencialmente obras sociales o redes de cuidado privadas.",
    "estimated_annual_cost_usd": "USD 4,9 mil millones en pérdidas por elder fraud según IC3 2024.",
    "why_current_solution_fails": "La prevención hoy se basa en consejos generales ex post; no existe protección conversacional y contextual suficientemente integrada en la vida digital de usuarios vulnerables.",
    "triple_impact_mapping": {
      "economic": "Evita pérdida patrimonial de hogares y costos derivados para bancos y familias.",
      "social": "El impacto social es muy alto porque protege autonomía, salud mental y patrimonio de poblaciones vulnerables.",
      "environmental": "No aplica directamente.",
      "dominant_dimension": "social"
    },
    "technologies": ["voice/deepfake detection", "AI Agents", "alertas de confianza"],
    "tech_rationale": "Para ser útil, la solución debe captar señales de coerción, urgencia, suplantación o anomalía conductual en tiempo real y escalar alertas a círculos de confianza.",
    "tech_fit_niche": "proteccion_vulnerables",
    "buyer": "Familias, bancos con segmentos senior, telcos, aseguradoras",
    "end_user": "Adultos mayores y cuidadores",
    "blocker": "Modelo de monetización inicial y desafío de adopción por parte del usuario final vulnerable",
    "mvp_feasibility_5_person_team": "media",
    "time_to_first_customer_months": 5,
    "scalability_rationale": "Puede escalar como app/SDK con red de alertas y motor de riesgo; no requiere atención manual individual para cada incidente.",
    "replicability_rationale": "El patrón de fraude a adultos mayores existe en múltiples países y canales de comunicación.",
    "competitive_landscape_latam": "Mercado fragmentado; hay escasa oferta regional especializada en protección digital senior antifraude.",
    "complexity_justifies_5_person_year": true
  },
  {
    "problem_id": "P005",
    "vertical": "EduTech",
    "sub_segment": "Educación adaptativa embebida para prevención de fraude financiero",
    "scam_typology": "falso_broker",
    "primary_vulnerability": "educacion_financiera",
    "target_victim_profile": "Usuarios de fintechs, bancos y exchanges con baja comprensión de riesgo financiero, rendimientos irreales y señales de fraude de inversión",
    "geography_primary": "Argentina",
    "geography_expansion": ["México", "Brasil"],
    "pain_point_one_sentence": "Las entidades financieras y plataformas digitales pierden usuarios y reputación porque millones de personas ingresan a productos complejos sin capacidades suficientes para detectar rendimientos irreales, falsas promesas de inversión o esquemas Ponzi disfrazados.",
    "evidence": {
      "description": "El IC3 ubicó a investment fraud como la tipología con mayores pérdidas en 2024, con más de USD 6,5 mil millones. Interpol también identifica el fraude de inversión como una de las modalidades financieras más prevalentes a nivel global.",
      "source_type": "tier_1",
      "source_name": "FBI IC3 2024 Internet Crime Report; Interpol Global Financial Fraud Assessment 2024",
      "year": "2024"
    },
    "who_pays_today": "Bancos, exchanges, fintechs, programas de compliance/educación al usuario y potencialmente aseguradoras o cámaras sectoriales.",
    "estimated_annual_cost_usd": "USD 6,5 mil millones en investment fraud reportados por IC3 2024.",
    "why_current_solution_fails": "La educación financiera suele ser estática, generalista y desconectada del momento de decisión del usuario; no actúa como freno contextual.",
    "triple_impact_mapping": {
      "economic": "Reduce pérdidas del usuario y churn reputacional para plataformas financieras.",
      "social": "Mejora inclusión financiera segura y reduce asimetría informativa.",
      "environmental": "No aplica directamente.",
      "dominant_dimension": "social"
    },
    "technologies": ["educación adaptativa", "simuladores", "modelos de riesgo por perfil"],
    "tech_rationale": "La única forma de que la educación genere resultados es integrarla al flujo del producto con personalización, simulación y evaluación dinámica de riesgo.",
    "tech_fit_niche": "educacion_adaptativa",
    "buyer": "Fintechs, bancos digitales, exchanges, áreas de compliance y customer success",
    "end_user": "Usuario retail con baja alfabetización financiera",
    "blocker": "Riesgo de que el proyecto se perciba como contenido educativo y no como producto antifraude con ventaja tecnológica suficiente",
    "mvp_feasibility_5_person_team": "alta",
    "time_to_first_customer_months": 4,
    "scalability_rationale": "Escala como módulo SaaS/SDK embebido en journeys digitales.",
    "replicability_rationale": "Es replicable en distintos productos financieros y geografías con adaptación cultural y regulatoria moderada.",
    "competitive_landscape_latam": "Abundancia de contenido educativo general, pero poca prevención adaptativa embebida y accionable en tiempo real.",
    "complexity_justifies_5_person_year": true
  }
]
```

---

## Comentario analítico breve de Fase 1

Los cinco problemas seleccionados cumplen, con distintos niveles de fortaleza, las condiciones mínimas planteadas en la consigna:

- tienen **escala regional o global**;
- presentan una **fricción económica concreta**;
- admiten una **solución tecnológicamente no trivial**;
- pueden escalar como software o infraestructura;
- y justifican una dedicación anual de un equipo de cinco personas.

No obstante, no todos muestran el mismo nivel de robustez frente a un profesor escéptico o un potencial cliente. Por ello, resulta indispensable aplicar una segunda fase de validación crítica.

---

# FASE 2. Validación crítica

## Parte A. Tabla de scoring

| Problem ID | Problema resumido | A Urgencia | B Evidencia | C WTP | D Ventaja tech | E Escala/replic. | F Competencia | G Nivel académico | Total /35 | X Triple impacto | Veredicto |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|
| P001 | Prevención de phishing/smishing e ingeniería social en banca y billeteras | 5 | 4 | 5 | 4 | 5 | 3 | 4 | 30 | 4 | **Pasa** |
| P002 | Protección PyME contra BEC y fraude de transferencias | 5 | 5 | 5 | 4 | 5 | 3 | 5 | 32 | 4 | **Pasa** |
| P003 | Prevención de pig butchering y falsas inversiones en crypto retail | 5 | 4 | 4 | 5 | 5 | 3 | 5 | 31 | 4 | **Pasa** |
| P004 | Protección de adultos mayores y familias frente a fraude emocional/digital | 5 | 5 | 3 | 4 | 4 | 4 | 4 | 29 | 5 | **Pasa** |
| P005 | Educación adaptativa embebida contra fraude de inversión | 4 | 4 | 3 | 3 | 5 | 2 | 3 | 24 | 4 | **Descartado por score bajo** |

### Justificación sintética del scoring

- **P001** puntúa alto por frecuencia, buyer claro y replicabilidad, aunque enfrenta competencia de vendors antifraude y behavioral analytics ya instalados.
- **P002** resulta especialmente sólido porque combina una pérdida económica evidente, buyer corporativo nítido, urgencia operativa y complejidad técnica defendible.
- **P003** presenta enorme urgencia y complejidad técnica, con buena narrativa regional por el crecimiento de adopción crypto; su principal riesgo es la complejidad comercial de vender a exchanges o fintechs cripto.
- **P004** tiene el triple impacto más potente, pero su buyer no siempre es tan claro ni tan inmediato como en banca o B2B.
- **P005** no queda eliminado por evidencia ni por buyer, sino por quedar demasiado cerca del límite de “producto educativo avanzado” y demasiado lejos, por ahora, de una ventaja técnica contundente ante un evaluador exigente.

---

## Parte B. Top 3 problemas validados

# 1) P002. Protección de PyMEs contra BEC y fraude de transferencias

### Resumen ejecutivo
Las PyMEs siguen perdiendo dinero por instrucciones de pago fraudulentas que parecen legítimas y que atraviesan correo, mensajería y circuitos internos de aprobación. No cuentan con controles suficientemente sofisticados para detectar estas maniobras antes de transferir. Un sistema que evalúe riesgo contextual de pagos y comunicaciones puede reducir pérdidas directas y mejorar su postura de riesgo sin exigirles un equipo de ciberseguridad propio.

### Discovery question
**¿Cuántas veces en los últimos 12 meses tuvieron que frenar, investigar o lamentar una transferencia o cambio de cuenta sospechoso iniciado por mail o WhatsApp, y qué control concreto tenían antes de autorizar ese pago?**

### Proxy de validación rápida
- Buscar job postings de analistas de fraude, treasury controls o email security en bancos, aseguradoras, MSSPs y empresas medianas.
- Revisar comunicaciones de aseguradoras cyber y brokers sobre BEC como causa de siniestros.
- Relevar cámaras PyME o estudios de riesgo empresarial que hablen de fraude por email y transferencias.
- Buscar vendors globales que empujen esta categoría en LATAM, señal de presupuesto ya existente.

### Pitch académico de 60 segundos
El problema no es simplemente “phishing corporativo”, sino la incapacidad de miles de PyMEs para detectar fraude financiero contextual antes de emitir una transferencia. Es un problema regional, con pérdidas multimillonarias y buyer claro. La solución exige ingeniería real: análisis de comunicación, scoring de anomalías, patrones aprobatorios y señales conductuales, integradas a correo y flujo de pagos. Escala como SaaS B2B y es replicable en múltiples países. Su triple impacto surge de proteger patrimonio empresarial, continuidad operativa y empleo, especialmente en organizaciones sin áreas avanzadas de seguridad.

---

# 2) P003. Protección de onboarding y retail crypto contra pig butchering y falsas inversiones

### Resumen ejecutivo
Los usuarios nuevos de crypto están siendo manipulados por redes de fraude que combinan vínculo emocional, falsa educación financiera y transferencia de fondos a direcciones ilícitas. Las plataformas suelen llegar tarde, cuando el dinero ya salió. Un sistema preventivo antes del retiro o la transferencia permitiría reducir pérdidas, soporte reactivo y daño reputacional.

### Discovery question
**¿Qué porcentaje de sus tickets o casos de soporte más costosos involucran usuarios que transfirieron fondos a terceros después de recibir “asesoramiento” externo o señales de inversión que ustedes no generaron?**

### Proxy de validación rápida
- Revisar blogs oficiales y centros de ayuda de exchanges que advierten sobre pig butchering, romance-investment scams y fake investment mentors.
- Buscar reportes de blockchain intelligence sobre scam wallets, consumer protection y retail onboarding.
- Analizar políticas de riesgo y warning flows de exchanges regionales para ver dónde todavía no existe una intervención real antes de la salida de fondos.

### Pitch académico de 60 segundos
Este problema combina una amenaza global en expansión con una ventana de producto muy concreta: el momento previo al envío de fondos desde el usuario retail. No alcanza con educación general ni con listas de direcciones bloqueadas. Se necesita una solución de riesgo multimodal que combine señales conversacionales, graph analytics y wallet intelligence para frenar estafas antes del daño. Tiene alta complejidad técnica, mercado regional claro y proyección global, especialmente en plataformas con alto volumen de onboarding de usuarios novatos.

---

# 3) P001. Prevención de phishing/smishing e ingeniería social en banca y billeteras

### Resumen ejecutivo
Bancos y billeteras continúan enfrentando fraude originado no solo en vulnerabilidades técnicas, sino en manipulación psicológica del cliente. El costo no es solo la pérdida monetaria: incluye reintegros, soporte, reclamos y erosión de confianza. Una capa de detección contextual antes de la autorización puede disminuir estos costos y actuar donde los mensajes educativos tradicionales fracasan.

### Discovery question
**¿Qué proporción de sus casos de fraude reportado comienza con una interacción de suplantación por SMS, WhatsApp o llamada que convence al cliente de compartir credenciales, OTP o aprobar una operación?**

### Proxy de validación rápida
- Buscar comunicados públicos de bancos y reguladores sobre phishing, smishing, vishing y fraude por suplantación.
- Revisar presupuestos de vendors de antifraude, autenticación y behavioral biometrics en instituciones financieras.
- Relevar campañas oficiales repetidas de educación bancaria: si se repiten, es porque el problema persiste y genera gasto real.

### Pitch académico de 60 segundos
Se trata de un problema financiero masivo, no de una simple campaña de concientización. El desafío técnico consiste en detectar manipulación, riesgo contextual y señales de ingeniería social antes de que el usuario complete una acción de alto riesgo. La solución puede implementarse como capa de scoring y asistencia inteligente sobre canales ya existentes, por lo que escala regionalmente. Combina impacto económico claro para bancos y fintechs con una dimensión social fuerte al proteger a usuarios vulnerables de pérdidas evitables.

---

## Parte C. Mini business case para el problema número 1 del ranking

# Problema priorizado: P002. Protección de PyMEs contra BEC y fraude de transferencias

## Problema
Las PyMEs pierden dinero cuando un atacante suplanta a un proveedor, directivo o contraparte y logra inducir una transferencia o un cambio de cuenta bancaria aparentemente legítimo. Según el **FBI IC3 2024 Internet Crime Report**, el **BEC registró 21.442 denuncias y aproximadamente USD 2,77 mil millones en pérdidas durante 2024**, lo que confirma que no se trata de un riesgo marginal sino de una categoría con impacto económico extraordinario.

## Triple impacto mapeado
- **Económico:** evita pérdidas directas por transferencias fraudulentas, interrupciones operativas, horas hombre de investigación y potencial litigio con proveedores o clientes.
- **Social:** protege la continuidad de PyMEs, el empleo y la resiliencia de organizaciones que suelen carecer de equipos maduros de ciberseguridad.
- **Ambiental:** el efecto ambiental es indirecto y bajo; no constituye una razón principal de compra.

## Solución propuesta
Construir una plataforma SaaS que analice en tiempo real correos, instrucciones de pago y cambios de cuenta para detectar anomalías de contexto, lenguaje, comportamiento aprobatorio y riesgo operacional antes de que la transferencia sea ejecutada.

## Stack tecnológico
- **NLP y análisis semántico:** para detectar lenguaje de urgencia, cambio abrupto de instrucciones, patrones de suplantación y señales de pretexting.
- **Scoring de riesgo comportamental:** para comparar la solicitud con patrones históricos de aprobación, monto, proveedor, horario y circuito interno.
- **Integraciones con correo y sistemas administrativos/ERP livianos:** para insertar el control en el punto operativo donde nace el pago.
- **Motor de reglas y explainability:** para que la solución sea auditable, defendible y usable por equipos no técnicos.

La elección es correcta porque el problema ocurre en un punto intermedio entre la comunicación y la autorización financiera. Ni un filtro de spam tradicional ni una capacitación anual resuelven esa brecha.

## Mercado
### TAM
PyMEs y mid-market companies de LATAM que realizan pagos digitales frecuentes y dependen de correo/mensajería para coordinar transferencias.

### SAM
Empresas de Argentina, México y Colombia en sectores con alto volumen de pagos a proveedores, tesorería distribuida y bajo nivel de controles antifraude avanzados.

### SOM
Primeras decenas de clientes en Argentina dentro de PyMEs de servicios, comercio exterior, distribución, tecnología y estudios profesionales con riesgo operativo visible.

El potencial global es alto porque el BEC es transnacional y no depende de una regulación local específica.

## Comprador
- **Quién firma:** dueño, CFO, gerente de administración/finanzas, CISO externalizado o broker de seguro cyber que recomienda el control.
- **Cuánto pagaría por año:** como hipótesis de entrada, un ticket anual B2B SaaS medio, significativamente menor al costo esperado de un solo incidente relevante.
- **Por qué cambiaría:** porque hoy su alternativa suele ser una mezcla débil de capacitación, doble chequeo manual y herramientas genéricas de correo que no frenan fraude contextual de pago.

## Competencia
### Competidores/sustitutos relevantes
1. **Secure email gateways y suites de seguridad corporativa**: protegen contra spam y phishing, pero no necesariamente validan el riesgo de una orden de pago contextual.
2. **Procedimientos manuales internos**: dependen de disciplina humana, escalan mal y fallan frente a urgencia o autoridad simulada.
3. **Vendors globales de fraude/compliance**: suelen apuntar a enterprise o banca, dejando un gap de accesibilidad para PyMEs regionales.

### Ángulo diferencial
La oportunidad no es competir frontalmente con una suite enterprise completa, sino resolver una brecha específica y costosa para organizaciones medianas: la validación inteligente previa al pago fraudulento inducido por ingeniería social.

## Escalabilidad y replicabilidad
El producto escala como software porque una vez desarrollado el motor de scoring, cada nuevo cliente agrega principalmente integración, parametrización y aprendizaje del modelo, no atención manual proporcional. Es replicable en distintos países y verticales porque el patrón de BEC, cambio de cuenta fraudulento y suplantación de proveedor es estructuralmente similar.

## Modelo de monetización
La forma más simple de monetización en los primeros 12 meses es **SaaS B2B por empresa**, con pricing escalonado por volumen de usuarios, buzones monitoreados o flujos de aprobación protegidos. También puede explorarse un canal indirecto vía brokers de seguros cyber o MSSPs.

## MVP
En 3 meses, un equipo de 5 personas puede construir un MVP funcional compuesto por:
- integración con Gmail/Microsoft 365;
- scoring básico de riesgo de correo e instrucción de pago;
- dashboard de alertas con razones explicables;
- workflow de validación reforzada para cambios de cuenta y pagos atípicos;
- dataset inicial sintético + casos públicos para calibración temprana.

Ese MVP ya podría ser usado por una PyME piloto y generar validación comercial real.

## Primer paso de validación
Esta semana, la acción concreta más valiosa sería **entrevistar a responsables de administración/finanzas o brokers de seguros cyber que atiendan PyMEs argentinas** y preguntar por incidentes o near misses de cambio de cuenta fraudulento y aprobación de transferencias. En paralelo, conviene relevar comunidades y cámaras empresarias donde el problema pueda emerger con lenguaje operativo, no técnico.

## Riesgo principal
El mayor riesgo es que el comprador perciba que el problema se resuelve “con procedimiento interno” y no reconozca la necesidad de una capa tecnológica adicional hasta después de sufrir una pérdida fuerte.

## Defensa ante el veto del profesor
**Objeción probable:** “Esto ya lo hace el correo corporativo o el banco; no hay startup acá”.

**Respuesta:** no, porque el problema no se limita al phishing genérico ni a la autenticación bancaria, sino a la **detección contextual de fraude de pago inducido por ingeniería social**. La evidencia del FBI IC3 muestra que BEC continúa generando pérdidas multimillonarias aun en organizaciones que ya usan correo corporativo, antivirus y MFA. Por lo tanto, existe una brecha real entre infraestructura básica y prevención efectiva del fraude operacional. Esa brecha justifica un producto específico, escalable y tecnológicamente defendible.

---

# Conclusión general

El análisis muestra que el dominio de las estafas financieras sí contiene oportunidades de startup reales, pero no en cualquier recorte. Los problemas con mejor perfil para una tesis-emprendimiento no son los meramente educativos ni los excesivamente dependientes de campañas de concientización, sino aquellos donde confluyen cuatro elementos: **pérdida económica material, buyer identificable, intervención tecnológica en tiempo real y posibilidad de escalar regionalmente**.

Bajo ese criterio, los tres problemas con mejor desempeño son:

1. **BEC y fraude de transferencias en PyMEs**.
2. **Prevención de pig butchering y falsas inversiones en crypto retail**.
3. **Prevención de phishing/smishing e ingeniería social en banca y billeteras**.

De los tres, **BEC en PyMEs** aparece como la opción más sólida para avanzar en discovery real inmediato, porque combina evidencia robusta, comprador relativamente accesible, complejidad técnica defendible y menor dependencia de integraciones institucionales pesadas desde el día uno.

---

# Fuentes visibles

## Tier 1
1. **FBI Internet Crime Complaint Center (IC3).** *2024 Internet Crime Report.* Hallazgos usados: pérdidas totales por ciberdelito, investment fraud, BEC y elder fraud.  
   URL de referencia institucional: <https://www.ic3.gov>

2. **Verizon.** *2025 Data Breach Investigations Report (DBIR).* Hallazgos usados: relevancia del factor humano, pretexting, phishing y contexto empresarial.  
   URL de referencia: <https://www.verizon.com/business/resources/reports/dbir/>

3. **Interpol.** *Global Financial Fraud Assessment 2024.* Hallazgos usados: prevalencia global del fraude de inversión y creciente sofisticación del fraude financiero transnacional.  
   URL de referencia: <https://www.interpol.int>

4. **Europol / Eurojust.** *Common Challenges in Cybercrime 2024.* Hallazgos usados: peso del fraude online y del fraude de inversión con criptoactivos.  
   URL de referencia: <https://www.europol.europa.eu>

5. **Chainalysis.** Reportes y hallazgos 2025 sobre revenue observado en 2024 para pig butchering y scam ecosystems cripto.  
   URL de referencia: <https://www.chainalysis.com>

6. **CONDUSEF (México).** Comunicaciones y alertas sobre fraudes digitales y afectación de adultos mayores.  
   URL de referencia: <https://www.condusef.gob.mx>

## Tier 2 / apoyo contextual
7. **Nacha.** Comunicación basada en IC3 sobre pérdidas por BEC acumuladas entre 2022 y 2024.  
   URL: <https://www.nacha.org>

---

## Nota final de integridad
Este documento evitó introducir cifras no verificadas con suficiente respaldo institucional. Cuando una afirmación no pudo contrastarse con claridad o el dato exacto no estaba disponible en fuente oficial accesible, se mantuvo la redacción en términos prudentes y no concluyentes.
