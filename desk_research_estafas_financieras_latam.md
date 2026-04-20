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

---

# MÓDULO COMPLEMENTARIO. Validación metodológica, Desk Research + User Research

## Introducción del módulo complementario

A partir del entregable ya desarrollado en Fase 1 y Fase 2, este módulo complementario incorpora una capa metodológica adicional orientada a validar la **existencia empírica, frecuencia, dolor y disposición a pagar** asociadas a los cinco problemas identificados. El cambio de foco es deliberado: mientras que las secciones anteriores priorizaron discovery estratégico y priorización de oportunidades, el presente módulo se concentra exclusivamente en la **validación del problema**, sin diseñar soluciones ni profundizar en la elección tecnológica.

En términos metodológicos, este módulo parte de una premisa central: **ningún problema queda verdaderamente validado solo por desk research o solo por entrevistas**. La investigación secundaria permite medir escala, visibilidad pública, señal regulatoria y actividad económica ya existente. La investigación primaria, en cambio, permite verificar si el dolor se experimenta realmente como tal, si es frecuente en la vida de los actores involucrados y si alguien estaría dispuesto a pagar por resolverlo. Recién cuando ambos tracks convergen puede hablarse de validación fuerte.

Por esa razón, este módulo se estructura en tres partes:

1. **Track A. Desk Research metodológico complementario**.
2. **Track B. User Research, con instrumentos listos para ejecutar por el equipo**.
3. **Convergencia y criterios de validación cruzada**.

Es importante subrayar una restricción de honestidad metodológica: **en este documento no se inventa trabajo de campo no realizado**. Por lo tanto, el Track B queda desarrollado como diseño de investigación listo para implementación, pero no presenta hallazgos empíricos que todavía no fueron recolectados por el equipo.

---

# TRACK A. Desk Research metodológico complementario

## Objetivo del Track A

El objetivo del Track A es ampliar la validación secundaria de cada problema a partir de seis señales metodológicas adicionales:

- señal laboral;
- señal regulatoria;
- señal de mercado privado y funding;
- señal mediática y de volumen público;
- señal del usuario final en apps y foros;
- señal académica y de literatura gris.

A diferencia del desk research de la Fase 1, que fue construido para formular y priorizar problemas, este Track A busca responder una pregunta más estricta: **¿existen pruebas suficientes de que el problema es real, persistente y económicamente relevante?**

## Alcance metodológico del Track A en este entregable

Dado que el objetivo de este trabajo es mantener integridad académica y no simular búsquedas no ejecutadas, el Track A se presenta aquí como una **matriz metodológica aplicada**, con:

- señal observada o inferida a partir del desk research ya realizado;
- nivel de fuerza de esa señal;
- lectura crítica de lo que todavía falta relevar con mayor profundidad;
- veredicto preliminar de validación secundaria.

Esto permite que el documento conserve valor académico inmediato y, al mismo tiempo, funcione como plan de trabajo concreto para continuar la validación.

---

## Track A por problema

### P001. Prevención de phishing, smishing e ingeniería social en banca y billeteras

```json
{
  "problem_id": "P001",
  "desk_research": {
    "A1_job_signal": {
      "postings_found": "señal parcial no cuantificada en este entregable",
      "top_employers": ["bancos", "fintechs", "procesadores de pago"],
      "signal_strength": "moderada",
      "notes": "La existencia de equipos antifraude y customer protection en banca y fintech sugiere presupuesto ya asignado, aunque en este trabajo no se levantó aún una tabla sistemática de postings por país."
    },
    "A2_regulatory_signal": {
      "sources_found": ["CONDUSEF", "IC3/FBI"],
      "key_stat_or_norm": "Existe documentación institucional sobre fraude digital, phishing y afectación a usuarios vulnerables; el problema supera el umbral de relevancia pública.",
      "signal_strength": "fuerte",
      "notes": "La señal regulatoria es clara en términos de alertas y documentación pública, aunque convendría profundizar con barrido específico en BCRA, CNBV y Banco Central do Brasil."
    },
    "A3_startup_signal": {
      "startups_found": ["vendors globales de antifraude transaccional y behavioral biometrics"],
      "implication": "mercado_validado",
      "notes": "La existencia de actores consolidados confirma mercado, aunque también eleva la exigencia de diferenciación."
    },
    "A4_media_signal": {
      "coverage_volume": "alta",
      "trend": "estable",
      "notes": "El phishing, smishing y la suplantación bancaria mantienen presencia sostenida en cobertura periodística y campañas institucionales."
    },
    "A5_app_forum_signal": {
      "pattern_found": "reclamos recurrentes por transferencias no reconocidas, bloqueo preventivo, soporte insuficiente o percepción de exposición frente a engaños externos",
      "frequency": "media",
      "notes": "La señal existe, pero debe sistematizarse con revisión explícita de apps bancarias y wallets locales."
    },
    "A6_academic_signal": {
      "papers_found": "más de una línea de evidencia internacional relevante",
      "key_finding": "El factor humano, el phishing y la ingeniería social siguen apareciendo como vectores estructurales de fraude y breach.",
      "signal_strength": "fuerte",
      "notes": "DBIR, IC3 y literatura de ciberseguridad sostienen la validez del problema, aun cuando LATAM tenga menor producción académica específica."
    },
    "desk_research_verdict": "validado",
    "desk_research_confidence": "media",
    "desk_research_summary": "El problema está validado a nivel secundario: existe evidencia institucional sólida, visibilidad pública persistente y señal de presupuesto en el sistema financiero. Falta, sin embargo, profundizar la cuantificación regional comparada."
  }
}
```

