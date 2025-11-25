JAVAC      := javac
JAVA       := java
JFLAGS     := -encoding UTF-8 -Xlint:unchecked -Xlint:deprecation
SRC_DIR    := .
BIN_DIR    := bin

ifeq ($(OS),Windows_NT)
  PSEP := ;
else
  PSEP := :
endif
CLASSPATH  := $(BIN_DIR)
SOURCES := $(shell find $(SRC_DIR) -name "*.java")
MAIN_CLASS := tarea3_LP.Main

.PHONY: all run clean jar help
all: $(BIN_DIR)/.compiled
$(BIN_DIR)/.compiled: $(SOURCES)
	@mkdir -p $(BIN_DIR)
	@echo "Compilando fuentes..."
	$(JAVAC) $(JFLAGS) -d $(BIN_DIR) -cp $(CLASSPATH) $(SOURCES)
	@# Marca de compilación exitosa
	@touch $(BIN_DIR)/.compiled
	@echo "OK: clases en $(BIN_DIR)/"

run: all
	@echo "Ejecutando $(MAIN_CLASS)..."
	$(JAVA) -cp $(BIN_DIR)$(PSEP)$(CLASSPATH) $(MAIN_CLASS)

clean:
	@echo "Limpiando bin/"
	@rm -rf $(BIN_DIR)
jar: all
	@echo "Creando JAR ejecutable app.jar..."
	@echo "Main-Class: $(MAIN_CLASS)" > manifest.tmp
	@jar cfm app.jar manifest.tmp -C $(BIN_DIR) .
	@rm -f manifest.tmp
	@echo "Listo: app.jar"

help:
	@echo "Comandos disponibles:"
	@echo "  make          -> compilar"
	@echo "  make run      -> compilar y ejecutar Main"
	@echo "  make clean    -> borrar bin/"
	@echo "  make jar      -> crear app.jar (opcional)"
