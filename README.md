# 🚀 Serenity BDD - BrowserStack Automation Tests

Proyecto de automatización de pruebas usando Serenity BDD, Cucumber y BrowserStack para ejecución en múltiples plataformas.

---

## 📋 Descripción

Este proyecto ejecuta pruebas automatizadas de votación en la aplicación Muthematrix usando:
- **Serenity BDD** para reporting avanzado
- **Cucumber** para definición de escenarios BDD
- **Screenplay Pattern** para código mantenible
- **BrowserStack** para ejecución en la nube en múltiples plataformas

### 🎯 Tests Implementados

1. **Test Windows**: Usuario David vota desde Windows 10 + Chrome
2. **Test Mac**: Usuario Enzo vota desde Mac OS Monterey + Safari 15.6

---

## 🔧 Configuración Inicial

### 1. Requisitos Previos

- Java 17
- Maven 3.6+
- Cuenta de BrowserStack activa

### 2. Configurar Secrets en GitHub

Los tests en GitHub Actions requieren estos secrets configurados:

1. Ve a: `https://github.com/Ooenygmaoo/serenitybdd/settings/secrets/actions`

2. Crea los siguientes secrets:

| Nombre del Secret | Valor | Descripción |
|-------------------|-------|-------------|
| `BROWSERSTACK_USER` | Tu username de BrowserStack | Usuario para autenticación |
| `BROWSERSTACK_ACCESS_KEY` | Tu access key de BrowserStack | Clave de acceso API |

#### Cómo obtener tus credenciales:
1. Inicia sesión en https://www.browserstack.com/
2. Ve a tu dashboard
3. Copia tu **Username** y **Access Key**

---

## 🏃 Ejecución Local

### Configurar credenciales localmente:

```bash
export BROWSERSTACK_USER="tu_username"
export BROWSERSTACK_KEY="tu_access_key"
```

### Ejecutar tests:

```bash
# Test de Windows
mvn clean verify \
  -Denvironment=browserstack \
  -DBROWSERSTACK_USER="${BROWSERSTACK_USER}" \
  -DBROWSERSTACK_KEY="${BROWSERSTACK_KEY}" \
  -DBROWSERSTACK_URL="https://${BROWSERSTACK_USER}:${BROWSERSTACK_KEY}@hub-cloud.browserstack.com/wd/hub" \
  -Dcucumber.filter.tags="@windows"

# Test de Mac
mvn clean verify \
  -Denvironment=browserstack \
  -DBROWSERSTACK_USER="${BROWSERSTACK_USER}" \
  -DBROWSERSTACK_KEY="${BROWSERSTACK_KEY}" \
  -DBROWSERSTACK_URL="https://${BROWSERSTACK_USER}:${BROWSERSTACK_KEY}@hub-cloud.browserstack.com/wd/hub" \
  -Dcucumber.filter.tags="@mac"

# Todos los tests
mvn clean verify \
  -Denvironment=browserstack \
  -DBROWSERSTACK_USER="${BROWSERSTACK_USER}" \
  -DBROWSERSTACK_KEY="${BROWSERSTACK_KEY}" \
  -DBROWSERSTACK_URL="https://${BROWSERSTACK_USER}:${BROWSERSTACK_KEY}@hub-cloud.browserstack.com/wd/hub" \
  -Dcucumber.filter.tags="@webMuthematrix"
```

### Generar reporte Serenity:

```bash
mvn serenity:aggregate
```

El reporte se generará en: `target/site/serenity/index.html`

---

## 🤖 Ejecución en GitHub Actions

### ⏰ Ejecución Automática (Programada)

Los tests se ejecutan automáticamente **2 veces al día**:

| Horario | Zona Horaria | UTC | Descripción |
|---------|--------------|-----|-------------|
| **9:00 AM** | Colombia (UTC-5) | 14:00 UTC | Ejecución matutina |
| **9:00 PM** | Colombia (UTC-5) | 02:00 UTC | Ejecución nocturna |

**Configuración en `.github/workflows/Test_Execution.yml`:**
```yaml
schedule:
  - cron: '0 14 * * *'   # 9 AM Colombia
  - cron: '0 2 * * *'    # 9 PM Colombia
```

### 🚀 Ejecución Manual

1. Ve a tu repositorio en GitHub: [Actions Tab](../../actions)
2. Click en **"BrowserStack Tests - Windows & Mac (9 AM & 9 PM Colombia)"**
3. Click en **"Run workflow"** (botón verde en la derecha)
4. Selecciona la rama: `master` o `staging`
5. Click en **"Run workflow"** para confirmar

