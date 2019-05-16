package Un;

public class Profesor extends Empleado {

    protected String carrera;
    String grupo;
    boolean asistencia;

    public Profesor(String nombre, String horario, String domicilio, String jefe, String carrera) {
        super(nombre, horario, domicilio, jefe);
        this.carrera = carrera;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void enseñar() {
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public double cobrar() {
        double salario;
        salario = this.getHt() * 80;
        return salario;

    }

}
