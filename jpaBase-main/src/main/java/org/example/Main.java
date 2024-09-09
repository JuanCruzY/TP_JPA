package org.example;

import org.example.Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Factura f1 = new Factura();
            f1.setNumero(12);
            f1.setFecha("10/08/2020");
            Domicilio d1 = Domicilio.builder().nombreCalle("Belgrano").numero(220).build();
            Cliente c1 = Cliente.builder().nombre("Juan Cruz").apellido("Yanardi").dni(45257784).build();
            c1.setDomicilio(d1);
            d1.setCliente(c1);
            f1.setCliente(c1);
            Categoria perecederos = Categoria.builder().denominacion("perecederos").build();
            Categoria lacteos = Categoria.builder().denominacion("lacteos").build();
            Categoria limpieza = Categoria.builder().denominacion("limpieza").build();
            Articulo a1 = Articulo.builder().cantidad(200).denominacion("yogurt").precio(20).build();
            Articulo a2 = Articulo.builder().cantidad(300).denominacion("detergente").precio(80).build();
            a1.getCategorias().add(perecederos);
            a1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(a1);
            perecederos.getArticulos().add(a1);
            a2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(a2);
            DetalleFactura det1 = new DetalleFactura();
            det1.setArticulo(a1);
            det1.setCantidad(2);
            det1.setSubtotal(40);
            a1.getDetalle().add(det1);
            f1.getDetalles().add(det1);
            det1.setFactura(f1);
            DetalleFactura det2 = new DetalleFactura();
            det2.setArticulo(a2);
            det2.setCantidad(1);
            det1.setSubtotal(80);
            a2.getDetalle().add(det2);
            f1.getDetalles().add(det2);
            det2.setFactura(f1);
            f1.setTotal(120);


            //Cliente c1 = Cliente.builder().nombre("Juan Cruz").apellido("Yanardi").dni(45257784).build();
            //Domicilio d1 = Domicilio.builder().nombreCalle("Belgrano").numero(220).build();
            //c1.setDomicilio(d1);
            //d1.setCliente(c1);

            entityManager.persist(f1);
            //Domicilio dom = entityManager.find(Domicilio.class, 1l);
            //Cliente cli = entityManager.find(Cliente.class, 1L);
            //System.out.println("Cliente de domicilio: "+ dom.getCliente().getDni());
            //System.out.println("Domicilio de cliente: "+ cli.getDomicilio().getNombreCalle());
            entityManager.flush();
            entityManager.getTransaction().commit();

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo persistir la factura");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