### 📊 Ver Resultados

#### GitHub Actions:
- **Dashboard**: [Ver todas las ejecuciones](../../actions)
- **Reportes**: Los reportes Serenity están en "Artifacts" de cada ejecución
- **Logs**: Logs detallados de cada job disponibles en la ejecución
- **Retención**: Artifacts guardados por 30 días

#### BrowserStack Dashboard:
- **URL**: https://automate.browserstack.com/dashboard
- **Videos**: Video completo de cada test
- **Screenshots**: Screenshots automáticos de cada paso
- **Logs**: Console logs y Network logs
- **Ejecuciones**: Busca por "Muthematrix Voting Tests" o "browserstack-multi-platform"

### 🔍 Cómo encontrar tus tests en BrowserStack

1. Inicia sesión en: https://automate.browserstack.com
2. Ve a la sección "Automate"
3. Busca por:
   - **Project Name**: "Muthematrix Voting Tests"
   - **Build Name**: "browserstack-multi-platform"
   - **Session Name**: "Muthematrix Vote Test"
4. Click en cualquier sesión para ver video y detalles

---

## 🖥️ Plataformas de Testing

### Job 1: test-windows (David)
| Atributo | Valor |
|----------|-------|
| **OS** | Windows 10 |
| **Navegador** | Chrome (latest) |
| **Resolución** | 1920x1080 |
| **Usuario de prueba** | slash17 |
| **Password** | nirvana16 |
| **Tag Cucumber** | `@windows` |
| **Tiempo estimado** | ~5 minutos |
| **Geolocalización** | Colombia (America/Bogota) |

### Job 2: test-mac (Enzo)
| Atributo | Valor |
|----------|-------|
| **OS** | macOS Monterey |
| **Navegador** | Safari 15.6 |
| **Resolución** | 1920x1080 |
| **Usuario de prueba** | enzo |
| **Password** | alejandro1991 |
| **Tag Cucumber** | `@mac` |
| **Tiempo estimado** | ~5 minutos |
| **Ejecución** | Secuencial (después de Windows) |
| **Geolocalización** | Colombia (America/Bogota) |

### 🔄 Orden de Ejecución

```
1. test-windows    →  Job Windows ejecuta primero
2. test-mac        →  Job Mac ejecuta después (ahorra paralelismo)
3. publish-summary →  Publica resumen consolidado
```

**Nota**: Los jobs se ejecutan secuencialmente para optimizar el uso de paralelismo en BrowserStack.

---

## 📂 Estructura del Proyecto

```
serenitybdd/
├── .github/
│   └── workflows/
│       └── Test_Execution.yml          # Workflow de GitHub Actions
├── src/
│   ├── main/java/                      # Código fuente (si aplica)
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java          # Hooks de Cucumber
│       │   └── starter/
│       │       ├── stepdefinitions/
│       │       │   └── VoteMuthematrixStepDefinitions.java
│       │       └── CucumberTestSuite.java
│       └── resources/
│           ├── features/
│           │   └── voteMuthematrix.feature    # Escenarios de prueba
│           ├── serenity.conf                  # Configuración Serenity
│           └── browserstack.conf              # Configuración BrowserStack
├── target/
│   └── site/serenity/                  # Reportes generados
├── pom.xml                             # Configuración Maven
└── README.md                           # Este archivo
```

---

## 🔍 Solución de Problemas

### ❌ Error: "Could not resolve substitution to a value: ${BROWSERSTACK_USER}"

**Síntoma**: 
```
Failed to read the serenity.conf file: Could not resolve substitution to a value: ${BROWSERSTACK_USER}
```

**Causa**: Los secrets no están configurados en GitHub Actions o las variables no se están pasando correctamente.

**Solución**:
1. Verifica que los secrets existan en: `Settings` → `Secrets and variables` → `Actions`
2. Verifica que el workflow pase las variables con `-D`:
   ```yaml
   -DBROWSERSTACK_USER="${BROWSERSTACK_USER}"
   -DBROWSERSTACK_KEY="${BROWSERSTACK_KEY}"
   -DBROWSERSTACK_URL="${BROWSERSTACK_URL}"
   ```
3. Asegúrate de que el archivo `serenity.conf` use `${?VARIABLE}` para variables opcionales

---

### ❌ Error: "A webdriver.remote.url property must be defined"

**Síntoma**:
```
RemoteDriverConfigurationError: A webdriver.remote.url property must be defined when using a Remote driver
```

**Causa**: La URL de BrowserStack no se está construyendo o pasando correctamente a Maven/Serenity.

