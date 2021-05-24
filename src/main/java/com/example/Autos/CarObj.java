package com.example.Autos;

public class CarObj
{

    public String Marke;
    public String Modell;
    public String Hubraum;
    public int PS;
    public int NM;



    public CarObj(String marke, String modell, String hubraum, int ps, int nm)
    {
        
        Marke = marke;
        Modell = modell;
        Hubraum = hubraum;
        PS = ps;
        NM = nm;
    }
   
	public String getMarke()
    {
        return Marke;
    }
    public void setMarke(String marke)
    {
        Marke = marke;
    }
    public String getModell()
    {
        return Modell;
    }
    public void setModell(String modell)
    {
        Modell = modell;
    }
    public String getHubraum()
    {
        return Hubraum;
    }
    public void setHubraum(String hubraum)
    {
        Hubraum = hubraum;
    }
    public int getPS()
    {
        return PS;
    }
    public void setPS(int ps)
    {
        PS = ps;
    }
    public int getNM()
    {
        return NM;
    }
    public void setNM(int nm)
    {
        NM = nm;
    }
}