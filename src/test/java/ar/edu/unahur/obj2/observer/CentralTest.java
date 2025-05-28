package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.comportamientos.RiesgoPromedio;
import ar.edu.unahur.obj2.observer.entidades.Entidad;

class CentralTest {

    Central central;
    Entidad e1;
    Entidad e2;
    Entidad e3;

    @BeforeEach
    void setUp() {
        central = new Central();
        e1 = new Entidad("n1");
        e2 = new Entidad("n2");
        e3 = new Entidad("n3");

        central.addEntidad(e1);
        central.addEntidad(e2);
    }

    @Test
    void testEmisionAlertaValida() {

        central.emitirAlerta("Inundación", 5);
        assertEquals(1, central.cantidadAlertasRegistrada());
    }

    @Test
    void testEmisionAlertaNivelInvalidoMayor() {

        assertThrows(IllegalArgumentException.class,
                () -> central.emitirAlerta("Terremoto", 11),
                "Nivel de alerta incorrecto");
    }

    @Test
    void testEmisionAlertaNivelInvalidoMenor() {

        assertThrows(IllegalArgumentException.class,
                () -> central.emitirAlerta("Terremoto", -3),
                "Nivel de alerta incorrecto");
    }

    @Test
    void dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        assertEquals(2, e1.cantidadAlertasRecibidas());
        assertEquals(2, e2.cantidadAlertasRecibidas());
        assertEquals(10, e1.riesgo());
        assertEquals(10, e2.riesgo());
    }

    @Test
    void dadoElSetUp_alCambiarElComportamientoAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        e1.setComportamiento(new RiesgoPromedio());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);

        assertEquals(2, e1.cantidadAlertasRecibidas());
        assertEquals(2, e2.cantidadAlertasRecibidas());
        assertEquals(7, e1.riesgo());
        assertEquals(10, e2.riesgo());
    }

    @Test
    void dadoElSetUp_alAgregarAlertasQuitaUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {

        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        central.removeEntidad(e1);
        central.emitirAlerta("granizo", 7);
        assertEquals(2, e1.cantidadAlertasRecibidas());
        assertEquals(3, e2.cantidadAlertasRecibidas());
        assertEquals(10, e1.riesgo());
        assertEquals(7, e2.riesgo());
        assertEquals(5, central.cantidadNotificacionesEnviadad());
    }
}