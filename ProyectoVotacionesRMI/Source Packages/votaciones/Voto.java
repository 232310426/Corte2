

import java.io.Serializable;

public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String opcion;

    public Voto(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion() {
        return opcion;
    }

    @Override
    public String toString() {
        return "Voto{opcion='" + opcion + "'}";
    }
}