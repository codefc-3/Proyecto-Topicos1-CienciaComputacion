package app.mycompany.warnapp;

public class Alerta {


    String claseid, ubicacion, alerta;

    public Alerta(String claseid, String ubicacion, String alerta) {
        this.claseid = claseid;
        this.ubicacion = ubicacion;
        this.alerta = alerta;
    }

    public String getClaseid() {
        return claseid;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getAlerta() {
        return alerta;
    }

}
