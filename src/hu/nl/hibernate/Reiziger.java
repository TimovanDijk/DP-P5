package hu.nl.hibernate;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="Reiziger")
public class Reiziger {
    @Column(name="voorletters")
    private String voorletters;
    @Column(name="tussenvoegsel")
    private String tussenvoegsel;
    @Column(name="achternaam")
    private String achternaam;
    @Column(name="geboortedatum")
    private Date gbdatum;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_REIZIGERID")
    private int reizigerid;
    @OneToMany(mappedBy="eigenaar")
    private Collection<OVChipkaart> chipkaarten = new ArrayList<OVChipkaart>();

    public Reiziger() {
    }

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date gbdatum, int reizigerid) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbdatum = gbdatum;
        this.reizigerid = reizigerid;
    }

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date gbdatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbdatum = gbdatum;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setRid(int reizigerid) {
        this.reizigerid = reizigerid;
    }

    public int getRid() {
        return reizigerid;
    }

    public Collection<OVChipkaart> getChipkaarten() {
        return chipkaarten;
    }

    public boolean voegOVToe(OVChipkaart kaart) {
        boolean gelukt = false;
        if (!chipkaarten.contains(kaart)) {
            chipkaarten.add(kaart);
        }
        return gelukt;
    }

    public boolean removeOV(OVChipkaart kaart) {
        boolean gelukt = false;
        if (chipkaarten.contains(kaart)) {
            chipkaarten.remove(kaart);
            gelukt = true;
        }
        return gelukt;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGbdatum() {
        return gbdatum;
    }

    public void setGbdatum(Date gbdatum) {
        this.gbdatum = gbdatum;
    }

    @Override
    public String toString() {
        return "Naam: " + voorletters + " " + tussenvoegsel + " " + achternaam + " GBdatum: " + gbdatum;
    }
}
