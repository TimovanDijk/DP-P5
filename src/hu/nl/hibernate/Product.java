package hu.nl.hibernate;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String productNaam;
    private String beschrijving;
    private float prijs;
    private ArrayList<String> kaarten = new ArrayList<String>();

    public Product(int productNummer, String productNaam, String beschrijving, float prijs) {
        this.productNummer = productNummer;
        this.productNaam = productNaam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public ArrayList<String> getKaarten() {
        return kaarten;
    }

    public void voegKaarttoe(String ovnum) {
        kaarten.add(ovnum);
    }

    public void  verwijderKaart(String ovnum){
        kaarten.remove(ovnum);
    }


    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getProductNaam() {
        return productNaam;
    }

    public void setProductNaam(String productNaam) {
        this.productNaam = productNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return productNummer + " met naam: " + productNaam + " en beschrijving: " + beschrijving + " heeft prijs: " + prijs;
    }
}
