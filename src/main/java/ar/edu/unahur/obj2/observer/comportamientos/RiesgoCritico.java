package ar.edu.unahur.obj2.observer.comportamientos;

import java.util.List;

import ar.edu.unahur.obj2.observer.models.Alerta;

public class RiesgoCritico implements RiesgoStrategy {

    @Override
    public double riesgo(List<Alerta> alertas) {
        Alerta ultima = alertas.get(alertas.size() - 1);
        return Boolean.TRUE.equals(ultima.esCritica()) ? 10.0 : ultima.getNivel();
    }

}
