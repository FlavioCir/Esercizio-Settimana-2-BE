package Esercizio_Settimanale;

public class Libro extends Biblioteca {

	String autore;
	String genere;
	
	public Libro(long codiceIsbn, String titolo, int annoPubblicazione, int nPagine, String autore, String genere) {
		super(codiceIsbn, titolo, annoPubblicazione, nPagine);
		
		this.autore = autore;
		this.genere = genere;
	}
	
	
	
	@Override
	public String toString() {
		return "Libro -" + " Codice ISBN: " + this.codiceIsbn + ", Titolo: " + this.titolo + ", Anno: " + this.annoPubblicazione + ", Pagine: " + this.nPagine
                + ", Autore: " + this.autore + ", Genere: " + this.genere;
	}
	
}
