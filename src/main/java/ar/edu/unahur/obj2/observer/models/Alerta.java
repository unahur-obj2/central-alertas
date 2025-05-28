package ar.edu.unahur.obj2.observer.models;

public class Alerta {
    private final String tipo;
    private final Integer nivel;

    public Alerta(String tipo, Integer nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public Boolean esCritica() {
        return Boolean.TRUE.equals(nivel >= 8);
    }
}