### P002. Protección de PyMEs contra BEC y fraude de transferencias

```json
{
  "problem_id": "P002",
  "desk_research": {
    "A1_job_signal": {
      "postings_found": "señal fuerte inferida",
      "top_employers": ["empresas medianas", "MSSPs", "aseguradoras cyber"],
      "signal_strength": "moderada",
      "notes": "La existencia de funciones de tesorería, fraude, compliance y email security sugiere gasto real, aunque debe medirse mejor con scraping o relevamiento manual de postings."
    },
    "A2_regulatory_signal": {
      "sources_found": ["IC3/FBI", "Verizon DBIR"],
      "key_stat_or_norm": "BEC produjo aproximadamente USD 2,77 mil millones en pérdidas reportadas en 2024.",
      "signal_strength": "fuerte",
      "notes": "Aunque la evidencia más dura es internacional, el carácter transnacional del BEC vuelve altamente transferible la señal al contexto LATAM."
    },
    "A3_startup_signal": {
      "startups_found": ["vendors de email security", "fraud prevention", "cyber insurance enablement"],
      "implication": "mercado_validado",
      "notes": "La inversión global en esta categoría confirma que el problema ya es reconocido por el mercado privado."
    },
    "A4_media_signal": {
      "coverage_volume": "media",
      "trend": "alza",
      "notes": "El BEC suele recibir menos cobertura masiva que las estafas a consumidores, pero aparece con frecuencia en prensa de ciberseguridad, seguros y riesgo corporativo."
    },
    "A5_app_forum_signal": {
      "pattern_found": "no aplica con igual claridad en app stores; la voz del usuario emerge más en comunidades empresariales, foros profesionales y casos compartidos por brokers o áreas de administración",
      "frequency": "baja",
      "notes": "La menor presencia en reviews públicas no invalida el problema; simplemente refleja que el buyer es institucional."
    },
    "A6_academic_signal": {
      "papers_found": "línea robusta en reportes sectoriales y literatura de fraude organizacional",
      "key_finding": "El pretexting y la ingeniería social siguen siendo mecanismos centrales de fraude empresarial y compromisos de pago.",
      "signal_strength": "fuerte",
      "notes": "La literatura y los reportes de incidentes sostienen con claridad que se trata de un problema persistente y costoso."
    },
    "desk_research_verdict": "validado",
    "desk_research_confidence": "alta",
    "desk_research_summary": "Es el problema con mejor validación secundaria del conjunto. Tiene magnitud económica clara, buyer institucional identificable y una lógica transnacional muy bien documentada."
  }
}
```

### P003. Protección de onboarding y retail crypto contra pig butchering y falsas inversiones

```json
{
  "problem_id": "P003",
  "desk_research": {
    "A1_job_signal": {
      "postings_found": "señal parcial",
      "top_employers": ["exchanges", "crypto compliance teams", "blockchain intelligence firms"],
      "signal_strength": "moderada",
      "notes": "La expansión de funciones ligadas a compliance, transaction monitoring y fraud/risk en crypto sugiere WTP institucional creciente."
    },
    "A2_regulatory_signal": {
      "sources_found": ["Interpol", "Europol"],
      "key_stat_or_norm": "El fraude de inversión y el uso de criptoactivos en esquemas de fraude financiero ya están identificados como problema de escala internacional.",
      "signal_strength": "moderada",
      "notes": "La señal es fuerte a nivel global, pero todavía menos granular en estadística oficial LATAM."
    },
    "A3_startup_signal": {
      "startups_found": ["blockchain intelligence", "wallet screening", "crypto compliance"],
      "implication": "mercado_emergente",
      "notes": "Existe mercado validado en infraestructura de riesgo, aunque la prevención centrada en manipulación del usuario retail sigue menos explotada."
    },
    "A4_media_signal": {
      "coverage_volume": "alta",
      "trend": "alza",
      "notes": "Pig butchering, romance-investment scams y fraude cripto muestran fuerte crecimiento de cobertura en medios especializados y generalistas."
    },
    "A5_app_forum_signal": {
      "pattern_found": "usuarios que reportan bloqueos, pérdidas, transferencias inducidas o advertencias tardías en plataformas crypto",
      "frequency": "media",
      "notes": "La señal del usuario final existe y probablemente sea muy rica en foros, Trustpilot y reviews de exchanges, aunque requiere sistematización posterior."
    },
    "A6_academic_signal": {
      "papers_found": "evidencia creciente pero aún fragmentada",
      "key_finding": "El fraude de inversión mediado por cripto y el scam-as-a-service muestran profesionalización creciente.",
      "signal_strength": "moderada",
      "notes": "Hay una base suficiente para sostener el problema, aunque todavía menos madura que en BEC o phishing bancario."
    },
    "desk_research_verdict": "parcialmente_validado",
    "desk_research_confidence": "media",
    "desk_research_summary": "El problema tiene una señal fuerte de crecimiento y relevancia económica, pero necesita mayor profundización en buyer específico y casuística regional para subir a validación secundaria alta."
  }
}
```

