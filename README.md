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

### Ejecución Automática (Programada)

Los tests se ejecutan automáticamente:
- **9:00 AM** Colombia (14:00 UTC) - Todos los días
- **9:00 PM** Colombia (02:00 UTC) - Todos los días

### Ejecución Manual

1. Ve a: https://github.com/Ooenygmaoo/serenitybdd/actions
2. Click en **"BrowserStack Tests - Windows & Mac (9 AM & 9 PM Colombia)"**
3. Click en **"Run workflow"**
4. Selecciona branch: `master`
5. Click en **"Run workflow"**

### Ver Resultados

**GitHub Actions**:
- URL: https://github.com/Ooenygmaoo/serenitybdd/actions
- Los reportes Serenity están disponibles en "Artifacts" de cada ejecución

**BrowserStack Dashboard**:
- URL: https://automate.browserstack.com/dashboard
- Verás videos, logs y screenshots de cada ejecución

---

## 🖥️ Plataformas de Testing

### Job 1: Windows (David)
- **Sistema**: Windows 10
- **Navegador**: Chrome (última versión)
- **Usuario de prueba**: slash17 / nirvana16
- **Tag Cucumber**: `@windows`
- **Tiempo estimado**: ~5 minutos

### Job 2: Mac (Enzo)
- **Sistema**: Mac OS Monterey
- **Navegador**: Safari 15.6
- **Usuario de prueba**: enzo / alejandro1991
- **Tag Cucumber**: `@mac`
- **Tiempo estimado**: ~5 minutos
- **Ejecución**: Secuencial después de Windows

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

### Error: "Could not resolve substitution to a value: ${BROWSERSTACK_USER}"
**Causa**: Los secrets no están configurados en GitHub  
**Solución**: Crear los secrets `BROWSERSTACK_USER` y `BROWSERSTACK_ACCESS_KEY` en GitHub

### Error: "A webdriver.remote.url property must be defined"
**Causa**: La URL de BrowserStack no se está pasando correctamente  
**Solución**: Verificar que `BROWSERSTACK_URL` se construya y pase a Maven con `-DBROWSERSTACK_URL`

### Error: "Invalid credentials" desde BrowserStack
**Causa**: Las credenciales son incorrectas  
**Solución**: Verificar que los valores de los secrets sean correctos

### Los tests no aparecen en BrowserStack Dashboard
**Causa**: La ejecución falló antes de conectarse a BrowserStack  
**Solución**: Revisar los logs en GitHub Actions para ver el error específico

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

## 📞 Información de Contacto y Soporte

- **Repositorio**: https://github.com/Ooenygmaoo/serenitybdd
- **GitHub Actions**: https://github.com/Ooenygmaoo/serenitybdd/actions
- **BrowserStack Dashboard**: https://automate.browserstack.com/dashboard

---

## 📄 Licencia

Este proyecto está bajo la licencia especificada en el archivo `LICENSE`.

---

## 🎯 Estado del Proyecto

✅ **Configuración completa**  
✅ **Tests funcionando en BrowserStack**  
✅ **GitHub Actions configurado**  
✅ **Ejecución automática programada**  
✅ **2 plataformas soportadas** (Windows + Mac)

**Última actualización**: Abril 16, 2026

