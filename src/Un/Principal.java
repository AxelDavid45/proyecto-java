package Un;

import com.sun.javafx.image.IntPixelGetter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

//Immportamos el paquete para comprobar que no entren
import java.util.regex.*;// para las excepciones creadas por el usuario

public class Principal {
    //Constantes para inicio de sesion
    static final String ADMINISTRADOR = "1";
    static final String PASSWORD = "1";
    //Patron con la expresion regular
    // Expresión regular cambiada para que no acepte numeros
    static final Pattern noNumerosconLetras = Pattern.compile("[^0-9]+");
    //Matcher
    Matcher m;
    //Nos serviran para empezar a controlar las entradas de los datos
    boolean errorString = true;
    boolean error = true;

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
    String stringToNumber;


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
        try {
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
                        error = true;
                        while (error) {

                            try {
                                MostrarMenuPrincipal();
                                stringToNumber = entrada.nextLine();
                                OpcSelec = Integer.parseInt(stringToNumber);
                                error = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: No introduzcas letras, intentalo de nuevo");
                            }

                        }

                        switch (OpcSelec) {
                            //Menu alumnos
                            case 1:
                                do {
                                    regresar = 1; //Hace que regrese al menu
                                    error = true;
                                    while (error) { //Try para el menu de alumnos
                                        try {
                                            MostrarSubMenuAlumnos();
                                            stringToNumber = entrada.nextLine(); //Tenemos una cadena
                                            OpcSelec2 = Integer.parseInt(stringToNumber); //La convertimos a entero
                                            error = false;
                                        } catch (NumberFormatException e) {
                                            System.out.println("Error: No introduzcas letras, intentalo de nuevo");
                                        }

                                    } //Fin del bucle para try-catch

                                    //Menu Alumnos

                                    switch (OpcSelec2) {
                                        case 1:
                                            this.CrearAlumno();//Creacion del alumno del administrador
                                            break;
                                        case 2:
                                            this.ImpresionArregloAlumnos(alumnos); //Borrar alumno
                                            if (this.impresion) {
                                                error = true;
                                                while (error) { //Empieza comprobacion de id existente
                                                    System.out.println("\nIngresa el ID del elemento: ");
                                                    idElegido = entrada.nextInt();
                                                    if (idElegido > (alumnos.size() - 1)) {
                                                        System.out.println("Error: Has ingresado un ID inexistente..");
                                                    } else {
                                                        error = false;
                                                        //Borra el elemento seleccionado
                                                        alumnos.remove(idElegido);
                                                    }
                                                }

                                            }
                                            break;
                                        case 3:
                                            //Imprimir datos de alumnos
                                            this.ImpresionArregloAlumnos(alumnos);
                                            break;
                                        case 4:
                                            regresar = 0;
                                            System.out.println(regresar);
                                            break;
                                    }
                                } while (regresar != 0);
                                break;
                            case 2:
                                do {
                                    regresar = 1;
                                    error = true;
                                    while (error) { //Inicia bucle para try-catch profesores
                                        try {
                                            MostrarSubMenuProf(); //Menu de profesores
                                            System.out.println("\t \t \t ¿Que opcion desea escoger?");
                                            stringToNumber = entrada.nextLine();
                                            OpcSelect3 = Integer.parseInt(stringToNumber);
                                            error = false;
                                        } catch (NumberFormatException e) {
                                            System.out.println("Error: No introduzcas letras, intentalo de nuevo");
                                        }
                                    } //Fin del bucle try-catch profesores
                                    switch (OpcSelect3) {
                                        case 1:
                                            this.CrearProfesor();
                                            break;
                                        case 2:

                                            this.ImpresionArregloProfesores(profesores);
                                            if (this.impresion) {
                                                error = true;
                                                while (error) {
                                                    while (error) {
                                                        try {
                                                            System.out.println("\nIngresa el ID del elemento: ");
                                                            stringToNumber = entrada.nextLine();
                                                            idElegido = Integer.parseInt(stringToNumber);
                                                            error = false;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Error: Introduce solo numeros...");
                                                        }
                                                    }
                                                    if (idElegido > profesores.size() - 1) {
                                                        System.out.println("Error: Has ingresado un ID inexistente..");
                                                        error = true;
                                                    } else {
                                                        error = false;
                                                        profesores.remove(idElegido); //Borra el elemento seleccionado
                                                    }
                                                }
                                            }
                                            break;
                                        case 3:
                                            this.ImpresionArregloProfesores(profesores);
                                            break;
                                        case 4:
                                            this.ImpresionArregloProfesores(profesores);
                                            if (this.impresion) {
                                                error = true;
                                                while (error) {
                                                    while (error) {
                                                        try {
                                                            System.out.println("Ingresa el id para marcarle la asistencia");
                                                            stringToNumber = entrada.nextLine();
                                                            idElegido = Integer.parseInt(stringToNumber);
                                                            error = false;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Error: Introduce solo numeros...");
                                                        }
                                                    }
                                                    if (idElegido > (profesores.size() - 1)) {
                                                        System.out.println("Error: Has ingresado un ID inexistente..");
                                                        error = true;
                                                    } else {
                                                        error = false;
                                                        profesores.get(idElegido).setAsistencia(true);
                                                    }
                                                }
                                            }
                                            break;
                                        case 5:
                                            //Asigna el grupo del profesor
                                            this.ImpresionArregloProfesores(profesores);
                                            if (this.impresion) {
                                                String grupo = "ok";
                                                error = true;
                                                while (error) {
                                                    while (error) {
                                                        try {
                                                            System.out.println("Ingrese el ID del profesor");
                                                            stringToNumber = entrada.nextLine();
                                                            idElegido = Integer.parseInt(stringToNumber);
                                                            error = false;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println("Error: Escribe solo numeros...");
                                                        }
                                                    }
                                                    if (idElegido > (profesores.size() - 1)) {
                                                        System.out.println("Error: No existe el id...");
                                                        error = true;
                                                    } else {
                                                        entrada.nextLine();
                                                        errorString = true;
                                                        while (errorString) {
                                                            System.out.println("Ingresa el grupo:");
                                                            grupo = entrada.nextLine();
                                                            m = noNumerosconLetras.matcher(grupo);
                                                            if (!m.matches()) {
                                                                errorString = true;
                                                                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO. (SOLO SE ADMITEN LETRAS)");
                                                            } else {
                                                                profesores.get(idElegido).setGrupo(grupo);
                                                                errorString = false;
                                                            }
                                                        }

                                                    }
                                                }




                                            }
                                            break;
                                        case 6:
                                            this.ImpresionArregloProfesores(profesores);
                                            error = true;
                                            int ht = 0;
                                            if (this.impresion) {
                                                while (error) {
                                                    try {
                                                        System.out.println("Ingresa el id para asignar pago");
                                                        stringToNumber = entrada.nextLine();
                                                        idElegido = Integer.parseInt(stringToNumber);
                                                        System.out.println("¿Cuantas horas trabajo el profesor");
                                                        stringToNumber = entrada.nextLine();
                                                        ht = Integer.parseInt(stringToNumber);
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Error: Solo escribe numeros...");
                                                    }
                                                    if (idElegido > (profesores.size() - 1)) {
                                                        System.out.println("Error: No existe ese id...");
                                                        error = true;
                                                    } else {
                                                        profesores.get(idElegido).setHt(ht);
                                                        salario = profesores.get(idElegido).cobrar();
                                                        acumpago = Admon.estadodeCuenta(salario);//---------------Acumulador de todos los pagos.
                                                        System.out.println("\n" + "El pago del profesor " + profesores.get(idElegido).getNombre() + " es"
                                                                + " " + salario);
                                                        error = false;
                                                    }
                                                }

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
                                try {
                                    guardarArregloAlumno(alumnos, "alumnos.txt");
                                    System.out.println("Exportado con exito");
                                } catch (IOException e) {
                                    System.out.println("error: " + e.getMessage());
                                    System.out.println("No se pudo guardar el fichero");
                                }
                                break;
                            case 4:
                                try {
                                    guardarArregloProfesor(profesores, "profesores.txt");
                                    System.out.println("Exportado con exito");
                                } catch (IOException e) {
                                    System.out.println("error: " + e.getMessage());
                                    System.out.println("No se pudo guardar el fichero");
                                }
                                break;
                            case 5: //Cierra sesión
                                menu = true;
                                login = false;
                                break;
                        }
                    } while (!menu);
                } else if (Usuario.equals("Maestro") && Password.equals(PASSWORD)) {
                    menu = false;
                    login = true; //Cambia a la variable a true para no repetir el login

                    do {
                        int resp2 = 0;
                        error = true;
                        while (error) {
                            try {
                                MostrarMenuProf();
                                System.out.println("\t \t \t ¿Que opcion desea escoger?");
                                stringToNumber = entrada.nextLine();
                                resp2 = Integer.parseInt(stringToNumber);
                                error = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: No introduzcas letras, intentalo de nuevo");
                            }
                            switch (resp2) {
                                case 1:
                                    this.ImpresionArregloAlumnos(alumnos);
                                    error = true;
                                    int ID = 0;
                                    if (this.impresion) {
                                        while (error) {
                                            while (error) {
                                                try {
                                                    System.out.println("Ingrese el Id del Alumno");
                                                    stringToNumber = entrada.nextLine();
                                                    ID = Integer.parseInt(stringToNumber);
                                                    error = false;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Error: Solo escribe numeros...");
                                                }
                                            }

                                            if (ID > (alumnos.size() - 1)) {
                                                System.out.println("Error: No existe el id...");
                                                error = true;
                                            } else {
                                                entrada.nextLine();
                                                System.out.println("¿El Alumno asistio hoy?");
                                                String asis = entrada.nextLine();
                                                if (asis.equals("si") || asis.equals("Si")) {
                                                    alumnos.get(ID).setAsistencia(true);
                                                } else {
                                                    alumnos.get(ID).setAsistencia(false);
                                                }
                                                error = false;
                                            }
                                        }


                                    }
                                    break;
                                case 2:
                                    this.ImpresionArregloAlumnos(alumnos);
                                    break;
                                case 3:
                                    this.ImpresionArregloAlumnos(alumnos);
                                    error = true;
                                    if (this.impresion) {
                                        double prom = 0;
                                        while (error) {
                                            while (error) {
                                                try {
                                                    System.out.println("Ingrese el ID");
                                                    stringToNumber = entrada.nextLine();
                                                    idElegido = Integer.parseInt(stringToNumber);
                                                    System.out.println("Escribe el prommedio");
                                                    stringToNumber = entrada.nextLine();
                                                    prom = Double.parseDouble(stringToNumber);
                                                    error = false;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Error: Solo numeros...");
                                                }
                                            }
                                            if (idElegido > (alumnos.size() - 1)) {
                                                System.out.println("Error: No existe el id...");
                                                error = true;
                                            } else {
                                                alumnos.get(idElegido).setPromedio(prom);
                                                error = false;
                                            }

                                        }
                                    }
                                    break;
                                case 4:
                                    menu = true;
                                    login = false;
                                    break;
                            }
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
        } catch (NullPointerException e) {
            System.out.println("Saliendo del sistema...");
            System.exit(0);
        }

    }

    private void ImpresionArregloAlumnos(ArrayList<Estudiante> nombreArreglo) {
        if (!nombreArreglo.isEmpty()) {
            System.out.println("-----------------MOSTRANDO TODOS LOS ALUMNOS----------------------");
            for (int i = 0; i < nombreArreglo.size(); i++) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("ID: " + i + " \nNombre: " + nombreArreglo.get(i).getNombre()
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
        errorString = true; // Para que el ciclo Volver a iniciar en VERDADERO
        // Variables locales que se usaran para el metodo
        String nom2 = "ok";
        String hor2 = "ok";
        String jefe = "ok";
        String carre2 = "ok";

        System.out.println("\n" + "CREANDO PPROFESOR");
        entrada.nextLine();//limpiar el buffer

        while (errorString) {
            System.out.println("\n" + "Ingrese nombre del profesor:");
            nom2 = entrada.nextLine();
            m = noNumerosconLetras.matcher(nom2);
            if (!m.matches()) {
                errorString = true;
                System.out.println(" \n" + " CARACTERES INVALIDOS, VUELVE A INTENTARLO. (SOLO SE ADMITEN LETRAS) ");
            } else {
                errorString = false;
            }
        }
        System.out.println("\n" + "Ingrese Domicilio");
        String dom2 = entrada.nextLine();
        errorString = true;
        while (errorString) {
            System.out.println("\n" + "Ingrese turno del profesor:");
            hor2 = entrada.nextLine();
            m = noNumerosconLetras.matcher(hor2);
            if (!m.matches()) {
                errorString = true;
                System.out.println(" \n" + " CARACTERES INVALIDOS, VUELVE A INTENTARLO. (SOLO SE ADMITEN LETRAS) ");
            } else {
                errorString = false;
            }
        }
        errorString = true;
        while (errorString) {
            System.out.println("\n" + "Ingrese el jefe inmediato:");
            jefe = entrada.nextLine();
            m = noNumerosconLetras.matcher(jefe);
            if (!m.matches()) {
                errorString = true;
                System.out.println(" \n" + " CARACTERES INVALIDOS, VUELVE A INTENTARLO. (SOLO SE ADMITEN LETRAS) ");
            } else {
                errorString = false;
            }
        }
        errorString = true;
        while (errorString) {
            System.out.println("\n" + "Ingrese la materia que imparte seguida de la carrera (Ej. Calculo Integral - SISTEMAS)");
            carre2 = entrada.nextLine();
            m = noNumerosconLetras.matcher(carre2);
            if (!m.matches()) {
                errorString = true;
                System.out.println(" \n" + " CARACTERES INVALIDOS, VUELVE A INTENTARLO. (SOLO SE ADMITEN LETRAS) ");
            } else {
                errorString = false;
            }
        }
        profesores.add(new Profesor(nom2, hor2, dom2, jefe, carre2));
    }

    private void CrearAlumno() {
        errorString = true;//para que el ciclo vuelva iniciar en TRUE
        //VARIABLES LOCALES QUE SE USARAN PARA EL METODO
        String nom = "ok";
        String turn = "ok";
        String grup = "ok";
        int gra = 0;
        System.out.println("\n" + "CREANDO ALUMNO");

        while (errorString) {//entra a comprobar el nombre
            System.out.println("\n" + "Ingrese Nombre completo: ");
            nom = entrada.nextLine();
            m = noNumerosconLetras.matcher(nom);
            if (!m.matches()) {//el coincide tiene que ser falso porque eso quiere decir que no se encntrara ningun numero
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS.)");
            } else {
                errorString = false;
            }

        }
        errorString = true;//vuelve a poner la variable true para entrar al while
        while (errorString) {
            System.out.println("\n" + "Ingrese turno que le corresponde: ");
            turn = entrada.nextLine();
            m = noNumerosconLetras.matcher(turn);
            if (!m.matches()) {
                errorString = true;
                System.out.println("\n CARACTERES INVALIDOS, VUELVE A INTENTARLO.(SOLO SE ADMITEN LETRAS)");
            } else {
                errorString = false;
            }
        }

        System.out.println("\n" + "Ingrese Domicilio: ");
        String dom = entrada.nextLine();

        error = true;
        while (error) {
            try {
                System.out.println("\n" + "Ingrese semestre: " + "SOLO NUMEROS.");
                stringToNumber = entrada.nextLine();
                gra = Integer.parseInt(stringToNumber);
                error = false;
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce solo numeros...");
            }
        }

        errorString = true;
        entrada.nextLine();
        while (errorString) {
            System.out.println("\n" + "Ingrese Grupo: ");
            grup = entrada.nextLine();
            m = noNumerosconLetras.matcher(grup);
            if (!m.matches()) {
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
        System.out.println("3. E X P O R T A R (ALUMNOS)");
        System.out.println("4. E X P O R T A R (PROFESORES)");
        System.out.println("5. C E R R A R  S E S I O N");
        System.out.println("\t \t¿Que opcion desea escoger?");
    }

    private static void MostrarSubMenuAlumnos() {
        System.out.println("\n" + "1. C R E A R  A L U M N O");
        System.out.println("2. B O R R A R  A L U M N O");
        System.out.println("3. V E R  A L U M N O");
        System.out.println("4. R E G R E S A R");
        System.out.println("\t \t ¿Que pcion desea escoger?");
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

    public void guardarArregloAlumno(ArrayList<Estudiante> nombreArreglo, String n) throws IOException {
        {
            File f = new File(n);
            if (!f.exists()) f.createNewFile();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
            dos.writeUTF("LOS DATOS DEL ARCHIVO SON:\n");
            for (int i = 0; i < nombreArreglo.size(); i++) {
                dos.writeUTF("Dato: " + (i + 1) + "\n");
                dos.writeUTF("Nombre: " + nombreArreglo.get(i).getNombre());
                dos.writeUTF("\nGrupo: " + nombreArreglo.get(i).getGrupo());
                dos.writeUTF("\nPromedio: " + nombreArreglo.get(i).getPromedio());
                dos.writeUTF("\n-----------------------------------------------");
                dos.writeUTF("\n");
            }
        }
    }

    public void guardarArregloProfesor(ArrayList<Profesor> nombreArreglo, String n) throws IOException {
        {
            File f = new File(n);
            if (!f.exists()) f.createNewFile();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
            dos.writeUTF("LOS DATOS DEL ARCHIVO SON:\n");
            for (int i = 0; i < nombreArreglo.size(); i++) {
                dos.writeUTF("Dato: " + (i + 1) + "\n");
                dos.writeUTF("Nombre: " + nombreArreglo.get(i).getNombre());
                dos.writeUTF("\nGrupo: " + nombreArreglo.get(i).getGrupo());
                dos.writeUTF("\nPromedio: " + nombreArreglo.get(i).getCarrera());
                dos.writeUTF("\n-----------------------------------------------");
                dos.writeUTF("\n");
            }
        }
    }
}