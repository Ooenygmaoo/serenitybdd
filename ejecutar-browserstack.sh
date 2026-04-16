#!/bin/bash

###############################################################################
# Script para ejecutar tests de BrowserStack por plataforma
# Uso: ./ejecutar-browserstack.sh [windows|mac|all]
###############################################################################

set -e  # Salir si hay error

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Banner
echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║         🌐 BrowserStack Test Execution - Multi-Platform      ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Verificar que las credenciales estén configuradas
if [ -z "$BROWSERSTACK_USER" ] || [ -z "$BROWSERSTACK_KEY" ]; then
    echo -e "${RED}❌ ERROR: Credenciales de BrowserStack no configuradas${NC}"
    echo ""
    echo "Por favor, configura las variables de entorno:"
    echo "   export BROWSERSTACK_USER='tu_usuario'"
    echo "   export BROWSERSTACK_KEY='tu_access_key'"
    echo ""
    exit 1
fi

# Verificar argumento
if [ -z "$1" ]; then
    echo -e "${YELLOW}⚠️  No se especificó plataforma. Opciones disponibles:${NC}"
    echo ""
    echo "   windows) Test en Windows 10 + Chrome (Usuario: David)"
    echo "   mac)     Test en Mac OS Monterey + Safari (Usuario: Enzo)"
    echo "   all)     Ambas plataformas (secuencial)"
    echo ""
    read -p "Selecciona la plataforma (windows/mac/all): " PLATFORM
else
    PLATFORM=$1
fi

# Función para ejecutar test
run_test() {
    local TAG=$1
    local DESC=$2

    echo -e "${GREEN}✅ Ejecutando: $DESC${NC}"
    echo "─────────────────────────────────────────────────────────"
    echo -e "${BLUE}📋 Configuración:${NC}"
    echo "   Usuario BrowserStack: $BROWSERSTACK_USER"
    echo "   Entorno: browserstack"
    echo "   Tag: @$TAG"
    echo ""

    echo -e "${YELLOW}🚀 Iniciando ejecución...${NC}"
    echo ""

    mvn clean verify \
        -Denvironment=browserstack \
        -Dcucumber.filter.tags="@$TAG" \
        -Dmaven.test.failure.ignore=true

    return $?
}

# Ejecutar según la plataforma seleccionada
case $PLATFORM in
    windows)
        run_test "windows" "Windows 10 + Chrome Latest (Usuario: David / slash17)"
        RESULT=$?
        ;;

    mac)
        run_test "mac" "Mac OS Monterey + Safari 15.6 (Usuario: Enzo / enzo)"
        RESULT=$?
        ;;

    all)
        echo -e "${BLUE}📋 Ejecutando tests en TODAS las plataformas...${NC}"
        echo ""

        run_test "windows" "Windows 10 + Chrome Latest (Usuario: David / slash17)"
        RESULT_WIN=$?

        echo ""
        echo "════════════════════════════════════════════════════════════════"
        echo ""

        run_test "mac" "Mac OS Monterey + Safari 15.6 (Usuario: Enzo / enzo)"
        RESULT_MAC=$?

        # Resultado combinado
        if [ $RESULT_WIN -eq 0 ] && [ $RESULT_MAC -eq 0 ]; then
            RESULT=0
        else
            RESULT=1
        fi
        ;;

    *)
        echo -e "${RED}❌ ERROR: Opción inválida. Usa: windows, mac, o all${NC}"
        exit 1
        ;;
esac

# Generar reporte Serenity
echo ""
echo -e "${YELLOW}📊 Generando reporte Serenity...${NC}"
mvn serenity:aggregate

# Resultado final
echo ""
echo "════════════════════════════════════════════════════════════════"
if [ $RESULT -eq 0 ]; then
    echo -e "${GREEN}✅ TESTS COMPLETADOS EXITOSAMENTE${NC}"
else
    echo -e "${RED}❌ ALGUNOS TESTS FALLARON - Ver reporte para detalles${NC}"
fi
echo "════════════════════════════════════════════════════════════════"
echo ""
echo -e "${BLUE}📁 Reporte disponible en:${NC} target/site/serenity/index.html"
echo -e "${BLUE}🔗 Dashboard BrowserStack:${NC} https://automate.browserstack.com/dashboard"
echo ""
echo -e "${BLUE}🎯 Tests ejecutados:${NC}"
if [ "$PLATFORM" == "all" ] || [ "$PLATFORM" == "windows" ]; then
    echo "   • Windows 10 Chrome - David (slash17)"
fi
if [ "$PLATFORM" == "all" ] || [ "$PLATFORM" == "mac" ]; then
    echo "   • Mac OS Safari - Enzo (enzo)"
fi
echo ""

exit $RESULT

