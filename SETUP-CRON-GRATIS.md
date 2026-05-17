# 🆓 Configuración de Cron Gratuito Exacto (Cada 12 Horas)

Este documento te guía para configurar ejecuciones exactas cada 12 horas de forma 100% gratuita.

---

## 🎯 Problema Actual

| Problema | Solución |
|----------|----------|
| GitHub Actions solo 100 min/mes (repo privado) | Usar repo público (2000 min) o trigger externo |
| Cron de GitHub impreciso (±15 min) | Usar servicio externo exacto |
| Necesitas gratis | Cron-job.org o EasyCron |

---

## ✅ OPCIÓN 1: Hacer Repo Público (MÁS SIMPLE)

### Ventajas:
- ✅ 2000 minutos/mes gratis
- ✅ ~40 ejecuciones/mes (50 min cada una)
- ✅ Suficiente para 2 veces al día durante 20 días
- ✅ No requiere configuración adicional

### Cómo hacerlo:
1. Ve a: https://github.com/Ooenygmaoo/serenitybdd/settings
2. Baja hasta **"Danger Zone"**
3. Click en **"Change visibility"**
4. Selecciona **"Make public"**
5. Confirma escribiendo el nombre del repositorio

⚠️ **Nota**: Tu código será visible públicamente, pero tus secrets (credenciales) permanecen privados y seguros.

---

## ✅ OPCIÓN 2: Cron-job.org + GitHub Webhook (100% GRATIS)

Esta opción ejecuta el workflow exactamente cada 12 horas sin consumir minutos esperando.

### Paso 1: Crear Personal Access Token en GitHub

1. Ve a: https://github.com/settings/tokens/new
2. **Nombre**: `Cron Job Trigger`
3. **Expiration**: `No expiration` (o 1 año)
4. **Scopes**: Marca solo:
   - ✅ `repo` (Full control of private repositories)
5. Click en **"Generate token"**
6. **⚠️ IMPORTANTE**: Copia el token ahora, no lo volverás a ver

```
Ejemplo de token: ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

---

### Paso 2: Registrarse en Cron-job.org

1. Ve a: https://cron-job.org/en/signup/
2. Crea una cuenta gratuita:
   - Email
   - Password
   - Confirma email

**Límites Free**:
- ✅ Hasta 50 cron jobs
- ✅ Intervalo mínimo: 1 minuto
- ✅ 100% gratis para siempre

---

### Paso 3: Crear el Cron Job

1. Inicia sesión en: https://cron-job.org/en/
2. Click en **"Create cronjob"**
3. Llena el formulario:

#### 🔧 Configuración del Job:

| Campo | Valor |
|-------|-------|
| **Title** | `BrowserStack Tests - Every 12 Hours` |
| **Address (URL)** | `https://api.github.com/repos/Ooenygmaoo/serenitybdd/dispatches` |
| **Schedule** | ⏰ Ver sección siguiente |

#### ⏰ Schedule (Cada 12 horas):

Opción A: **9 AM y 9 PM Colombia** (14:00 y 02:00 UTC)
```
Execution Type: Twice a day
At times: 14:00 and 02:00 (UTC)
```

Opción B: **Cada 12 horas exactas**
```
Execution Type: Every 12 hours
Starting at: 14:00 (UTC)
```

#### 🔐 Request Settings:

```
Request Method: POST

Headers:
Accept: application/vnd.github.v3+json
Authorization: Bearer TU_TOKEN_AQUI
Content-Type: application/json

Body (JSON):
{
  "event_type": "run-browserstack-tests"
}
```

**⚠️ Reemplaza `TU_TOKEN_AQUI`** con el token que generaste en el Paso 1.

4. Click en **"Create cronjob"**
5. Click en **"Enable"** para activarlo

---

### Paso 4: Verificar que Funciona

#### Test Manual:
1. En cron-job.org, encuentra tu job
2. Click en **"Run now"**
3. Ve a GitHub Actions: https://github.com/Ooenygmaoo/serenitybdd/actions
4. Deberías ver una nueva ejecución iniciándose

