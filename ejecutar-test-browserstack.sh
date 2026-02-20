#!/bin/bash

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║                                                                ║"
echo "║     🚀 EJECUTANDO TEST EN BROWSERSTACK 🚀                     ║"
echo "║                                                                ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""
echo "📋 Configuración:"
echo "   • Plataforma: Windows 10"
echo "   • Navegador: Chrome Latest (última versión)"
echo "   • Geolocalización: Colombia 🇨🇴"
echo "   • Test: voteMuthematrix (@webMuthematrix)"
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Intentar diferentes comandos de Maven
if command -v mvn &> /dev/null; then
    echo "✓ Usando Maven del sistema"
    MVN_CMD="mvn"
elif [ -f "./mvnw" ]; then
    echo "✓ Usando Maven Wrapper (./mvnw)"
    MVN_CMD="./mvnw"
elif command -v /usr/local/bin/mvn &> /dev/null; then
    echo "✓ Usando Maven de /usr/local/bin"
    MVN_CMD="/usr/local/bin/mvn"
else
    echo "❌ Error: Maven no encontrado"
    echo ""
    echo "💡 Opciones:"
    echo "   1. Instalar Maven: brew install maven"
    echo "   2. Ejecutar desde IntelliJ IDEA:"
    echo "      • Abre el proyecto"
    echo "      • Click derecho en pom.xml"
    echo "      • Maven → Run Maven Goal"
    echo "      • Ejecuta: clean verify -Denvironment=browserstack -Dcucumber.filter.tags=\"@webMuthematrix\""
    echo ""
    exit 1
fi

echo "▶️  Iniciando ejecución..."
echo ""

# Ejecutar Maven
$MVN_CMD clean verify \
  -Denvironment=browserstack \
  -Dcucumber.filter.tags="@webMuthematrix" \
  -Dmaven.test.failure.ignore=true

EXIT_CODE=$?

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

if [ $EXIT_CODE -eq 0 ]; then
    echo "✅ ¡Ejecución completada con éxito!"
else
    echo "⚠️  Ejecución completada con código: $EXIT_CODE"
fi

echo ""
echo "📊 Ver resultados:"
echo "   • Reporte local: open target/site/serenity/index.html"
echo "   • BrowserStack Dashboard: https://automate.browserstack.com/dashboard"
echo ""
echo "🎬 Video y logs disponibles en BrowserStack"
echo ""

