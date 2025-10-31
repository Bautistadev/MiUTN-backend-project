package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "puntos_especiales")
@PrimaryKeyJoinColumn(name = "nodo_id")
public class PuntoEspecial extends Nodo {
    // 🔥 SOLO HEREDA - ya tiene coordenadaX, coordenadaY, tipo, etc.
    // Los tótems, extintores, desfibriladores son puntos especiales
}