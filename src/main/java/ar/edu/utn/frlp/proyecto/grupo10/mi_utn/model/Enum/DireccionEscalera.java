package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum;

import java.util.Arrays;
import java.util.List;

public enum DireccionEscalera {
    AMBOS("ambos", " Ambos sentidos"),
    SUBIDA("subida", " Solo subida"),
    BAJADA("bajada", "Solo bajada");

    private final String valor;
    private final String descripcion;

    DireccionEscalera(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Método para obtener el enum desde un string
    public static DireccionEscalera fromValor(String valor) {
        for (DireccionEscalera direccion : values()) {
            if (direccion.valor.equalsIgnoreCase(valor)) {
                return direccion;
            }
        }
        throw new IllegalArgumentException("Dirección no válida: " + valor);
    }

    // Método para usar en los dropdowns del frontend
    public static List<DireccionEscalera> getOpciones() {
        return Arrays.asList(values());
    }
}