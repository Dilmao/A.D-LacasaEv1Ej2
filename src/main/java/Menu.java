import database.EmfSingleton;
import jakarta.persistence.EntityManagerFactory;

public class Menu {
    public static void main(String[] args) {
        // Problema en la creacion de la base de datos
        boolean salir = false;
        String opcion;

        while (!salir) {
            System.out.println("*****************************************************************");
            System.out.println("0. Salir");
            System.out.println("1. Dar de alta una nueva obra");
            System.out.println("2. Cambiar la obra asignada a un empleado");
            System.out.println("*****************************************************************");
            opcion = libs.Leer.pedirCadena("Introduzca una opcion");

            switch (opcion) {
                case "0":
                    desconectar();
                    salir = true;
                    break;
                case "1":
                    code.ConsultarObras.darAltaObra();
                    break;
                case "2":   // TODO
                    code.ConsultarEmpleados.cambiarObra();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    private static void desconectar() {
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        emf.close();
    }
}
