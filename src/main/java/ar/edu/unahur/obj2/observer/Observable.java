package ar.edu.unahur.obj2.observer;

import ar.edu.unahur.obj2.observer.entidades.Observer;

public interface Observable {

    void addEntidad(Observer entidad);

    void removeEntidad(Observer entidad);

    void notificar();

}
