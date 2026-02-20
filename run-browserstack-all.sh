#!/bin/bash

# Script para ejecutar tests en TODAS las plataformas de BrowserStack en paralelo
# Uso: ./run-browserstack-all.sh

echo "🚀 Ejecutando tests en TODAS las plataformas de BrowserStack"
echo "🌎 Geolocalización: Colombia"
echo "================================"
echo "💻 Windows 10 + Chrome Latest (Última versión)"
echo "🍎 macOS Monterey + Safari 15.6"
echo "📱 iPhone 13 + Chromium"
echo "================================"
echo ""

# Crear directorio para logs
mkdir -p logs

echo "▶️  Iniciando ejecución Windows..."
mvn clean verify \
  -Denvironment=browserstack \
  -Dplatform=windows \
  -Dcucumber.filter.tags="@webMuthematrix" \
  -Dmaven.test.failure.ignore=true \
  > logs/windows.log 2>&1 &

WINDOWS_PID=$!

sleep 5

echo "▶️  Iniciando ejecución Mac..."
mvn verify \
  -Denvironment=browserstack \
  -Dplatform=mac \
  -Dcucumber.filter.tags="@webMuthematrix" \
  -Dmaven.test.failure.ignore=true \
  > logs/mac.log 2>&1 &

MAC_PID=$!

sleep 5

echo "▶️  Iniciando ejecución iPhone..."
mvn verify \
  -Denvironment=browserstack \
  -Dplatform=iphone \
  -Dcucumber.filter.tags="@webMuthematrix" \
  -Dmaven.test.failure.ignore=true \
  > logs/iphone.log 2>&1 &

IPHONE_PID=$!

echo ""
echo "⏳ Esperando que terminen todas las ejecuciones..."
echo "   Windows PID: $WINDOWS_PID"
echo "   Mac PID: $MAC_PID"
echo "   iPhone PID: $IPHONE_PID"
echo ""
echo "💡 Puedes ver los logs en tiempo real con:"
echo "   tail -f logs/windows.log"
echo "   tail -f logs/mac.log"
echo "   tail -f logs/iphone.log"

# Esperar a que terminen todos los procesos
wait $WINDOWS_PID
WINDOWS_STATUS=$?

wait $MAC_PID
MAC_STATUS=$?

wait $IPHONE_PID
IPHONE_STATUS=$?

echo ""
echo "================================"
echo "✅ Todas las ejecuciones completadas"
echo "================================"
echo "📊 Resultados:"
echo "   Windows: $([ $WINDOWS_STATUS -eq 0 ] && echo '✅ PASSED' || echo '❌ FAILED')"
echo "   Mac:     $([ $MAC_STATUS -eq 0 ] && echo '✅ PASSED' || echo '❌ FAILED')"
echo "   iPhone:  $([ $IPHONE_STATUS -eq 0 ] && echo '✅ PASSED' || echo '❌ FAILED')"
echo ""
echo "📂 Logs guardados en: logs/"
echo "📊 Revisa el reporte en: target/site/serenity/index.html"
echo "🔗 Dashboard de BrowserStack: https://automate.browserstack.com/dashboard"

