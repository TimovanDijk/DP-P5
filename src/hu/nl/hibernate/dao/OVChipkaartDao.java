package hu.nl.hibernate.dao;

import hu.nl.hibernate.OVChipkaart;
import hu.nl.hibernate.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

public class OVChipkaartDao {
    private static SessionFactory factory;

    public OVChipkaartDao() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    /* Method to CREATE an employee in the database */
    public Integer addOVChipkaart(OVChipkaart ov) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer kaartNummer = null;
        try {
            tx = session.beginTransaction();
            kaartNummer = (Integer) session.save(ov);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kaartNummer;
    }

    /* Method to  READ all the employees */
    public void listOVChipkaarten() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List chipkaarten = session.createQuery("FROM OVChipkaart").list();
            for (Iterator iterator = chipkaarten.iterator(); iterator.hasNext(); ) {
                OVChipkaart ovChipkaart = (OVChipkaart) iterator.next();
                System.out.print("Geldig tot: " + ovChipkaart.getGeldigTot());
                System.out.print("  Klasse: " + ovChipkaart.getKlasse());
                System.out.println("  Eigenaar: " + ovChipkaart.getEigenaar().getAchternaam());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an employee */
    public void updateOVchipkaart(Integer kaartNummer, Date geldigtot, int klasse, Reiziger eigenaar) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            OVChipkaart ovChipkaart = (OVChipkaart) session.get(OVChipkaart.class, kaartNummer);
            ovChipkaart.setGeldigTot(geldigtot);
            ovChipkaart.setKlasse(klasse);
            ovChipkaart.setEigenaar(eigenaar);
            session.update(ovChipkaart);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteOVChipkaart(Integer kaartNummer) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            OVChipkaart ovChipkaart = (OVChipkaart) session.get(OVChipkaart.class, kaartNummer);
            session.delete(ovChipkaart);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