### P004. Protección antifraude para adultos mayores y familias cuidadoras

```json
{
  "problem_id": "P004",
  "desk_research": {
    "A1_job_signal": {
      "postings_found": "señal débil",
      "top_employers": ["bancos", "programas de customer protection", "áreas de inclusión financiera"],
      "signal_strength": "débil",
      "notes": "El problema es muy visible socialmente, pero no necesariamente genera roles especializados fácilmente identificables en mercado laboral."
    },
    "A2_regulatory_signal": {
      "sources_found": ["IC3/FBI", "CONDUSEF"],
      "key_stat_or_norm": "Las pérdidas por elder fraud alcanzaron casi USD 4,9 mil millones en 2024 según IC3.",
      "signal_strength": "fuerte",
      "notes": "La señal estadística es clara y la afectación de adultos mayores también aparece en organismos de protección financiera."
    },
    "A3_startup_signal": {
      "startups_found": ["protección senior", "family safety", "fraud protection tools"],
      "implication": "mercado_emergente",
      "notes": "No parece existir todavía un mercado regional consolidado, pero sí un espacio emergente donde la señal social es fuerte."
    },
    "A4_media_signal": {
      "coverage_volume": "alta",
      "trend": "estable",
      "notes": "Las estafas a adultos mayores mantienen una presencia mediática sostenida por su gravedad humana y simbólica."
    },
    "A5_app_forum_signal": {
      "pattern_found": "reclamos de familiares, miedo a operaciones no reconocidas, llamadas engañosas y sensación de desprotección",
      "frequency": "media",
      "notes": "La voz del usuario aparece más por relatos familiares y foros que por reviews estructuradas de producto."
    },
    "A6_academic_signal": {
      "papers_found": "evidencia internacional robusta sobre vulnerabilidad y elder fraud",
      "key_finding": "Las poblaciones mayores presentan exposición diferenciada a fraude por factores cognitivos, emocionales y digitales.",
      "signal_strength": "fuerte",
      "notes": "El problema es investigable y consistente, aunque la monetización sigue siendo menos nítida."
    },
    "desk_research_verdict": "parcialmente_validado",
    "desk_research_confidence": "media",
    "desk_research_summary": "La existencia y gravedad del problema están claramente validadas, pero el buyer y la estructura de gasto todavía necesitan prueba adicional."
  }
}
```

### P005. Educación adaptativa embebida para prevención de fraude financiero

```json
{
  "problem_id": "P005",
  "desk_research": {
    "A1_job_signal": {
      "postings_found": "señal parcial",
      "top_employers": ["fintechs", "áreas de educación financiera", "customer success/compliance"],
      "signal_strength": "débil",
      "notes": "La señal laboral existe, pero no necesariamente prueba que haya presupuesto específico para educación antifraude adaptativa."
    },
    "A2_regulatory_signal": {
      "sources_found": ["IC3/FBI", "Interpol"],
      "key_stat_or_norm": "El fraude de inversión es una categoría de pérdidas masivas; sin embargo, la regulación no prueba por sí sola que la educación adaptativa sea el mecanismo de compra preferido.",
      "signal_strength": "moderada",
      "notes": "La señal regula el problema macro, pero no valida de forma directa este recorte específico."
    },
    "A3_startup_signal": {
      "startups_found": ["edtech financiera", "embedded education modules"],
      "implication": "sin_señal",
      "notes": "Existen iniciativas cercanas, pero la frontera entre contenido, feature y producto sigue siendo ambigua."
    },
    "A4_media_signal": {
      "coverage_volume": "media",
      "trend": "estable",
      "notes": "La agenda pública confirma preocupación por la falta de educación financiera, pero menos por este formato específico de solución."
    },
    "A5_app_forum_signal": {
      "pattern_found": "usuarios confundidos por productos financieros, promesas de retorno y escasa claridad educativa",
      "frequency": "media",
      "notes": "La fricción educativa existe, pero no necesariamente se traduce en demanda explícita por una capa adaptativa independiente."
    },
    "A6_academic_signal": {
      "papers_found": "evidencia sobre financial literacy y vulnerabilidad a fraude",
      "key_finding": "La baja alfabetización financiera aumenta exposición a estafas e inversiones engañosas.",
      "signal_strength": "moderada",
      "notes": "El problema de base está validado, pero el recorte de mercado y buyer sigue siendo metodológicamente más débil."
    },
    "desk_research_verdict": "inconcluso",
    "desk_research_confidence": "baja",
    "desk_research_summary": "Existe evidencia sobre la vulnerabilidad, pero todavía no alcanza para sostener con seguridad que esta formulación particular del problema tenga fuerza de mercado autónoma."
  }
}
```

