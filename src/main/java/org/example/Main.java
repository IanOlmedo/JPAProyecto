package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            //
            em.getTransaction().begin();

            Categoria dulces = new Categoria("Dulces");

            Categoria lacteos = new Categoria("Lacteos");

            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(3,"Edulcorante",1850);

            Articulo art2 = new Articulo(4,"Trapo de pizo",3600);

            art1.getCategorias().add(dulces);
            art1.getCategorias().add(lacteos);

            lacteos.getArticulos().add(art1);
            dulces.getArticulos().add(art1);
            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);


            Factura fac1 = new Factura("14/10/2024", 35);

            Cliente cliente = new Cliente("Ian", "Sanchez", 44058201);
            Domicilio dom1 = new Domicilio("Lavalle",517);

            cliente.setDomicilio(dom1);

            fac1.setCliente(cliente);

            DetalleFactura det1 = new DetalleFactura();

            det1.setArticulo(art1);
            det1.setCantidad(6);
            det1.setSubtotal(11100);

            fac1.getFacturas().add(det1);

            DetalleFactura det2 = new DetalleFactura();

            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(50);

            fac1.getFacturas().add(det2);

            em.persist(fac1);


            em.flush();

            em.getTransaction().commit();
        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}
        
        em.close();
        entityManagerFactory.close();
    }
}
