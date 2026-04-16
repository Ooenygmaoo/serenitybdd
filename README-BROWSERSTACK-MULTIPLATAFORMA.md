# 🚀 BrowserStack - Configuración Multi-Plataforma (Una Cuenta)

## 📋 Resumen
Este proyecto ejecuta **2 tests diferentes** en **2 plataformas diferentes** usando **UNA SOLA cuenta de BrowserStack**:

- **Test 1**: Usuario David (slash17) → **Windows 10 + Chrome Latest**
- **Test 2**: Usuario Enzo (enzo) → **Mac OS Monterey + Safari 15.6**

---

## 🔑 Configurar Credenciales

### En GitHub (para ejecución automática):
1. Ve a `Settings` → `Secrets and variables` → `Actions`
2. Crea estos 2 secrets:
   - **BROWSERSTACK_USER**: Tu username de BrowserStack
   - **BROWSERSTACK_ACCESS_KEY**: Tu access key de BrowserStack

### En Local (para pruebas):
```bash
export BROWSERSTACK_USER="tu_usuario"
export BROWSERSTACK_KEY="tu_access_key"
```

Para hacerlo permanente:
```bash
echo 'export BROWSERSTACK_USER="tu_usuario"' >> ~/.zshrc
echo 'export BROWSERSTACK_KEY="tu_access_key"' >> ~/.zshrc
source ~/.zshrc
```

---

## 🚀 Ejecución

### Localmente:

#### Ejecutar solo Windows (David):
```bash
./ejecutar-browserstack.sh windows
```

#### Ejecutar solo Mac (Enzo):
```bash
./ejecutar-browserstack.sh mac
```

#### Ejecutar ambas plataformas:
```bash
./ejecutar-browserstack.sh all
```

### En GitHub Actions:

#### Automático:
- Se ejecuta **cada 12 horas** (11 AM y 11 PM Colombia)
- Ejecuta ambos tests automáticamente (Windows y Mac)

#### Manual:
1. Ve a `Actions` en tu repositorio
2. Selecciona `BrowserStack Tests - Windows & Mac`
3. Click en `Run workflow`

---

## 📊 Tests Ejecutados

| Usuario | Login | Password | Plataforma | Browser |
|---------|-------|----------|------------|---------|
| David | slash17 | nirvana16 | Windows 10 | Chrome Latest |
| Enzo | enzo | alejandro1991 | Mac OS Monterey | Safari 15.6 |

---

## 🎯 Flujo de Prueba

Ambos tests ejecutan el mismo flujo:
1. Abrir https://webx.muthematrix.com/
2. Click en "Log In"
3. Ingresar credenciales (diferentes por usuario)
4. Ir a Panel de Usuario
5. Votar por el servidor

---

## 📁 Archivos Clave

```
serenitybdd/
├── .github/workflows/
│   └── Test_Execution.yml              # Workflow con 2 jobs (Windows + Mac)
│
├── src/test/
│   ├── java/hooks/
│   │   └── Hooks.java                  # Configura plataforma según tag
│   ├── resources/
│   │   ├── features/
│   │   │   └── voteMuthematrix.feature # 2 scenarios con tags @windows y @mac
│   │   └── serenity.conf               # Configuración BrowserStack dinámica
│
└── ejecutar-browserstack.sh            # Script para ejecución local
```

---

## 🔧 Cómo Funciona

### Tags de Cucumber:
- `@windows` → Ejecuta test de David en Windows 10 Chrome
- `@mac` → Ejecuta test de Enzo en Mac OS Safari

### Hook Dinámico:
El archivo `Hooks.java` detecta el tag del scenario y configura automáticamente:
- OS y versión
- Browser y versión
- Session name

### Workflow de GitHub:
Ejecuta 2 jobs secuenciales:
1. `test-windows` → Tag @windows
2. `test-mac` → Tag @mac (ejecuta después de Windows)

---

## 📊 Reportes

### BrowserStack Dashboard:
https://automate.browserstack.com/dashboard

Verás 2 sesiones:
- Vote Test - Windows Chrome - David votes for the server on Windows
- Vote Test - Mac Safari - Enzo votes for the server on Mac

### Serenity Reports:
Después de ejecutar, abre:
```bash
open target/site/serenity/index.html
```

En GitHub Actions, los reportes se suben como artifacts:
- `serenity-windows-report-XXX`
- `serenity-mac-report-XXX`

---

## 💡 Ventajas de Esta Configuración

✅ **Una sola cuenta** → Menos costos  
✅ **Ejecución secuencial** → No consume paralelismo extra  
✅ **Usuarios diferentes** → Prueba con datos reales distintos  
✅ **Plataformas diferentes** → Cobertura cross-browser  
✅ **Configuración dinámica** → Fácil agregar más plataformas  

---

## 🔄 Agregar Más Plataformas

Para agregar una nueva plataforma (ejemplo: Windows 11 Edge):

### 1. Actualizar feature:
```gherkin
@edge
Scenario: Maria votes for the server on Edge
  Given Maria enter to the muthematrix page
  When Maria enter to the login page and click on vote with username "maria" and password "pass123"
  Then Maria Could be vote for the server
```

### 2. Actualizar Hooks.java:
```java
else if (scenario.getSourceTagNames().contains("@edge")) {
    System.setProperty("bstack.os", "Windows");
    System.setProperty("bstack.osVersion", "11");
    System.setProperty("bstack.browserName", "Edge");
    System.setProperty("bstack.browserVersion", "latest");
}
```

### 3. Actualizar workflow:
Agregar un nuevo job `test-edge` similar a los existentes.

---

## 🆘 Troubleshooting

### Error: "Could not instantiate RemoteWebDriver"
- Verifica que las credenciales estén configuradas correctamente
- Verifica que tu cuenta tenga minutos disponibles

### Tests no aparecen en BrowserStack
- Verifica que `webdriver.driver = remote` esté configurado
- Verifica que el URL remoto sea correcto

### Safari no funciona en Mac
- Safari tiene limitaciones con algunos permisos
- Si falla, cambia a Chrome en Mac:
  ```java
  System.setProperty("bstack.browserName", "Chrome");
  ```

---

## 📞 Enlaces Útiles

- 🌐 [BrowserStack Dashboard](https://automate.browserstack.com/dashboard)
- 📖 [BrowserStack Capabilities](https://www.browserstack.com/automate/capabilities)
- 🔧 [Configuración de plataformas](https://www.browserstack.com/list-of-browsers-and-platforms)

---

**Última actualización**: Abril 2026  
**Configuración**: Una cuenta, múltiples plataformas, usuarios dinámicos