---

## Tabla sintética de veredictos del Track A

| Problem ID | Veredicto Track A | Confianza | Lectura sintética |
|---|---|---|---|
| P001 | Validado | Media | Problema real y persistente, con fuerte señal institucional y financiera. |
| P002 | Validado | Alta | Problema muy sólido, con magnitud económica clara y buyer nítido. |
| P003 | Parcialmente validado | Media | Fuerte señal global y de crecimiento, pero falta mayor prueba regional y comercial. |
| P004 | Parcialmente validado | Media | Problema socialmente muy fuerte, con buyer todavía menos definido. |
| P005 | Inconcluso | Baja | La vulnerabilidad existe, pero el framing de mercado sigue siendo débil. |

---

# TRACK B. User Research

## Introducción metodológica del Track B

El Track B traduce los cinco problemas identificados en un plan de investigación primaria ejecutable por el equipo. Su función no es complementar decorativamente el desk research, sino poner a prueba los supuestos implícitos detrás de cada formulación del problema.

En línea con el enfoque de Design Research y venture validation, este track se apoya en cuatro principios:

1. **No hablar de soluciones** durante el fieldwork.
2. **Preguntar por hechos pasados**, no por opiniones hipotéticas.
3. **Registrar contradicciones** entre lo que la persona dice que haría y lo que efectivamente hizo.
4. **Distinguir dolor real de preocupación abstracta**.

Dado que en este punto del trabajo el equipo todavía no ejecutó entrevistas sistemáticas, lo que sigue no es una síntesis de resultados, sino el **diseño completo de los instrumentos** para salir a campo correctamente.

---

## B1. Assumption Log por problema

### P001. Prevención de phishing, smishing e ingeniería social en banca y billeteras

**Supuestos críticos**
- Los usuarios efectivamente viven episodios de manipulación previa a la operación y no solo errores de uso o fraude técnico externo.
- Bancos y fintechs perciben este problema como un costo prevenible y no únicamente como un riesgo residual inevitable.
- La ingeniería social previa a la transferencia es suficientemente frecuente como para justificar compra institucional.

**Supuestos relevantes**
- Los usuarios asocian el problema al banco o billetera, aunque el engaño haya ocurrido por canales externos.
- Los equipos antifraude diferencian entre fraude transaccional clásico y fraude inducido por manipulación del cliente.
- Los episodios ocurren en canales repetibles como WhatsApp, SMS o llamada telefónica.

**Supuestos operacionales**
- El punto de intervención más útil ocurre antes de la autorización.
- Los clientes aceptarían fricción adicional si el riesgo fuera claro.

### P002. Protección de PyMEs contra BEC y fraude de transferencias

**Supuestos críticos**
- Las PyMEs realmente experimentan o rozan episodios de cambio de cuenta, instrucciones urgentes de pago o aprobación fraudulenta.
- El dolor económico y reputacional es suficiente para gatillar compra.
- Las PyMEs no están bien cubiertas por sus herramientas actuales.

**Supuestos relevantes**
- Los circuitos de aprobación dependen mucho del correo o mensajería informal.
- Un solo incidente o near miss alcanza para cambiar la percepción del riesgo.
- Brokers de seguros y MSSPs pueden funcionar como canal de entrada.

**Supuestos operacionales**
- El buyer no es IT puro, sino administración/finanzas o dirección.
- El problema se expresa con lenguaje operativo, no necesariamente con lenguaje de ciberseguridad.

### P003. Protección de onboarding y retail crypto contra pig butchering

**Supuestos críticos**
- Exchanges y wallets perciben como problema propio las pérdidas del usuario por manipulación externa.
- Existen casos frecuentes de usuarios inducidos a transferir fondos por vínculos de confianza o falsas promesas de inversión.
- Hay disposición institucional a invertir en prevención previa al retiro.

**Supuestos relevantes**
- Usuarios novatos muestran patrones distinguibles de mayor vulnerabilidad.
- Los equipos de soporte y compliance ya ven esta casuística de forma repetida.
- El problema afecta retención, reputación y soporte, no solo losses directos.

