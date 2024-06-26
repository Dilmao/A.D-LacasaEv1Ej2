package code;

import database.EmfSingleton;
import entities.EmpleadoEntity;
import entities.ObraEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import libs.Leer;

import java.util.ArrayList;

public class ConsultarEmpleados {
    public static void cambiarObra() {
        EmpleadoEntity empleado = listarEmpleados("Introduzca el nombre del empleado ('0' para salir)");
        ObraEntity obra;
        try {
            EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            // Conseguir el empleado
            Query listarEmpleados = em.createQuery("from EmpleadoEntity where id = ?1");
            listarEmpleados.setParameter(1, empleado.getId());
            ArrayList<EmpleadoEntity> listaEmpleados = (ArrayList<EmpleadoEntity>) listarEmpleados.getResultList();

            obra = ConsultarObras.listarObras("Que obra quieres asignarle a " + empleado.getNombre() + " ('0' para salir)");
            for (EmpleadoEntity e : listaEmpleados) {
                e.setIdObra(obra.getId());
            }

            //
            transaction.begin();
            empleado.setIdObra(obra.getId());
            em.persist(empleado);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    //**************************************** Funciones para la pedida de datos ****************************************
    public static EmpleadoEntity listarEmpleados(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String nombreEmpleado = "";
        EmpleadoEntity empleado = null;

        try {
            // Se consigue la lista de todos los empleados en la base de datos.
            Query listarEmpleados = em.createQuery("from EmpleadoEntity");
            ArrayList<EmpleadoEntity> listaEmpleados = (ArrayList<EmpleadoEntity>) listarEmpleados.getResultList();

            // Se ejecuta el código hasta que el usuario introduzca lo pedido.
            while (empleado == null && !nombreEmpleado.equals("0")) {
                // Se listan todos los empleados y se pide que introduzca el nombre del empleado.
                System.out.println("******************************");
                for (EmpleadoEntity e : listaEmpleados) {
                    System.out.println(e.getDni() + ". " + e.getNombre());
                }
                System.out.println("******************************");
                nombreEmpleado = Leer.pedirCadena(mensaje);

                // Comprobacion de que el empleado introducido existe.
                for (EmpleadoEntity e : listaEmpleados) {
                    if (e.getNombre().equalsIgnoreCase(nombreEmpleado)) {
                        empleado = e;
                    }
                }

                if (empleado == null) {
                    System.out.println("El empleado introducido no existe");
                }
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }

        return empleado;
    }
}
