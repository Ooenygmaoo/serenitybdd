# 🔐 GUÍA: Cómo Crear Secrets en GitHub

## 📍 Ubicación
`Tu Repositorio` → `Settings` → `Secrets and variables` → `Actions`

---

## 🎯 Paso a Paso

### 1️⃣ Acceder a Secrets
1. Ve a tu repositorio en GitHub
2. Haz clic en **Settings** (configuración)
3. En el menú lateral izquierdo, busca **Secrets and variables**
4. Haz clic en **Actions**

### 2️⃣ Crear Cada Secret

Haz clic en el botón verde **"New repository secret"** para cada uno de estos:

---

## 🔑 Secrets a Crear

### **Secret 1: BROWSERSTACK_USER**
- **Name**: `BROWSERSTACK_USER`
- **Secret**: `david3360` (o tu usuario de cuenta 1)
- Click **"Add secret"**

### **Secret 2: BROWSERSTACK_ACCESS_KEY**
- **Name**: `BROWSERSTACK_ACCESS_KEY`
- **Secret**: `CHYMa7RyuRpFNz6T3t8q` (o tu access key de cuenta 1)
- Click **"Add secret"**

### **Secret 3: BROWSERSTACK_USER2**
- **Name**: `BROWSERSTACK_USER2`
- **Secret**: Tu usuario de la segunda cuenta de BrowserStack
- Click **"Add secret"**

### **Secret 4: BROWSERSTACK_ACCESS_KEY2**
- **Name**: `BROWSERSTACK_ACCESS_KEY2`
- **Secret**: Tu access key de la segunda cuenta de BrowserStack
- Click **"Add secret"**

---

## ✅ Verificar que se crearon correctamente

Deberías ver los 4 secrets listados (no podrás ver los valores, solo los nombres):

```
BROWSERSTACK_USER
BROWSERSTACK_ACCESS_KEY
BROWSERSTACK_USER2
BROWSERSTACK_ACCESS_KEY2
```

---

## 📋 Dónde encontrar tus credenciales de BrowserStack

1. Inicia sesión en https://www.browserstack.com/
2. Ve a tu Dashboard
3. Haz clic en **"Access Key"** o tu perfil
4. Copia:
   - **Username** (ejemplo: `david3360`)
   - **Access Key** (ejemplo: `CHYMa7RyuRpFNz6T3t8q`)

---

## 🔄 Para actualizar un secret existente

1. Ve a la lista de secrets
2. Haz clic en el secret que quieres actualizar
3. Haz clic en **"Update secret"**
4. Pega el nuevo valor
5. Click **"Update secret"**

---

## 🧪 Probar que funcionan

Después de crear los secrets:

1. Ve a **Actions** en tu repositorio
2. Selecciona el workflow **"BrowserStack Tests - Account 1"**
3. Haz clic en **"Run workflow"** → **"Run workflow"**
4. Espera a que se ejecute (toma ~5-10 minutos)
5. Revisa los logs para verificar que conectó correctamente

---

## ⚠️ Notas de Seguridad

- ✅ Los secrets NO son visibles después de crearlos
- ✅ Los secrets NO aparecen en los logs (GitHub los oculta automáticamente)
- ✅ Los secrets solo están disponibles para workflows autorizados
- ❌ NO compartas tus access keys públicamente
- ❌ NO los subas al código fuente

---

## 🆘 Solución de Problemas

### Error: "Secret not found"
- Verifica que el nombre del secret sea exactamente como se muestra (case-sensitive)
- Verifica que estés en el repositorio correcto

### Error: "Authentication failed"
- Verifica que las credenciales sean correctas
- Prueba iniciar sesión en BrowserStack con esas credenciales
- Verifica que tu cuenta tenga minutos disponibles

### Los tests no se ejecutan
- Verifica que el workflow esté habilitado en Actions
- Verifica que la rama sea `master` (o la que configuraste)
- Revisa los logs del workflow para más detalles