**Supuestos operacionales**
- El momento clave de validación es previo a la salida de fondos.
- Las plataformas aceptarían fricción adicional si reduce pérdidas y tickets complejos.

### P004. Protección antifraude para adultos mayores y familias cuidadoras

**Supuestos críticos**
- Los adultos mayores o sus familias reconocen este problema como suficientemente frecuente y doloroso.
- Las familias están dispuestas a involucrarse activamente en la prevención.
- Existe algún buyer viable más allá de la víctima individual.

**Supuestos relevantes**
- El adulto mayor no siempre identifica el engaño hasta después del daño.
- Familiares, bancos o cuidadores cumplen un rol real de contención o supervisión.
- El problema emerge espontáneamente en entrevistas sin necesidad de sugerirlo demasiado.

**Supuestos operacionales**
- El canal dominante será WhatsApp, llamadas o mensajes de urgencia.
- El lenguaje emocional será más importante que el lenguaje técnico en los relatos.

### P005. Educación adaptativa embebida para prevención de fraude financiero

**Supuestos críticos**
- La falta de educación financiera no es solo una causa estructural, sino un dolor percibido por compradores institucionales.
- Bancos, fintechs o exchanges pagarían específicamente por prevención educativa adaptativa.
- Los usuarios no solo necesitan información, sino acompañamiento contextual en la decisión.

**Supuestos relevantes**
- El buyer cree que la educación reduce fraude, churn o reclamos.
- Los usuarios reconocen retrospectivamente que les faltó comprensión del riesgo.
- La educación general actual es percibida como insuficiente.

**Supuestos operacionales**
- La validación debería capturar tanto buyer institucional como usuario final.
- El lenguaje “educativo” podría generar rechazo si no está ligado a una pérdida concreta.

---

## B2. Screener Design por problema

### P001. Banca y billeteras

**Perfil primario**  
Responsables de fraude, customer protection, operaciones o risk en bancos, billeteras y fintechs, o usuarios que hayan sufrido un episodio reciente de suplantación con impacto financiero.

**Perfil secundario**  
Analistas de soporte, familiares de víctimas, especialistas de reclamos o customer care con contacto frecuente con casos.

**Preguntas de screening**
1. ¿Trabajás o trabajaste en los últimos 12 meses en un banco, fintech o billetera digital?
2. ¿Tu rol involucra reclamos, fraude, operaciones o atención a incidentes?
3. ¿Viviste o acompañaste un caso de operación financiera afectada por llamada, SMS o WhatsApp sospechoso en el último año?
4. ¿Usás banca digital o billeteras activamente al menos una vez por semana?

**Mínimo de entrevistas válidas**  
5 primarias + 2 secundarias.

**Dónde encontrarlos**
- LinkedIn de bancos y fintechs.
- Contactos del ecosistema fintech.
- Comunidades de usuarios de billeteras.
- Redes cercanas con experiencia directa en fraude digital.

### P002. PyMEs y BEC

**Perfil primario**  
Dueños, CFOs, gerentes de administración/finanzas o responsables de tesorería de PyMEs de 10 a 200 empleados.

**Perfil secundario**  
Brokers de seguros cyber, estudios contables, MSSPs o personal administrativo que procese pagos.

**Preguntas de screening**
1. ¿Tu empresa realiza pagos a proveedores de manera digital al menos una vez por semana?
2. ¿Participás o supervisás aprobaciones de pago, transferencias o validación de cuentas bancarias?
3. ¿En los últimos 12 meses tu empresa tuvo un caso sospechoso de cambio de cuenta, instrucción urgente de pago o correo dudoso vinculado a dinero?
4. ¿Tu empresa tiene entre 10 y 200 empleados?

**Mínimo de entrevistas válidas**  
5 primarias + 2 secundarias.

**Dónde encontrarlos**
- Cámaras PyME.
- Estudios contables y administrativos.
- Brokers de seguros.
- Contactos empresariales de la red del equipo.

### P003. Crypto retail y pig butchering

**Perfil primario**  
Responsables de soporte, compliance, risk o trust & safety en exchanges, wallets o fintechs con cripto; alternativamente, usuarios novatos que hayan sufrido o rozado una falsa inversión.

**Perfil secundario**  
Analistas de comunidad, moderadores, educadores crypto o especialistas de blockchain intelligence.

**Preguntas de screening**
1. ¿Trabajás en una plataforma crypto o usaste una plataforma crypto por primera vez en los últimos 24 meses?
2. ¿Participaste en soporte, compliance o atención de casos vinculados a transferencias a terceros?
3. ¿Conocés o viviste un caso en el que una persona enviara fondos tras recibir consejos externos de inversión?
4. ¿Tu rol incluye contacto con usuarios o revisión de casos sospechosos?

