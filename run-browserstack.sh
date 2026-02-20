#!/bin/bash

# Script para ejecutar tests en BrowserStack
# Uso: ./run-browserstack.sh [windows|mac|iphone]

PLATFORM=${1:-windows}

echo "🚀 Ejecutando tests en BrowserStack"
echo "📱 Plataforma: $PLATFORM"
echo "🌎 Geolocalización: Colombia"
echo "================================"

case $PLATFORM in
  windows)
    echo "💻 Windows 10 + Chrome Latest (Última versión)"
    ;;
  mac)
    echo "🍎 macOS Monterey + Safari 15.6"
    ;;
  iphone)
    echo "📱 iPhone 13 + Chromium"
    ;;
  *)
    echo "❌ Plataforma no válida. Usa: windows, mac o iphone"
    exit 1
    ;;
esac

echo "================================"
echo ""

# Ejecutar Maven con la configuración de BrowserStack
mvn clean verify \
  -Denvironment=browserstack \
  -Dplatform=$PLATFORM \
  -Dcucumber.filter.tags="@webMuthematrix" \
  -Dmaven.test.failure.ignore=true

echo ""
echo "✅ Ejecución completada"
echo "📊 Revisa el reporte en: target/site/serenity/index.html"
echo "🔗 Dashboard de BrowserStack: https://automate.browserstack.com/dashboard"