**Solución**:
1. Verifica que la URL se construya en el workflow:
   ```bash
   BROWSERSTACK_URL="https://${BROWSERSTACK_USER}:${BROWSERSTACK_KEY}@hub-cloud.browserstack.com/wd/hub"
   ```
2. Verifica que se pase a Maven:
   ```bash
   mvn verify -DBROWSERSTACK_URL="${BROWSERSTACK_URL}"
   ```
3. Verifica que `serenity.conf` tenga:
   ```hocon
   webdriver.remote.url = ${?BROWSERSTACK_URL}
   ```

---

### ❌ Error: "Invalid credentials" desde BrowserStack

**Síntoma**: Error 401 o "Unauthorized" al ejecutar tests.

**Causa**: Las credenciales son incorrectas o han expirado.

**Solución**:
1. Verifica las credenciales actuales en: https://www.browserstack.com/accounts/settings
2. Actualiza los secrets en GitHub con las nuevas credenciales:
   - Username: `davidperez_Nt3dje`
   - Access Key: `yfXiDMmusrqyQZV3zyx8`
3. Vuelve a ejecutar el workflow

---

### ❌ Los tests no aparecen en BrowserStack Dashboard

**Síntomas posibles**:
- Dashboard vacío
- No se ven builds o sesiones
- Tests ejecutan pero no se registran

**Causa**: La ejecución falló antes de conectarse a BrowserStack.

**Solución**:
1. Revisa los logs en GitHub Actions para ver el error específico
2. Verifica que el test llegue hasta el step de `OpenUrl`
3. Busca en BrowserStack por "Project Name" o "Build Name":
   - Project: "Muthematrix Voting Tests"
   - Build: "browserstack-multi-platform"

---

### ❌ Error: "Session not created" o timeout

**Síntoma**: Tests fallan con timeout al crear sesión en BrowserStack.

**Causa**: Capacidad de BrowserStack agotada o plan con límites.

**Solución**:
1. Verifica tu plan en: https://www.browserstack.com/accounts/subscriptions
2. Espera unos minutos y vuelve a ejecutar
3. Ejecuta los tests secuencialmente (ya configurado en el workflow)

---

### 🔧 Comandos de Diagnóstico

```bash
# Verificar conexión a BrowserStack
curl -u "davidperez_Nt3dje:yfXiDMmusrqyQZV3zyx8" \
  https://api.browserstack.com/automate/plan.json

# Ver configuración de Serenity
cat src/test/resources/serenity.conf | grep -A 30 "browserstack"

# Ver logs detallados de Maven
mvn clean verify -X -Denvironment=browserstack

# Verificar que Java esté usando las variables
mvn help:system | grep BROWSERSTACK
```

---

## 🛠️ Tecnologías Utilizadas

- **Serenity BDD** 4.2.1 - Framework de testing y reporting
- **Cucumber** 7.x - BDD y Gherkin
- **Selenium** 4.x - Automatización web
- **Maven** - Gestión de dependencias
- **Java** 17 - Lenguaje de programación
- **BrowserStack** - Ejecución en la nube
- **GitHub Actions** - CI/CD

---

## 📊 Reportes

### Reporte Serenity
Los reportes de Serenity incluyen:
- ✅ Resultados de cada escenario
- ✅ Screenshots de cada paso
- ✅ Tiempos de ejecución
- ✅ Estadísticas y gráficos
- ✅ Logs detallados

### BrowserStack
Los reportes de BrowserStack incluyen:
- ✅ Video completo de la ejecución
- ✅ Screenshots automáticos
- ✅ Logs del navegador
- ✅ Logs de red
- ✅ Información del dispositivo

---

## 📝 Configuración del Workflow

El workflow de GitHub Actions está configurado para:

1. ✅ **Ejecutarse automáticamente** a las 9 AM y 9 PM Colombia
2. ✅ **Usar secrets** de GitHub para credenciales
3. ✅ **Ejecutar 2 jobs** secuencialmente (Windows primero, luego Mac)
4. ✅ **Generar reportes** Serenity para cada plataforma
5. ✅ **Guardar artifacts** con los reportes por 30 días
6. ✅ **Permitir ejecución manual** con `workflow_dispatch`

---

## 🔐 Seguridad

- ✅ Las credenciales están almacenadas como **GitHub Secrets**
- ✅ Los secrets están **encriptados** en GitHub
- ✅ Los valores **no se muestran** en los logs (aparecen como `***`)
- ✅ Solo usuarios con permisos pueden ver/modificar secrets
- ⚠️ **NUNCA** subas credenciales en el código fuente

