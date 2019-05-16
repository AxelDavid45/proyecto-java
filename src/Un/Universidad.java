package Un;

public class Universidad {

    protected String nombre;
    protected String horario;
    protected String domicilio;

    public Universidad(String nombre, String horario, String domicilio) {
        this.nombre = nombre;
        this.horario = horario;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

}
