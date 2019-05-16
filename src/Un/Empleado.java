package Un;

public class Empleado extends Universidad {

    protected String jefe;
    int ht;

    public Empleado(String nombre, String horario, String domicilio, String jefe) {
        super(nombre, horario, domicilio);
        this.jefe = jefe;
    }

    public int getHt() {
        return ht;
    }

    public void setHt(int ht) {
        this.ht = ht;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    /**
     * Metodo que servira para aplicar el polimorfismo heredando a admon y
     * profesor
     *
     * @return
     */
    public double cobrar() {

        return 0;
    }

}
