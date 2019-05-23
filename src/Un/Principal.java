package Un;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
//Immportamos el paquete para comprobar que no entren
import java.util.regex.*;

public class Principal {

    //Constantes para inicio de sesion
    static final String ADMINISTRADOR = "1";
    static final String PASSWORD = "1";
    //Patron con la expresion regular
    static final Pattern noNumerosconLetras = Pattern.compile("[^0-9]+"); //Expresion regular cambiada para que no
    // acepte numeros y letras
    static final Pattern noNumeros = Pattern.compile("[0-9]+");
    //Matcher
    Matcher m;
    //Nos serviran para empezar a controlar las entradas de los datos
    boolean errorString = true;
    boolean errorNumeric = true;

    //Variables
    private boolean login = false;
    private boolean menu = false;
    private int idElegido, regresar = 1;
    private int OpcSelec, OpcSelec2, OpcSelect3;
    private double salario;
    private String grupo;
    private Scanner entrada = new Scanner(System.in);
    private boolean impresion;
    double acumpago;


    //Arraylists con que almacenan los datos del programa
    private ArrayList<Estudiante> alumnos = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>();

    public static void main(String[] args) {
        Principal logica = new Principal();
        logica.Logica();

    }

    private void Logica() {
        Administrador Admon = new Administrador("Administrador", "Matu", "Lomas", "Idi Amin",
                "Administrador"); //Objeto para nuestra clase interface
        do {
            //Introduce los datos del usuario para comprobarlo
            String Usuario = JOptionPane.showInputDialog(null, "Ingresa tu usuario o escribe EXIT para salir");
            String Password = JOptionPane.showInputDialog(null, "Ingresa tu contraseña o escribe EXIT para salir");

            //Inicia login
            if (Usuario.equals(ADMINISTRADOR) && Password.equals(PASSWORD)) {
                menu = false; // Cambia la variable menu para entrar al menu
                login = true; //Cambia a la variable a true para no repetir el login
                JOptionPane.showMessageDialog(null, "INICIO DE SESION CORRECTO, DIRIGETE A LA TERMINAL...");
                System.out.println("**** M E N U ******");

                do {
                    MostrarMenuPrincipal();
                    OpcSelec = entrada.nextInt();
                    switch (OpcSelec) {
                        //Menu alumnos
                        case 1:
                            do {
                                regresar = 1; //Hace que regrese al menu
                                MostrarSubMenuAlumnos();
                                OpcSelec2 = entrada.nextInt();
                                //Menu Alumnos
                                switch (OpcSelec2) {
                                    case 1:
                                        this.CrearAlumno();//Creacion del alumno del administrador
                                        break;
                                    case 2:
                                        this.ImpresionArregloAlumnos(alumnos); //Borrar alumno
                                        if (this.impresion) {
                                            System.out.println("\nIngresa el ID del elemento: ");
                                            idElegido = entrada.nextInt();
                                            //Borra el elemento seleccionado
                                            alumnos.remove(idElegido);
                                        }
                                        break;
                                    case 3:
                                        //Imprimir datos de alumnos
                                        this.ImpresionArregloAlumnos(alumnos);
                                        break;
                                    case 4:
                                        regresar = 0;
                                        break;
                                }
                            } while (regresar != 0);
                            break;
                        case 2:
                            do {
                                regresar = 1;
                                MostrarSubMenuProf();
                                System.out.println("\t \t \t ¿Qué Opcion desea escoger?");
                                OpcSelect3 = entrada.nextInt();
                                switch (OpcSelect3) {
                                    case 1:
                                        this.CrearProfesor();
                                        break;
                                    case 2:
                                        this.ImpresionArregloProfesores(profesores);
                                        if (this.impresion) {
                                            System.out.println("\nIngresa el ID del elemento: ");
                                            idElegido = entrada.nextInt();
                                            //Borra el elemento seleccionado
                                            profesores.remove(idElegido);
                                        }
                                        break;
                                    case 3:
                                        this.ImpresionArregloProfesores(profesores);
                                        break;
                                    case 4:
                                        this.ImpresionArregloProfesores(profesores);
                                        if (this.impresion) {
                                            System.out.println("Ingresa el id para marcarle la asistencia");
                                            idElegido = entrada.nextInt();
                                            profesores.get(idElegido).setAsistencia(true);
                                        }
                                        break;
                                    case 5:
                                        //Asigna el grupo del profesor
                                        this.ImpresionArregloProfesores(profesores);
                                        if (this.impresion) {
                                            System.out.println("Ingrese el ID del profesor");
                                            idElegido = entrada.nextInt();
                                            entrada.nextLine();

                                            System.out.println("Ingresa el grupo:");
                                            grupo = entrada.nextLine();
                                            profesores.get(idElegido).setGrupo(grupo);
                                        }
                                        break;
                                    case 6:
                                        this.ImpresionArregloProfesores(profesores);
                                        if (this.impresion) {
                                            System.out.println("Ingresa el id para asignar pago");
                                            idElegido = entrada.nextInt();
                                            System.out.println("¿Cuantas horas trabajo el profesor");
                                            int ht = entrada.nextInt();
                                            profesores.get(idElegido).setHt(ht);
                                            salario = profesores.get(idElegido).cobrar();
                                            acumpago = Admon.estadodeCuenta(salario);//---------------Acumulador de todos los pagos.
                                            System.out.println("\n" + "El pago del profesor " + profesores.get(idElegido).getNombre() + " es"
                                                    + " " + salario);
                                        }
                                        break;
                                    case 7:
                                        System.out.println("\n" + "El Acumulado de todos los pagos es de : " + acumpago);
                                        break;
                                    case 8:
                                        regresar = 0;
                                        break;
                                }
                            } while (regresar != 0);
                            break;
                        case 3:
                            menu = true;
                            login = false;
                            System.out.println("\u000C");
                            break;
                    }
                } while (!menu);
            } else if (Usuario.equals("Maestro") && Password.equals(PASSWORD)) {
                menu = false;
                login = true; //Cambia a la variable a true para no repetir el login

                do {
                    MostrarMenuProf();
                    System.out.println("\t \t \t ¿Qué Opcion desea escoger?");
                    int resp2 = entrada.nextInt();

                    switch (resp2) {
                        case 1:
                            this.ImpresionArregloAlumnos(alumnos);
                            if (this.impresion) {
                                System.out.println("Ingrese el Id del Alumno");
                                int ID = entrada.nextInt();
                                entrada.nextLine();
                                System.out.println("¿El Alumno asistio hoy?");
                                String asis = entrada.nextLine();

                                if (asis.equals("si") || asis.equals("Si")) {
                                    alumnos.get(ID).setAsistencia(true);
                                } else {
                                    alumnos.get(ID).setAsistencia(false);
                                }

                            }
                            break;
                        case 2:
                            this.ImpresionArregloAlumnos(alumnos);
                            break;
                        case 3:
                            this.ImpresionArregloAlumnos(alumnos);
                            if (this.impresion) {
                                System.out.println("Ingrese el ID");
                                idElegido = entrada.nextInt();
                                System.out.println("Escribe el prommedio");
                                double prom = entrada.nextDouble();
                                alumnos.get(idElegido).setPromedio(prom);
                            }
                            break;
                        case 4:
                            menu = true;
                            login = false;
                            System.out.println("\u000C");
                            break;
                    }
                } while (!menu);
            } else if (Usuario.equals("Salario") && Password.equals("anotu")) {
                int horastraba = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa las horas trabajadas del administrador"));
                double salarioAd = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el salario del administrador"));
                double pagoTotal = Admon.cobrar(horastraba, salarioAd);
                JOptionPane.showMessageDialog(null, "El pago del administrador es: " + pagoTotal);

            } else if (Usuario.equals("EXIT") && Password.equals("EXIT")) {
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Tus datos son incorrectos verificalos");
            }
        } while (!login);

    }