#### Verificar Ejecuciones:
- En cron-job.org puedes ver el historial de ejecuciones
- En GitHub Actions verás cada ejecución disparada

---

## ✅ OPCIÓN 3: EasyCron (Alternativa)

Si cron-job.org no funciona, usa EasyCron:

1. **URL**: https://www.easycron.com/
2. **Free Tier**: 1 cron job gratis
3. **Configuración**: Similar a cron-job.org

---

## ✅ OPCIÓN 4: Render.com Cron Jobs (Gratis)

Render.com ofrece cron jobs gratuitos:

### Crear `cron.sh` en tu proyecto:

```bash
#!/bin/bash
curl -X POST \
  https://api.github.com/repos/Ooenygmaoo/serenitybdd/dispatches \
  -H "Accept: application/vnd.github.v3+json" \
  -H "Authorization: Bearer TU_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"event_type":"run-browserstack-tests"}'
```

### En Render.com:
1. Crea un "Cron Job"
2. Conecta tu repo
3. Configura el schedule: `0 2,14 * * *`
4. Command: `bash cron.sh`

---

## 📊 Comparación de Opciones

| Opción | Complejidad | Precisión | Costo | Minutos GitHub |
|--------|------------|-----------|-------|----------------|
| **Repo Público** | 🟢 Simple | ±15 min | 🆓 | 2000/mes |
| **Cron-job.org** | 🟡 Media | ⚡ Exacto | 🆓 | Solo ejecución |
| **EasyCron** | 🟡 Media | ⚡ Exacto | 🆓 | Solo ejecución |
| **Render.com** | 🔴 Alta | ⚡ Exacto | 🆓 | Solo ejecución |

---

## 🎯 Mi Recomendación

### Para tu caso:

**SI tu código puede ser público** → **Opción 1 (Repo Público)**
- Más simple
- Suficientes minutos
- Cero configuración adicional

**SI debe ser privado** → **Opción 2 (Cron-job.org)**
- Exacto (sin variación)
- 100% gratis
- Fácil de configurar
- No consume minutos esperando

---

## 🔍 Solución de Problemas

### Error: "Bad credentials" en cron-job.org
**Causa**: Token incorrecto o sin permisos  
**Solución**: Regenera el token con scope `repo`

### Error: "Not Found" 
**Causa**: URL del repo incorrecta  
**Solución**: Verifica la URL: `https://api.github.com/repos/OWNER/REPO/dispatches`

### El workflow no se dispara
**Causa**: El evento `repository_dispatch` no está configurado  
**Solución**: Asegúrate de usar el workflow `BrowserStack_Scheduled.yml` nuevo

### Cron-job.org marca error 4xx/5xx
**Causa**: Servidor de GitHub puede estar lento  
**Solución**: Configura reintentos en cron-job.org settings

---

## 📞 Enlaces Útiles

| Recurso | URL |
|---------|-----|
| **Cron-job.org** | https://cron-job.org/ |
| **GitHub Tokens** | https://github.com/settings/tokens |
| **GitHub Actions** | https://github.com/Ooenygmaoo/serenitybdd/actions |
| **Cron Expression Helper** | https://crontab.guru/ |

---

## ✅ Checklist de Configuración

- [ ] Decidir: ¿Repo público o privado?
- [ ] Si privado: Crear Personal Access Token en GitHub
- [ ] Registrarse en cron-job.org
- [ ] Crear cron job con URL y token
- [ ] Configurar schedule (cada 12 horas)
- [ ] Test manual en cron-job.org
- [ ] Verificar ejecución en GitHub Actions
- [ ] Confirmar que BrowserStack muestra resultados
- [ ] Verificar que se ejecuta a la hora exacta

---

**Última actualización**: Mayo 17, 2026  
**Workflow a usar**: `BrowserStack_Scheduled.yml`  
**Trigger type**: `repository_dispatch` con event `run-browserstack-tests`

