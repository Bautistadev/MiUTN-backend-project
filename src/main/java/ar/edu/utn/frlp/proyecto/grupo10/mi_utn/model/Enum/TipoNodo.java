package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum;


import java.util.Arrays;
import java.util.List;

public enum TipoNodo {
    AULA("aula", "Aula"),
    HALL("hall", "Hall"),
    BANO("bano", "Baño"),
    ESCALERA("escalera", "Escalera"),
    PUNTO("punto", "Punto"),
    EXTINTOR("extintor", "Matafuegos"),
    SALIDA_EMERGENCIA("salida_emergencia", "Salida Emergencia"),
    DESFIBRILADOR("desfibrilador", "Desfibrilador"),
    BOTIQUIN("botiquin", "Botiquín"),
    ALARMA("alarma", "Alarma"),
    TOTEM("totem", "Tótem");

    private final String valor;
    private final String descripcion;

    TipoNodo(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoNodo fromValor(String valor) {
        for (TipoNodo tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de nodo no válido: " + valor);
    }

    public static List<TipoNodo> getTiposArea() {
        return Arrays.asList(AULA, HALL, BANO, ESCALERA);
    }

    public static List<TipoNodo> getTiposPunto() {
        return Arrays.asList(PUNTO, EXTINTOR, SALIDA_EMERGENCIA, DESFIBRILADOR, BOTIQUIN, ALARMA, TOTEM);
    }

    public static List<TipoNodo> getTiposEspeciales() {
        return Arrays.asList(EXTINTOR, SALIDA_EMERGENCIA, DESFIBRILADOR, BOTIQUIN, ALARMA, TOTEM);
    }

    public boolean esArea() {
        return getTiposArea().contains(this);
    }

    public boolean esPunto() {
        return getTiposPunto().contains(this);
    }

    public boolean esEspecial() {
        return getTiposEspeciales().contains(this);
    }
}
