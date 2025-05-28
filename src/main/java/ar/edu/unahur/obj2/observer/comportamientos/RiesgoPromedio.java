package ar.edu.unahur.obj2.observer.comportamientos;

import java.util.List;

import ar.edu.unahur.obj2.observer.models.Alerta;

public class RiesgoPromedio implements RiesgoStrategy {

    @Override
    public double riesgo(List<Alerta> alertas) {
        return alertas.stream().mapToInt(Alerta::getNivel).sum() / (double) alertas.size();
    }

}