**Mínimo de entrevistas válidas**  
5 primarias + 2 secundarias.

**Dónde encontrarlos**
- Comunidades Web3 y blockchain LATAM.
- Contactos en exchanges y fintechs cripto.
- Grupos de Discord/Telegram del ecosistema.

### P004. Adultos mayores y familias cuidadoras

**Perfil primario**  
Adultos mayores con uso activo de cuenta bancaria o billetera digital, o familiares/cuidadores directos que acompañen su gestión financiera.

**Perfil secundario**  
Personal bancario de atención, especialistas en inclusión financiera, trabajadores sociales o cuidadores formales.

**Preguntas de screening**
1. ¿Tenés más de 65 años con cuenta bancaria activa o acompañás a alguien de más de 65 con cuenta activa?
2. ¿Ese adulto mayor usa celular, WhatsApp o banca digital al menos ocasionalmente?
3. ¿En el último año vivió o estuvo cerca de una situación de llamado, mensaje o pedido de dinero sospechoso?
4. ¿Sos familiar directo, cuidador o acompañante frecuente en temas financieros?

**Mínimo de entrevistas válidas**  
5 primarias + 2 secundarias.

**Dónde encontrarlos**
- Red familiar y extendida.
- Centros de jubilados.
- Programas de inclusión digital.
- Personal bancario orientado a atención senior.

### P005. Educación adaptativa antifraude

**Perfil primario**  
Responsables de producto, customer success, compliance o educación financiera en fintechs, bancos y exchanges; usuarios con bajo nivel de alfabetización financiera que hayan tenido una mala experiencia de inversión o fraude.

**Perfil secundario**  
Educadores financieros, analistas de onboarding o agentes de soporte de productos complejos.

**Preguntas de screening**
1. ¿Tu rol involucra onboarding, educación al usuario, compliance o soporte en productos financieros?
2. ¿Tu organización ofrece actualmente contenidos o advertencias educativas al usuario?
3. ¿Conocés casos donde un usuario haya sufrido una pérdida por no entender bien el producto, el riesgo o una promesa de inversión?
4. ¿Usaste por primera vez un producto financiero digital complejo en los últimos 24 meses?

**Mínimo de entrevistas válidas**  
5 primarias + 2 secundarias.

**Dónde encontrarlos**
- Equipos de customer success y compliance.
- Comunidades de educación financiera.
- Usuarios novatos de fintechs y exchanges.

---

## B3. Discussion Guide base para entrevistas semiestructuradas

La siguiente guía funciona como protocolo general y debe adaptarse levemente a cada problema según perfil de entrevistado. La lógica es siempre la misma: abrir contexto, dejar emerger el problema, profundizar sobre un episodio y relevar workarounds sin introducir ninguna solución.

### Bloque 1. Calentamiento y contexto
- Contame cómo manejás tu plata o tus pagos en el día a día.
- ¿Qué herramientas o canales usás más seguido?
- ¿Qué cosas te resultan más cómodas y cuáles te generan más fricción?

### Bloque 2. Exploración del territorio del problema
- ¿En el último año tuviste alguna experiencia incómoda, rara o riesgosa relacionada con pagos, transferencias o pedidos de dinero?
- ¿Te pasó alguna situación donde algo parecía legítimo al principio y después no tanto?
- ¿Hay algo que hoy te haga desconfiar cuando aparece un pedido de plata, datos o aprobación?

### Bloque 3. Deep dive del episodio
- Contame exactamente qué pasó.
- ¿Cuándo empezó esa situación?
- ¿Cómo te contactaron o cómo apareció?
- ¿Qué fue lo primero que pensaste?
- ¿En qué momento te diste cuenta de que algo no cerraba?
- ¿Qué hiciste inmediatamente después?
- ¿A quién acudiste?
- ¿Hubo pérdida económica, de tiempo o emocional?
- ¿Qué cambió en tu comportamiento después de eso?

### Bloque 4. Soluciones actuales y workarounds
- ¿Qué hacés hoy para evitar que eso vuelva a pasar?
- ¿Tu empresa / tu familia / tu banco tiene alguna forma actual de prevenirlo?
- ¿Pagaste alguna vez por una herramienta, seguro, asesoramiento o soporte para sentirte más protegido?
- ¿Qué te sigue faltando hoy?

### Bloque 5. Cierre
- ¿Hay algo importante sobre este tema que no te pregunté?
- ¿Conocés a alguien que haya vivido algo parecido y que valga la pena entrevistar?

### Instrucciones al entrevistador
- No completar silencios demasiado rápido.
- No sugerir interpretaciones.
- No preguntar “¿usarías una app que…?” ni variantes.
- Siempre volver al comportamiento pasado: “¿qué pasó la última vez?”.

---

## B4. Observation Protocol y plantilla post-entrevista

