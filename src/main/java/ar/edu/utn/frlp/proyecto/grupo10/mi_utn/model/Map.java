package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@Table(name="map")
@Entity
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JSONObjectConverter.class) // Aplicar el converter
    private JSONObject jsonData; // Directamente JSONObject

    // Constructores
    public Map() {

        this.jsonData = new JSONObject(); // Inicializar con JSON vacío
    }

    public Map(String nombre, JSONObject jsonData) {
        this();
        this.jsonData = jsonData != null ? jsonData : new JSONObject();
    }

    // Getters y setters NORMALES - sin métodos helper adicionales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public JSONObject getJsonData() {
        if (jsonData == null) {
            jsonData = new JSONObject();
        }
        return jsonData;
    }

    public void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData != null ? jsonData : new JSONObject();
        System.out.println("Set JSONData: " + this.jsonData.toString());
    }
}

@Converter(autoApply = true) // Cambia a true para aplicar automáticamente
class JSONObjectConverter implements AttributeConverter<JSONObject, String> {

    @Override
    public String convertToDatabaseColumn(JSONObject attribute) {
        if (attribute == null) {
            System.out.println("Converter: JSONObject es null, guardando como '{}'");
            return "{}"; // Guardar JSON vacío en lugar de null
        }
        String result = attribute.toString();
        System.out.println("Converter: Guardando JSON: " + result);
        return result;
    }

    @Override
    public JSONObject convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.trim().isEmpty()) {
                System.out.println("Converter: DB data es null o vacío, retornando new JSONObject()");
                return new JSONObject();
            }
            JSONObject result = new JSONObject(dbData);
            System.out.println("Converter: Cargando JSON desde DB: " + result.toString());
            return result;
        } catch (JSONException e) {
            System.err.println("Converter: Error parsing JSON: " + e.getMessage());
            return new JSONObject();
        }
    }
}
