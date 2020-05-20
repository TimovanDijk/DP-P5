package hu.nl.hibernate;

import hu.nl.hibernate.dao.OVChipkaartDao;
import hu.nl.hibernate.dao.ReizigerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
        OVChipkaartDao ovDao = new OVChipkaartDao();
        ReizigerDao rDao = new ReizigerDao();
        Reiziger r1 = new Reiziger("T", "van", "Dijk", date);
        Reiziger r2 = new Reiziger("D", "van", "Toon", date);
        OVChipkaart o1 = new OVChipkaart();
        OVChipkaart o2 = new OVChipkaart(date, 2, r2);
        OVChipkaart o3 = new OVChipkaart();
        o3.setEigenaar(r1);
        o1.setEigenaar(r2);
        o1.setKlasse(6);
        o2.setEigenaar(r2);
        System.out.println(o3.getEigenaar());
        r1.voegOVToe(o1);
        r1.voegOVToe(o2);
        r2.voegOVToe(o3);
        int reizigerID = rDao.addEigenaar(r1);
        int reizigerID2 = rDao.addEigenaar(r2);
        int ovId1 = ovDao.addOVChipkaart(o1);
        Integer ovId2 = ovDao.addOVChipkaart(o2);
        Integer ovId3 = ovDao.addOVChipkaart(o3);
        ovDao.listOVChipkaarten();
        ovDao.updateOVchipkaart(ovId1, date, 2, r1);
        ovDao.deleteOVChipkaart(ovId2);
        System.out.println("Hieronder de ov met toon weggehaald en ov van Dijk met datum");
        ovDao.listOVChipkaarten();
        rDao.deleteReiziger(reizigerID);
        rDao.listReizigers();
    }

}
