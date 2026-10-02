package modelo;

import java.io.Serializable;

public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pony;

    public Voto(String pony) {
        this.pony = pony;
    }

    public String getPony() {
        return pony;
    }

    @Override
    public String toString() {
        return "Voto{" + "pony=" + pony + '}';
    }
}