package Un;

public class Administrador extends Empleado implements Banco {

    String puesto;
    double estadodecuenta;

    public Administrador(String nombre, String horario, String domicilio, String jefe, String puesto) {
        super(nombre, horario, domicilio, jefe);
        this.puesto = puesto;
    }

    public double cobrar(int ht, double salario) {
        double calculoSalario = ht * salario;
        return calculoSalario;
    }

    @Override
    public double estadodeCuenta(double Salario) {
        this.estadodecuenta += Salario;
        return estadodecuenta;
    }
}