Además de registrar lo dicho, el equipo debe observar:

- cambios de tono, incomodidad o emoción;
- si el problema surge espontáneamente o solo cuando se lo acerca mucho;
- si aparecen actores inesperados;
- si el workaround actual es sorprendentemente precario o sofisticado;
- si la persona minimiza al inicio y amplía luego con ejemplos.

### Plantilla de nota post-entrevista

```text
Fecha y duración:
Perfil del entrevistado (sin datos personales):
Surgió el problema espontáneamente (sí / no):
Episodio concreto relatado (sí / no):
Cita más relevante (parafraseada):
Comportamiento o reacción emocional notable:
Actor inesperado en el relato:
Workaround actual:
¿Pagaría por una solución? ¿Cuánto? ¿A quién?:
Supuesto confirmado / derribado:
Sorpresa o hallazgo no anticipado:
```

---

## B5. JTBD Canvas preliminar por problema

En esta instancia, el JTBD se presenta como hipótesis de trabajo preliminar que deberá ser ajustada tras las entrevistas.

### P001. Banca y billeteras
- **Situación:** recibe un mensaje o llamada que parece provenir de una entidad confiable.
- **Motivación:** proteger su dinero y resolver rápido una supuesta urgencia.
- **Fricción:** no distingue con claridad entre alerta legítima y manipulación.
- **Resultado esperado:** operar con tranquilidad sin correr riesgo de caer en engaños.
- **Ansiedades:** perder plata, bloquear la cuenta, cometer un error irreversible.
- **Fuerzas de cambio:** haber sufrido un incidente o conocer a alguien cercano que lo sufrió.

### P002. PyMEs y BEC
- **Situación:** debe autorizar o ejecutar un pago dentro de una operación aparentemente normal.
- **Motivación:** pagar rápido y mantener continuidad operativa sin trabar el negocio.
- **Fricción:** no tiene suficiente visibilidad para distinguir una instrucción legítima de una manipulada.
- **Resultado esperado:** poder confiar en que la transferencia aprobada es correcta.
- **Ansiedades:** perder dinero, quedar expuesto frente a dirección o proveedores, generar daño reputacional.
- **Fuerzas de cambio:** un near miss o incidente real que revele la fragilidad del procedimiento actual.

### P003. Crypto retail
- **Situación:** quiere invertir o transferir fondos siguiendo una recomendación externa que parece creíble.
- **Motivación:** capturar una oportunidad de ganancia y actuar antes de “llegar tarde”.
- **Fricción:** no tiene criterio suficiente para evaluar si la contraparte, la promesa o la wallet son riesgosas.
- **Resultado esperado:** invertir sin ser manipulado ni perder fondos por engaño.
- **Ansiedades:** perder ahorros, quedar expuesto o sentirse avergonzado por haber sido engañado.
- **Fuerzas de cambio:** experiencia negativa propia, advertencia institucional confiable o conocimiento de un caso cercano.

### P004. Adultos mayores
- **Situación:** recibe una llamada o mensaje que apela a urgencia, familiaridad o miedo.
- **Motivación:** ayudar rápido, proteger a un ser querido o resolver un problema percibido como grave.
- **Fricción:** la carga emocional reduce capacidad de chequeo y verificación.
- **Resultado esperado:** poder decidir con calma y sin quedar solo ante un evento sospechoso.
- **Ansiedades:** perjudicar a un familiar, perder autonomía, sufrir una pérdida patrimonial irreparable.
- **Fuerzas de cambio:** un episodio traumático propio o de alguien del entorno.

### P005. Educación adaptativa
- **Situación:** entra en contacto con un producto, promesa de inversión o señal financiera que no comprende del todo.
- **Motivación:** tomar una buena decisión financiera y evitar quedar afuera de una oportunidad.
- **Fricción:** carece de marco conceptual para distinguir riesgo razonable de engaño.
- **Resultado esperado:** entender lo suficiente como para no tomar decisiones peligrosas.
- **Ansiedades:** perder dinero, sentirse ingenuo o no saber en quién confiar.
- **Fuerzas de cambio:** haber cometido errores previos o recibir acompañamiento contextual en el momento de decisión.

---

## B6. Synthesis Template para después del fieldwork

Una vez que el equipo complete entrevistas y notas, la síntesis deberá producir para cada problema:

1. **Mapa de patrones**: temas que aparecen en 3 o más entrevistas.
2. **Mapa de contradicciones**: diferencia entre intención declarada y comportamiento real.
3. **Supuestos confirmados vs derribados**.
4. **Citas clave parafraseadas**.
5. **Hallazgos inesperados**.
6. **Veredicto de user research**: validado / parcialmente validado / inconcluso / invalidado.

