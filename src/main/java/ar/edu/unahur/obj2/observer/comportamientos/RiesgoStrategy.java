package ar.edu.unahur.obj2.observer.comportamientos;

import java.util.List;

import ar.edu.unahur.obj2.observer.models.Alerta;

public interface RiesgoStrategy {
    double riesgo(List<Alerta> alertas);
}
