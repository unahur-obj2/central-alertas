package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unahur.obj2.observer.entidades.Observer;
import ar.edu.unahur.obj2.observer.models.Alerta;
import ar.edu.unahur.obj2.observer.models.Registro;

public class Central implements Observable {
    private List<Registro> registroAlertas = new ArrayList<>();
    private Set<Observer> entidades = new HashSet<>();

    public void emitirAlerta(String tipo, Integer nivel) {
        if (nivel <= 0 || nivel > 10)
            throw new IllegalArgumentException("Nivel de alerta incorrecto");
        registroAlertas.add(new Registro(new Alerta(tipo, nivel), entidades.size()));
        notificar();
    }

    @Override
    public void addEntidad(Observer entidad) {
        entidades.add(entidad);
    }

    @Override
    public void removeEntidad(Observer entidad) {
        entidades.remove(entidad);
    }

    @Override
    public void notificar() {
        Alerta alerta = registroAlertas.get(registroAlertas.size() - 1).getAlerta();
        entidades.stream().forEach(entidad -> entidad.update(alerta));
    }

    public Integer cantidadAlertasRegistrada() {
        return registroAlertas.size();
    }

    public Integer cantidadNotificacionesEnviadad() {
        return registroAlertas.stream().mapToInt(Registro::getCantidadNotificaciones).sum();
    }
}