    private void ImpresionArregloAlumnos(ArrayList<Estudiante> nombreArreglo) {
        if (!nombreArreglo.isEmpty()) {
            System.out.println("-----------------MOSTRANDO TODOS LOS ALUMNOS----------------------");
            for (int i = 0; i < nombreArreglo.size(); i++) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("ID: " + i + "\n Nombre: " + nombreArreglo.get(i).getNombre()
                        + "\nHorario: " + nombreArreglo.get(i).getHorario() + "\nGrupo: " + nombreArreglo.get(i).getGrupo() + "\nAsistencia: " + nombreArreglo.get(i).isAsistencia() + "\nPromedio: " + nombreArreglo.get(i).getPromedio());
                System.out.println("---------------------------------------------------------------");
                this.impresion = true;
            }
        } else {
            System.out.println("NO HAY ELEMENTOS PARA MOSTRAR");
            this.impresion = false;
        }

    }

    private void ImpresionArregloProfesores(ArrayList<Profesor> nombreArreglo) {
        if (!nombreArreglo.isEmpty()) {
            System.out.println("-----------------MOSTRANDO TODOS LOS PROFESORES----------------------");
            for (int i = 0; i < nombreArreglo.size(); i++) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("ID: " + i + "\nNombre: " + nombreArreglo.get(i).getNombre()
                        + "\nTurno: " + nombreArreglo.get(i).getHorario() + "\nGrupo asignado: " + nombreArreglo.get(i).getGrupo() + "\nAsistencia: " + nombreArreglo.get(i).isAsistencia()
                        + "\nCarrera: " + nombreArreglo.get(i).getCarrera() + "\nJefe inmediato: " + nombreArreglo.get(i).getJefe());
                System.out.println("---------------------------------------------------------------");
                this.impresion = true;
            }
        } else {
            System.out.println("NO HAY ELEMENTOS PARA MOSTRAR");
            this.impresion = false;
        }

    }

    private void CrearProfesor() {
        errorString = true; //Para que el ciclo vuelva a iniciar en TRUE
        //Variables locales que se usaran para el metodo
        String  nom2 = "ok";
        String  hor2 = "ok";
        String jefe = "ok";
        String carre2 = "ok";
        System.out.println("\n" + "CREANDO PROFESOR");
        entrada.nextLine(); //Limpia el buffer

        while (errorString){
            System.out.println("\n" + "Ingrese el Nombre Completo del profesor: ");
            nom2 = entrada.nextLine();
            m = noNumerosconLetras.matcher(nom2);
           if (m.matches()) {
            errorString = true;
            System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
        } else {
            errorString = false;
        }
    }

        System.out.println("\n" + "Ingrese Domicilio");
        String dom2 = entrada.nextLine();

        errorString = true;
        while(errorString){
            System.out.println("\n" + "Ingrese turno del profesor:");
            hor2 = entrada.nextLine();
            m = noNumerosconLetras.matcher(hor2);
            if (m.matches()) {
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
    }

        errorString = true;
        while(errorString){
        System.out.println("\n" + "Ingrese el jefe inmediato:");
        jefe = entrada.nextLine();
            m = noNumerosconLetras.matcher(jefe);
            if (m.matches()) {
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
        }
        errorString = true;
        while(errorString){
        System.out.println("\n" + "Ingrese la materia que imparte seguida de la carrera (Ej. Calculo Integral - SISTEMAS)");
        carre2 = entrada.nextLine();
        m = noNumerosconLetras.matcher(carre2);
        if (m.matches()) {
            errorString = true;
            System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
        } else {
            errorString = false;
        }
    }
        profesores.add(new Profesor(nom2, hor2, dom2, jefe, carre2));
    }

    private void CrearAlumno() {
        errorString = true; //Para que el ciclo vuelva a iniciar en TRUE
        //Variables locales que se usaran para el metodo
        String nom ="ok";
        String turn = "ok";
        String grup ="ok";
        System.out.println("\n" + "CREANDO ALUMNO");
        entrada.nextLine(); //Limpia el buffer

        while (errorString) { //Entra a comprobar el nombre
            System.out.println("\n" + "Ingrese el Nombre Completo: ");
            nom = entrada.nextLine();
            m = noNumerosconLetras.matcher(nom);
            if(!m.matches()){ //El matches tiene que ser false porque eso quiere decir que no encontro ningun numero.
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
        }

        errorString=true; //Vuelve a poner la variable en true para entrar al while
        while (errorString) {
            System.out.println("\n" + "Ingrese el Turno que le corresponde: ");
            turn = entrada.nextLine();
            m = noNumerosconLetras.matcher(turn);
            if (m.matches()) {
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
        }

        System.out.println("\n" + "Ingrese Domicilio");
        String dom = entrada.nextLine();

        System.out.println("\n" + "Ingrese Semestre: "+"     SOLO NUMEROS.");
        int gra = entrada.nextInt();

        errorString=true;
        entrada.nextLine();
        while(errorString) {
            System.out.println("\n" + "Ingrese su Grupo");
            grup = entrada.nextLine();
            m = noNumerosconLetras.matcher(grup);
            if (m.matches()) {
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
        }
        alumnos.add(new Estudiante(nom, turn, dom, gra, grup));
    }


    private static void MostrarMenuPrincipal() {
        System.out.println("\n" + "1. A L U M N O");
        System.out.println("2. P R O F E S O R");
        System.out.println("3. C E R R A R  S E S I O N");
        System.out.println("\t \t¿Qué Opcion desea escoger?");
    }

    private static void MostrarSubMenuAlumnos() {
        System.out.println("\n" + "1. C R E A R  A L U M N O");
        System.out.println("2. B O R R A R  A L U M N O");
        System.out.println("3. V E R  A L U M N O");
        System.out.println("4. R E G R E S A R");
        System.out.println("\t \tQué Opcion desea escoger?");
    }

    private static void MostrarMenuProf() {
        System.out.println("\n" + "1. P O N E R  A S I S T E N C I A");
        System.out.println("2. V E R  A L U M N O S");
        System.out.println("3. A S I G N A R  P R O M E D I O");
        System.out.println("4. C E R R A R  S E S I S I O N");
    }

    private static void MostrarSubMenuProf() {

        System.out.println("\n" + "1. C R E A R  P R O F E S O R E S");
        System.out.println("2. B O R R A R  P R O F E S O R E S");
        System.out.println("3. V E R  P R O F E S O R E S");
        System.out.println("4. P O N E R  A S I S T E N C I A");
        System.out.println("5. A S I G N A R  G R U P O");
        System.out.println("6. A S I G N A R  P A G O  A L  P R O F E S O R");
        System.out.println("7. V E R  E L  A C U M U L A D O  D E  P A G O  D E  L O S  M A E S T R O S");
        System.out.println("8. R E G R E S A R");
    }

}
