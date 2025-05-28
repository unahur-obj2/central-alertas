package ar.edu.unahur.obj2.observer.entidades;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.comportamientos.RiesgoCritico;
import ar.edu.unahur.obj2.observer.comportamientos.RiesgoStrategy;
import ar.edu.unahur.obj2.observer.models.Alerta;

public class Entidad implements Observer {

    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private final String nombre;
    private RiesgoStrategy comportamiento = new RiesgoCritico();

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }

    public void setComportamiento(RiesgoStrategy comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public double riesgo() {
        return comportamiento.riesgo(alertasRecibidas);
    }

    public Integer cantidadAlertasRecibidas() {
        return alertasRecibidas.size();
    }

}
