package Un;

public class Estudiante extends Universidad {

    protected int grado;
    protected String grupo;
    protected boolean asistencia;
    protected double promedio;

    public Estudiante(String nombre, String horario, String domicilio, int grado, String grupo) {
        super(nombre, horario, domicilio);
        this.grado = grado;
        this.grupo = grupo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
