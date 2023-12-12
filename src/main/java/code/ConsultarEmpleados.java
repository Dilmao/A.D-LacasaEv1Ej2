package code;

import database.EmfSingleton;
import entities.EmpleadoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class ConsultarEmpleados {
    public static void cambiarObra() {
        String empleado = listarEmpleados("Introduzca el nombre del empleado");
        String obra;
        try {
            EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
            // Conseguir el empleado
            Query listarObras = em.createQuery("from EmpleadoEntity where nombre = ?1");
            listarObras.setParameter(1, empleado);
            ArrayList<EmpleadoEntity> listaEmpleados = (ArrayList<EmpleadoEntity>) listarObras.getResultList();

            obra = ConsultarObras.listarObras("Que obra quieres asignarle a " + empleado);
            for (EmpleadoEntity e : listaEmpleados) {
                e.setNombreObra(obra);
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    //**************************************** Funciones para la pedida de datos ****************************************
    public static String listarEmpleados(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String empleado = "";
        boolean empleadoValido = false;

        try {
            Query listarEmpleados = em.createQuery("from EmpleadoEntity");
            ArrayList<EmpleadoEntity> listaEmpleados = (ArrayList<EmpleadoEntity>) listarEmpleados.getResultList();

            // Listar todos los empleados
            System.out.println("******************************");
            for (EmpleadoEntity e : listaEmpleados) {
                System.out.println(e.getDni() + ". " + e.getNombre());
            }
            System.out.println("******************************");
            empleado = libs.Leer.pedirCadena(mensaje);

            // Comprobacion de que el empleado introducido existe
            for (EmpleadoEntity e : listaEmpleados) {
                if (e.getNombre().toLowerCase().contains(empleado.toLowerCase())) {
                    empleadoValido = true;
                }
            }

            if (!empleadoValido) {
                System.out.println("El empleado introducido no existe");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }

        if (empleadoValido) {
            return empleado;
        } else {
            return null;
        }
    }
}
