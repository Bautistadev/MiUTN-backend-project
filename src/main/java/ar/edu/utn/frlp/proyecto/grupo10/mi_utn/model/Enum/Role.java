package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum;

public enum Role {
    ADMIN("ADMIN"),
    SISTEMAS("SISTEMAS"),
    MECANICA("MECANICA"),
    QUIMICA("QUIMICA"),
    INDUSTRIAL("INDUSTRIAL"),
    ELECTRICA("ELECTRICA"),
    CIVIL("CIVIL");

    private String name;

    private Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