---

## 🛠️ Tecnologías y Dependencias

| Tecnología | Versión | Uso |
|------------|---------|-----|
| **Java** | 17 | Lenguaje base |
| **Maven** | 3.6+ | Gestión de dependencias |
| **Serenity BDD** | 4.2.1 | Framework de testing y reporting |
| **Cucumber** | 7.x | BDD y Gherkin |
| **Selenium** | 4.23.1 | Automatización web |
| **JUnit** | 5.x (Vintage) | Test runner |
| **BrowserStack** | Cloud | Ejecución en múltiples plataformas |
| **GitHub Actions** | - | CI/CD automatizado |

---

## 📊 Información de Reportes

### Reporte Serenity BDD
Los reportes de Serenity incluyen:
- ✅ **Resultados por escenario** con estado (Pass/Fail)
- ✅ **Screenshots** de cada paso ejecutado
- ✅ **Tiempos de ejecución** detallados
- ✅ **Estadísticas y gráficos** interactivos
- ✅ **Logs detallados** de cada acción
- ✅ **Métricas de cobertura** de features

**Ubicación**: `target/site/serenity/index.html`

### BrowserStack Dashboard
Los reportes de BrowserStack incluyen:
- ✅ **Video completo** de la ejecución (HD)
- ✅ **Screenshots automáticos** de cada interacción
- ✅ **Console logs** del navegador
- ✅ **Network logs** (requests/responses)
- ✅ **Información del dispositivo** y capacidades
- ✅ **Stacktrace** de errores (si aplica)

**URL**: https://automate.browserstack.com/dashboard

---

## 🔐 Configuración de Seguridad

### Secrets de GitHub (Ya configurados ✅)

Los siguientes secrets ya están configurados en tu repositorio:

| Secret Name | Valor | Ubicación |
|-------------|-------|-----------|
| `BROWSERSTACK_USER` | `davidperez_Nt3dje` | GitHub Settings → Secrets |
| `BROWSERSTACK_ACCESS_KEY` | `yfXiDMmusrqyQZV3zyx8` | GitHub Settings → Secrets |

### Verificación de Secrets

Para verificar que los secrets funcionan correctamente:

1. Ve a: **Settings** → **Secrets and variables** → **Actions**
2. Deberías ver los 2 secrets con el ícono de candado 🔒
3. Los valores están encriptados y no se mostrarán en los logs

### Buenas Prácticas de Seguridad

- ✅ **Nunca** incluyas credenciales directamente en el código
- ✅ Los secrets están encriptados por GitHub
- ✅ Los valores aparecen como `***` en los logs
- ✅ Solo usuarios con permisos pueden ver/modificar secrets
- ⚠️ No compartas tu Access Key públicamente

---

## 📞 Enlaces Importantes

| Recurso | URL |
|---------|-----|
| **GitHub Actions** | [Ver Ejecuciones](../../actions) |
| **BrowserStack Dashboard** | https://automate.browserstack.com/dashboard |
| **BrowserStack Account** | https://www.browserstack.com/accounts/settings |
| **Serenity BDD Docs** | https://serenity-bdd.github.io/docs/guide/user_guide_intro |
| **Cucumber Docs** | https://cucumber.io/docs/cucumber/ |

---

## 📄 Licencia

Este proyecto está bajo la licencia especificada en el archivo `LICENSE`.

---

## 🎯 Estado del Proyecto

| Estado | Componente |
|--------|-----------|
| ✅ | Configuración BrowserStack completa |
| ✅ | Tests funcionando en BrowserStack |
| ✅ | GitHub Actions configurado y verificado |
| ✅ | Secrets configurados correctamente |
| ✅ | Ejecución automática programada (9 AM y 9 PM Colombia) |
| ✅ | 2 plataformas soportadas (Windows 10 Chrome + Mac Monterey Safari) |
| ✅ | Geolocalización Colombia configurada |
| ✅ | Reportes Serenity + BrowserStack funcionando |

---

## 🚀 Próximos Pasos Recomendados

1. **Ejecutar test manual** para verificar la nueva cuenta:
   ```bash
   # Push a staging para probar
   git checkout staging
   git push origin staging
   ```

2. **Verificar ejecución** en GitHub Actions

3. **Revisar resultados** en BrowserStack Dashboard

4. **Si todo funciona**, merge a master:
   ```bash
   git checkout master
   git merge staging
   git push origin master
   ```

---

**Última actualización**: Mayo 17, 2026  
**Credenciales BrowserStack**: Actualizadas ✅  
**Username actual**: `davidperez_Nt3dje`

