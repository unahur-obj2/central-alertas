package ar.edu.unahur.obj2.observer.models;

public class Registro {
    private final Alerta alerta;
    private final Integer cantidadNotificaciones;

    public Registro(Alerta alerta, Integer cantidadNotificaciones) {
        this.alerta = alerta;
        this.cantidadNotificaciones = cantidadNotificaciones;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public Integer getCantidadNotificaciones() {
        return cantidadNotificaciones;
    }

}
