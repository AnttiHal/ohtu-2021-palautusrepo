
package ohtu.intjoukkosovellus;

public class IntJoukko {

    
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luoTaulukko(5, 5);        
    }

    public IntJoukko(int koko) {
        luoTaulukko(koko, 5);
    }
    
    
    public IntJoukko(int koko, int kasvatuskoko) {        
        luoTaulukko(koko, kasvatuskoko);
    }

    

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            taulukko[0] = luku;
            alkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % taulukko.length == 0) {
                int[] taulukkoOld = new int[taulukko.length];
                taulukkoOld = taulukko;
                kopioiTaulukko(taulukko, taulukkoOld);
                taulukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, taulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        boolean found = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                found=true;
            }
        }
        return found;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                taulukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = taulukko[j];
                taulukko[j] = taulukko[j + 1];
                taulukko[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    private void luoTaulukko(int koko, int kasvatus) {
        if (koko < 0) {
            throw new IndexOutOfBoundsException("koko ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatus ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        taulukko = new int[koko];
        
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatus;
    }

    

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + taulukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += taulukko[i];
                tuotos += ", ";
            }
            tuotos += taulukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }
   
    

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
