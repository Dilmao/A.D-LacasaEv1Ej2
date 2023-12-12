package code;

import database.EmfSingleton;
import entities.ObraEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Date;

public class ConsultarObras {
    // TODO seguramente de problemas por el id de la obra
    public static void darAltaObra() {
        String nombre = ConsultarEmpleados.listarEmpleados("Introduzca el nombre del empleado responsable de la obra");
        String direccion = libs.Leer.pedirCadena("Introduzca la direccion donde se realizara la obra");
        Date fecha = libs.Leer.pedirDate("Introduzca la fecha de entrega");

        // Creacion de la obra
        try {
            EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            ObraEntity obra = new ObraEntity();
            obra.setNombre(nombre);
            obra.setDireccion(direccion);
            obra.setEntrega((java.sql.Date) fecha);

            em.persist(obra);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    //**************************************** Funciones para la pedida de datos ****************************************
    public static String listarObras(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String obra = "";
        boolean obraValida = false;

        try {
            Query listarObras = em.createQuery("from ObraEntity");
            ArrayList<ObraEntity> listaObras = (ArrayList<ObraEntity>) listarObras.getResultList();

            // Listar todas las obras
            System.out.println("******************************");
            for (ObraEntity o : listaObras) {
                System.out.println(o.getId() + ". " + o.getNombre());
            }
            System.out.println("******************************");
            obra = libs.Leer.pedirCadena(mensaje);

            // Comprobacion de que la obra introducida existe
            for (ObraEntity o : listaObras) {
                if (o.getNombre().toLowerCase().contains(obra.toLowerCase())) {
                    obraValida = true;
                }
            }

            if (!obraValida) {
                System.out.println("La obra introducida no existe");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }

        if (obraValida) {
            return obra;
        } else {
            return null;
        }
    }
}
