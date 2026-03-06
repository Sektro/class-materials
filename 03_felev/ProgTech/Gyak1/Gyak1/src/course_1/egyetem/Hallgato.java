package course_1.egyetem;


public class Hallgato {

    private String nev;
    private String nemzetiseg;
    private float atlag;

    public Hallgato(String nev, String nemzetiseg, float atlag)
    {
        this.nev = nev;
        this.nemzetiseg = nemzetiseg;
        this.atlag = atlag;
    }

    public String getNev()
    {
        return nev;
    }
    public void setNev(String nev)
    {
        this.nev = nev;
    }

    public String getNemzetiseg()
    {
        return nemzetiseg;
    }
    public void setNemzetiseg(String nev)
    {
        this.nemzetiseg = nemzetiseg;
    }

    public float getAtlag()
    {
        return atlag;
    }
    public void setNev(float atlag)
    {
        this.atlag = atlag;
    }
}
