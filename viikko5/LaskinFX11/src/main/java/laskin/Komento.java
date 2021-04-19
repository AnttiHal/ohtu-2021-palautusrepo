package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Sovelluslogiikka sovellus;
    protected Button nollaa;
    protected Button undo;
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;       
        this.nollaa = nollaa;
        this.undo = undo;
       
    }

    

    public abstract void suorita();
    public abstract void peru();
}
