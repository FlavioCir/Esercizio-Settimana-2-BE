package Esercizio_Settimanale;

public class Rivista extends Biblioteca {

	Periodicita periodicita;

	public Rivista(long codiceIsbn, String titolo, int annoPubblicazione, int nPagine, Periodicita periodicita) {
		super(codiceIsbn, titolo, annoPubblicazione, nPagine);
		
		this.periodicita = periodicita;
	}	
	
	@Override
	public String toString() {
		return "Rivista -" + " Codice ISBN: " + this.codiceIsbn + ", Titolo: " + this.titolo + ", Anno: " + this.annoPubblicazione + ", Pagine: " + this.nPagine
        + ", Periodicità: " + this.periodicita;
	}
	
}
