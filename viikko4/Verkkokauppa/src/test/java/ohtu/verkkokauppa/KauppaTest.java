package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
    k = new Kauppa(varasto, pankki, viite); 
}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        
        when(viite.uusi()).thenReturn(42);

        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
       
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        
        when(viite.uusi()).thenReturn(42);

        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
            
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKahdenEriOstoksenJalkeen() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);    
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(9));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKahdenSamanOstoksenJalkeen() {

        when(viite.uusi()).thenReturn(42);

 
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));
             

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.lisaaKoriin(2);    
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(8));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunTuoteOnLoppu() {
 
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));


        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);    
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void AloitaAsiointiMetodinKutsuNollaaEdellisetOstokset() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(4));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void KauppaPyytaaUudenViiteNumeronJokaiselleMaksutapahtumalle() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));
        when(viite.uusi()).thenReturn(43);  
        k.aloitaAsiointi();
        k.lisaaKoriin(2); 
        
        k.tilimaksu("antti", "1234");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        
         
        verify(pankki).tilisiirto(eq("antti"), eq(43), eq("1234"), eq("33333-44455"),eq(4));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void OstoskoristaPoistaminenToimiiOoikein() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));

        // tehdään ostokset
        k.aloitaAsiointi();
        
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2); 
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(4));
        
    }
}