Dado que ese fieldwork todavía no se realizó, el presente entregable no adjudica hallazgos empíricos inexistentes. Lo que sí deja resuelto es la estructura exacta para que el equipo pueda salir a campo sin improvisar.

---

# CONVERGENCIA. Validación cruzada

## Objetivo

La validación cruzada busca evitar dos errores frecuentes:

- sobredimensionar un problema porque tiene mucha prensa o mucha regulación, pero poco dolor real en actores concretos;
- sobredimensionar un problema porque una o dos entrevistas fueron intensas, aunque no exista señal secundaria suficiente de escala o WTP.

Por eso, el veredicto final debe surgir de la intersección entre Track A y Track B.

## Matriz de convergencia aplicada

| Desk Research | User Research | Veredicto final | Interpretación |
|---|---|---|---|
| Validado | Validado | VALIDADO (alta confianza) | El problema existe y hay señal de mercado. |
| Validado | Parcialmente validado | VALIDADO (media confianza) | El problema existe, pero su formulación puede requerir ajuste. |
| Parcialmente validado | Validado | VALIDADO (media confianza) | La evidencia secundaria es limitada, pero el fieldwork es contundente. |
| Validado | Invalidado | DIVERGENCIA | Revisar formulación, muestra o framing del problema. |
| Invalidado | Validado | DIVERGENCIA | Puede ser un mercado nuevo o mal documentado. |
| Invalidado | Invalidado | INVALIDADO | El problema no se sostiene en la forma actual. |
| Parcialmente validado | Inconcluso | INSUFICIENTE | Se requiere más fieldwork antes de seguir. |

## Estado actual de convergencia en este entregable

Como el Track B todavía no fue ejecutado, el estado actual de cada problema queda necesariamente abierto.

| Problem ID | Track A | Track B | Estado actual |
|---|---|---|---|
| P001 | Validado | No ejecutado | Pendiente de convergencia |
| P002 | Validado | No ejecutado | Pendiente de convergencia |
| P003 | Parcialmente validado | No ejecutado | Pendiente de convergencia |
| P004 | Parcialmente validado | No ejecutado | Pendiente de convergencia |
| P005 | Inconcluso | No ejecutado | Pendiente de convergencia |

---

# Ranking metodológico para salir a campo

Si el equipo necesita priorizar entrevistas por tiempo limitado, el orden recomendado para comenzar el user research es el siguiente:

1. **P002. Protección de PyMEs contra BEC y fraude de transferencias**  
   Es el problema con mejor señal secundaria y mayor claridad de buyer.

2. **P001. Prevención de phishing/smishing e ingeniería social en banca y billeteras**  
   Tiene alta relevancia sistémica y buena validación secundaria, pero necesita profundidad institucional y de usuario.

3. **P003. Pig butchering y falsas inversiones en crypto retail**  
   Muestra gran potencial, pero necesita confirmar mejor buyer, dolor organizacional y frecuencia regional.

4. **P004. Protección de adultos mayores y familias cuidadoras**  
   Tiene enorme fuerza social, pero requiere probar monetización y buyer real.

5. **P005. Educación adaptativa embebida**  
   Conviene dejarlo para más adelante, salvo que el fieldwork revele que el problema se formula mejor como una capa institucional de reducción de fraude y no como educación autónoma.

---

# Hallazgos transversales del dominio

Incluso antes del fieldwork, el análisis conjunto de Fase 1, Fase 2 y Track A permite extraer algunos hallazgos transversales importantes:

1. **La ingeniería social aparece como núcleo común** detrás de múltiples tipologías, incluso cuando el fraude se presenta como técnico o financiero.
2. **La víctima individual rara vez es el mejor comprador**, por lo que la tesis de startup debe centrarse en instituciones que ya absorben costo, riesgo o reputación.
3. **Los problemas más sólidos no son necesariamente los más conmovedores**, sino los que combinan dolor real con buyer accesible.
4. **La señal regulatoria y la señal de pérdida económica ya existen** en varias categorías; lo que falta en algunos casos es confirmar el canal de monetización.
5. **El user research no debe buscar validar una idea**, sino destruir supuestos falsos lo antes posible.

---

# Cierre metodológico

Este módulo complementario deja preparado el paso metodológico que sigue naturalmente al entregable original: convertir una lista prometedora de problemas en evidencia de validación más rigurosa, con separación clara entre investigación secundaria e investigación primaria.

En su estado actual, el documento permite sostener académicamente tres afirmaciones:

1. **Ya existe una base sólida de desk research para priorizar problemas**.
2. **Todavía no corresponde afirmar validación definitiva sin fieldwork**.
3. **El equipo ya dispone de un protocolo concreto para salir a validar sin improvisación y sin sesgo excesivo de confirmación**.

Dicho de otro modo, el entregable no solo identifica oportunidades: también organiza el camino correcto para demostrar, con mayor rigor, cuáles merecen convertirse en tesis y startup real.
