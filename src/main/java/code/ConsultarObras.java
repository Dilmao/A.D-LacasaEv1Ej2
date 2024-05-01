package code;

import database.EmfSingleton;
import entities.EmpleadoEntity;
import entities.ObraEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Date;

public class ConsultarObras {
    public static void darAltaObra() {
        EmpleadoEntity empleado = ConsultarEmpleados.listarEmpleados("Introduzca el nombre del empleado responsable de la obra ('0' para salir)");
        String direccion = libs.Leer.pedirCadena("Introduzca la direccion donde se realizara la obra");
        Date fecha = libs.Leer.pedirDate("Introduzca la fecha de entrega");

        // Creacion de la obra
        try {
            EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            ObraEntity obra = new ObraEntity();
            obra.setNombre(empleado.getNombre());
            obra.setDireccion(direccion);
            obra.setEntrega((java.sql.Date) fecha);

            em.persist(obra);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    //**************************************** Funciones para la pedida de datos ****************************************
    public static ObraEntity listarObras(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String nombreObra = "";
        ObraEntity obra = null;

        try {
            // Se consigue la lista de todas las obras en la base de datos.
            Query listarObras = em.createQuery("from ObraEntity");
            ArrayList<ObraEntity> listaObras = (ArrayList<ObraEntity>) listarObras.getResultList();

            // Se ejecuta el código hasta que el usuario introduzca lo pedido.
            while (obra == null || nombreObra != "0") {
                // Se listan todas las obras y se pide que introduzca el nombre de la obra.
                System.out.println("******************************");
                for (ObraEntity o : listaObras) {
                    System.out.println(o.getId() + ". " + o.getNombre());
                }
                System.out.println("******************************");
                nombreObra = libs.Leer.pedirCadena(mensaje);

                // Comprobacion de que la obra introducida existe
                for (ObraEntity o : listaObras) {
                    if (o.getNombre().equalsIgnoreCase(nombreObra)) {
                        obra = o;
                    }
                }

                // Si la obra introducia no existe, se da un menaje de error.
                if (obra == null) {
                    System.out.println("La obra introducida no existe");
                }
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }


        return obra;
    }
}
