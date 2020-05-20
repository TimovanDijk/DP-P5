package hu.nl.hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;

@Entity
@Table(name = "OV_Chipkaart")
public class OVChipkaart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_OVID")
    private int kaartnummer;
    @Column(name = "geldigtot")
    private Date geldigTot;

    @Column(name = "klasse")
    private int klasse;

    private float saldo;
    @ManyToOne
    @JoinColumn(name="reizigerid", nullable=false)
    private Reiziger eigenaar;


    public OVChipkaart() {
    }

    public OVChipkaart(Date geldigTot, int klasse, Reiziger eigenaar) {
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.eigenaar = eigenaar;
    }


    public int getKaartNum() {
        return kaartnummer;
    }

    public void setKaartNum(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Reiziger getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Reiziger eigenaar) {
        this.eigenaar = eigenaar;
        eigenaar.voegOVToe(this);

    }

    @Override
    public String toString() {
        return "kaartnummer: " + kaartnummer + " geldig tot: " + geldigTot + " saldo: " + saldo + " klasse: " + klasse + " eigenaar: " + eigenaar;
    }
}
