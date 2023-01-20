package Esercizio_Settimanale;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import java.util.Random;

public class Main {
	
	private static final String ENCODING = "utf-8";
	
	static String fileName = "archivio.txt";
	static File fileInfo = new File(fileName);
	
	static Scanner in = new Scanner(System.in);
	static ArrayList<Libro> libri = new ArrayList<>();
	static ArrayList<Rivista> riviste = new ArrayList<>();
  
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> archivio = new ArrayList<String>(FileUtils.readLines(fileInfo, ENCODING));
		boolean continuazioneProgramma = true;
		
		do {
			  System.out.println( "--- Benvenuto nell'archivio della biblioteca ---" );  
			  System.out.println( "0 - Per uscire dall'archivio" );
			  System.out.println( "1 - Aggiungi un elemento" );
			  System.out.println( "2 - Rimuovi elemento tramite codice ISBN" );
			  System.out.println( "3 - Cerca elemento tramite codice ISBN" );
			  System.out.println( "4 - Cerca elemento per anno" );
			  System.out.println( "5 - Cerca elemento per autore" );
			  System.out.println( "6 - Mostra archivio" );
			  System.out.println( "--------------------------------------------------" );
			  System.out.println( "- Seleziona una funzione: " );
			  try {
				  int selezione = Integer.parseInt(in.nextLine());
				  System.out.println( "--------------------------------------------------" );
				  switch(selezione) {
				  	case (1):
				  		System.out.println( "1 - Aggiungi libro" );
				  		System.out.println( "2 - Aggiungi rivista" );
				  		System.out.println( "- Inserisci tipo: " );  
				  		int selezioneTipo = Integer.parseInt(in.nextLine());
				  		if( selezioneTipo == 1 ) {
				  			aggiungiLibro();
				  		} else if (selezioneTipo == 2) {
				  			aggiungiRivista();
				  		} else {
				  			System.out.println( "- Il tipo selezionato non esiste!" );
				  		}
				  		break;
				  	case (2):
				  		System.out.println( "- Digita il codice ISBN del testo da rimuovere:" );
				  	 	long isbnRimuovi = Long.parseLong(in.nextLine());
				  	 	archivio.removeIf(linea -> linea.contains(Long.toString(isbnRimuovi)));
			  			FileUtils.writeLines(fileInfo, archivio);
				  		break;
				  	case (3):
				  		System.out.println( "- Digita il codice ISBN del testo da cercare:" );
			  	 		long cercaIsbn = Long.parseLong(in.nextLine());
			  	 		archivio.stream()
			  	 			.filter((b) -> b.contains(Long.toString(cercaIsbn)))
			  	 			.forEach((o) -> System.out.println(o));
				  		break;
				  	case (4):
				  		System.out.println( "- Digita l'anno del testo da cercare:" );
		  	 			int cercaAnno = Integer.parseInt(in.nextLine());
		  	 			archivio.stream()
		  	 				.filter((b) -> b.contains(Integer.toString(cercaAnno)))
		  	 				.forEach((o) -> System.out.println(o));
				  		break;
				  	case (5):
				  		System.out.println( "- Digita l'autore del testo da cercare:" );
	  	 				String cercaAutore = in.nextLine();
	  	 				archivio.stream()
	  	 					.filter((b) -> b.contains(cercaAutore))
	  	 					.forEach((o) -> System.out.println(o));
				  		break;
				  	case (6):
				  		leggiArchivio(fileInfo);
				  		break;
				  	case(0):
				  		System.out.println( "Hai lasciato l'archivio" );
				  		System.exit(0);
				  	default:
				  		System.out.println( "Istruzione non valida!" );
				  		break;
				  }
			  } catch(NumberFormatException e) {
				  System.out.println( "Erorr: hai inserito un valore errato!!" );
			  }
			  
			  System.out.println("Vuoi rimanere nell'archivio o usicre? (S/N)");
			  String input = in.nextLine();
			  continuazioneProgramma = input.equalsIgnoreCase("S");
			    
		} while(continuazioneProgramma);

	}
	
	//-----------------------------------------------------------------------------
	
	public static void aggiungiLibro() {
		System.out.println( "- Inserisci titolo: " );
		String titolo = in.nextLine();
  		System.out.println( "- Inserisci anno di pubblicazione: " );
  		int anno = Integer.parseInt(in.nextLine());
  		System.out.println( "- Inserisci numero pagine: " );
  		int nPagine = Integer.parseInt(in.nextLine());
  		System.out.println( "- Inserisci autore: " );
  		String autore = in.nextLine();
  		System.out.println( "- Inserisci genere: " );
  		String genere = in.nextLine();
  		
  		Libro libro = new Libro(generatoreIsbn(), titolo, anno, nPagine, autore, genere);
  		
  		libri.add(libro);
  		
  		String libroAgg = libro.toString();
  		try {
  			scriviInArchivio(fileInfo, libroAgg + System.lineSeparator(), true);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
  		
  		System.out.println( "Libro aggiunto correttamente!" );
  		
	}
	
	//-----------------------------------------------------------------------------
	
	public static void aggiungiRivista() {
		System.out.println( "Inserisci titolo: " );
		String titolo = in.nextLine();
		System.out.println( "Inserisci anno di pubblicazione: " );
		int anno = Integer.parseInt(in.nextLine());
		System.out.println( "Inserisci numero pagine: " );
		int nPagine = Integer.parseInt(in.nextLine());
		System.out.println( "1 - settimanale" );
		System.out.println( "2 - mensile" );
		System.out.println( "3 - semestrale" );
		System.out.println( "Inserisci periodicità: " );
		int selezione = Integer.parseInt(in.nextLine());
		Periodicita periodicita = null;
		switch(selezione) {
			case (1):
				periodicita =  Periodicita.SETTIMANALE;
				break;
			case (2):
				periodicita =  Periodicita.MENSILE;
				break;
			case (3):
				periodicita =  Periodicita.SEMESTRALE;
				break;
		}
				
		Rivista rivista = new Rivista(generatoreIsbn(), titolo, anno, nPagine, periodicita);
		
		riviste.add(rivista);
		String rivistaAgg = rivista.toString();
		try {
			scriviInArchivio(fileInfo, rivistaAgg + System.lineSeparator(), true);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.println( "Rivista aggiunto correttamente!" );
	}
	
	//-----------------------------------------------------------------------------
	
	public static long generatoreIsbn() {
		HashSet<Long> setIsbn = new HashSet<>();
		Random numR = new Random();
		long inizioCodice = 9000000000000L;
		long fineCodice = 9999999999999L;
		
        long nuovoIsbn = 0;
        
        do {
            nuovoIsbn = inizioCodice + (long)(numR.nextDouble()*(fineCodice - inizioCodice));
        } while (setIsbn.contains(nuovoIsbn));
        
        setIsbn.add(nuovoIsbn);
        return nuovoIsbn;
	}

	//-----------------------------------------------------------------------------
	
	public static void scriviInArchivio(File f, String s, boolean append) throws IOException {
		FileUtils.writeStringToFile(f, s, ENCODING, append);
	}
	
	//-----------------------------------------------------------------------------
	
	public static void leggiArchivio(File f) throws IOException {
		System.out.printf( "L'archivio della biblioteca contiente i seguenti testi: %n" );
		System.out.println( FileUtils.readFileToString(f, ENCODING) );
	}

}
