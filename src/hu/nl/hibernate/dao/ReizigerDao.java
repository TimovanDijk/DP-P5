package hu.nl.hibernate.dao;

import com.sun.org.apache.regexp.internal.RE;
import hu.nl.hibernate.OVChipkaart;
import hu.nl.hibernate.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ReizigerDao {
    private static SessionFactory factory;
    private OVChipkaartDao ovDao = new OVChipkaartDao();

    public ReizigerDao() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    /* Method to CREATE an eigenaar in the database */
    public Integer addEigenaar(Reiziger eigenaar) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer kaartNummer = null;

        try {
            tx = session.beginTransaction();
            kaartNummer = (Integer) session.save(eigenaar);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return kaartNummer;
    }

    /* Method to  READ all the eigenaren */
    public void listReizigers() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Reiziger").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Reiziger reiziger = (Reiziger) iterator.next();
                System.out.print("Voornaam: " + reiziger.getVoorletters());
                System.out.print("  tussenvoegsel: " + reiziger.getTussenvoegsel());
                System.out.println("  achternaam: " + reiziger.getAchternaam());
                System.out.println("  geboortedatum: " + reiziger.getGbdatum());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE eigenaar */
    public void updateEigenaar(String voornaam, String tussenvoegsel, String achternaam, Date geboortedatum, int reizigerId) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reiziger reiziger = (Reiziger) session.get(Reiziger.class, reizigerId);
            reiziger.setVoorletters(voornaam);
            reiziger.setTussenvoegsel(tussenvoegsel);
            reiziger.setAchternaam(achternaam);
            reiziger.setGbdatum(geboortedatum);
            session.update(reiziger);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteReiziger(Integer reizigerid) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reiziger reiziger = (Reiziger) session.get(Reiziger.class, reizigerid);
            for (OVChipkaart c: reiziger.getChipkaarten()) {
                ovDao.deleteOVChipkaart(c.getKaartNum());
            }
            session.delete(reiziger);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
