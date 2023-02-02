package com.wilsonriosv.EjerciciosSesiones4.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

@Entity
@ApiModel("Entidad laptop para registrar los datos de un objeto laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty("Clave tipo Long generada autoincremental")
    @Column(name = "id", nullable = false)
    private Long id;

    private String marca;
    private String modelo;
    private String serie;
    private String procesador;
    private Integer memoriaRAM;
    private boolean tarjetaVideo;

    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, String serie, String procesador, Integer memoriaRAM, boolean tarjetaVideo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
        this.procesador = procesador;
        this.memoriaRAM = memoriaRAM;
        this.tarjetaVideo = tarjetaVideo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Integer getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(Integer memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }

    public boolean isTarjetaVideo() {
        return tarjetaVideo;
    }

    public void setTarjetaVideo(boolean tarjetaVideo) {
        this.tarjetaVideo = tarjetaVideo;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", serie='" + serie + '\'' +
                ", procesador='" + procesador + '\'' +
                ", memoriaRAM=" + memoriaRAM +
                ", tarjetaVideo=" + tarjetaVideo +
                '}';
    }
}
