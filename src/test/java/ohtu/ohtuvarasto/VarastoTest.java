package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto kuormitettuVarasto;
    Varasto negatiivinenVarasto;
    Varasto ylitaytettyVarasto;
    Varasto negatiivinenYlitaysiVarasto;
    Varasto negatiivinenAlkuSaldo;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        kuormitettuVarasto = new Varasto(10,5);
        negatiivinenVarasto = new Varasto(-1);
        ylitaytettyVarasto = new Varasto(10,11);
        negatiivinenYlitaysiVarasto = new Varasto(-1, 4);
        negatiivinenAlkuSaldo = new Varasto(4, -1);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanKuormitetunVaraston() {
        assertEquals(5, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaKuormitetullaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaNegatiivisellaVarastollaOikeaTilavuus() {
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaYlitaydellaNegatiivisellaVarastollaOikeaTilavuus() {
        assertEquals(0, negatiivinenYlitaysiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaYlitaydellaNegatiivisellaVarastollaOikeaSaldo() {
        assertEquals(0, negatiivinenYlitaysiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaYlitaydellaVarastollaOikeaTilavuus() {
        assertEquals(10, ylitaytettyVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void ylitaytetynVarastonSaldoOikein() {
        assertEquals(10, ylitaytettyVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenAlkuSaldonVarastoSaldoOikein() {
        assertEquals(0, negatiivinenAlkuSaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoaIsompiLisays() {
        varasto.lisaaVarastoon(14);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-4), vertailuTarkkuus);
    }
    
    @Test
    public void liianSuuriOttoPalauttaaVainVarastonMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(15);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOnOikea() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }

}