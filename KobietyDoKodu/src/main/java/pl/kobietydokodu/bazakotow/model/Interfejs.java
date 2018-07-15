package pl.kobietydokodu.bazakotow.model;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

public class Interfejs {

	static Scanner sc = new Scanner(System.in);
	static KotDAO kotDao = new KotDAO();
	static String wybranaOpcjaMenu = null;

	
	public static void main(String[] args) {
			
		do {
		
			pokazMenu();
				
			if(wybranaOpcjaMenu.equals("1")) {
				dodajKota();
			}
			else if(wybranaOpcjaMenu.equals("2")) {
				wypiszKoty();
			}
			else if(wybranaOpcjaMenu.equals("x")) {
				System.out.println("##### Program zosta³ zamkniêty. #####");
			}
			
		}
		while(!wybranaOpcjaMenu.equalsIgnoreCase("x"));
			
	}

	
	
	public static String getUserInput() {
		return sc.nextLine().trim();
	}
	
	
	
	private static void pokazMenu() {
		
		String propozycjaOpcjiMenu;
		
		System.out.println("\n##### Menu (wybierz opcjê, aby przejœæ dalej): #####\n"
				+ "1 - dodaj kota\n"
				+ "2 - poka¿ koty\n"
				+ "x - zamknij program\n\n"
				+ "Wybieram: ");

		do {
			propozycjaOpcjiMenu = getUserInput();
			
			Pattern wzorOpcjiMenu = Pattern.compile("[12x]");
			Matcher opcjaMenuDoSprawdzenia = wzorOpcjiMenu.matcher(propozycjaOpcjiMenu);
			
			if(opcjaMenuDoSprawdzenia.matches()) {
				wybranaOpcjaMenu = propozycjaOpcjiMenu;
			}
			else {
				System.out.println("Podaj 1, 2 lub x!");
			}
		}
		while(wybranaOpcjaMenu==null);
		
	}
	
	
	
	private static void dodajKota( ) {
		
		Kot kot = new Kot();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		String propozycjaDaty;
		String propozycjaWagi;
		
		System.out.println("##### Dodawanie kota #####");
		System.out.println("Podaj imiê kota: ");
		kot.setImie(getUserInput());

		System.out.println("Podaj imiê opiekuna kota: ");
		kot.setImieOpiekuna(getUserInput());
		
		//odczyt daty urodzin kota
		do {
			System.out.println("Podaj datê urodzenia kota w formacie 'RRRR-MM-DD' (miêdzy 2000-01-01 a 2999-12-31): ");
			
			try {
				
				propozycjaDaty = getUserInput();
				
				Pattern wzorDaty = Pattern.compile("[2][0-9]{3}-(([0][1-9])|([1][0-2]))-(([0][1-9])|([12][0-9])|([3][0-1]))");
				Matcher dataDoSprawdzenia = wzorDaty.matcher(propozycjaDaty);
				
				if(dataDoSprawdzenia.matches()) {
					kot.setDataUrodzenia(sdf.parse(propozycjaDaty));
				}
			}
			catch(PatternSyntaxException pse) {
				System.out.println("B³¹d w wyra¿eniu regularnym.");
			}
			catch(ParseException pe) {
				System.out.println("To co wpisa³eœ nie jest dat¹!");
			}
		}
		while(kot.getDataUrodzenia()==null);
		
		
		//odczyt wagi kota
		do {
			System.out.println("Podaj wagê kota jako liczbê zmiennoprzecinkow¹ (miêdzy 0.01 a 29.99): ");
			
			try {
				
				propozycjaWagi = getUserInput();
				
				Pattern wzorWagi = Pattern.compile("[12]?[0-9]([\\.][0-9][0-9]?)?");
				Matcher wagaDoSprawdzenia = wzorWagi.matcher(propozycjaWagi);
				
				if(wagaDoSprawdzenia.matches()) {
					kot.setWaga(Float.parseFloat(propozycjaWagi));
				}
			}
			catch(NumberFormatException ne){
				System.out.println("To co wpisa³eœ nie jest liczb¹ zmiennoprzecinkow¹!");
			}
		}
		while(kot.getWaga()==null);
				
		
		System.out.println("Dziêkujê!");
		
		if(kotDao.dodajKota(kot)) {
			System.out.println("Dodano kota "+kot.getImie()+" do listy!");
		}
		else {
			System.out.println("Nie uda³o siê dodaæ kota "+kot.getImie()+" do listy!");
		}
	
	}
	
	
	
	private static void wypiszKoty() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		String propozycjaWybranegoNrKota;
		Integer nrWybranegoKota = null;
		
		System.out.println("##### Lista kotów #####");
		
		if(kotDao.getKoty().isEmpty()) {
			System.out.println("Nie ma ¿adnego kota na liœcie.\n");
		}
		else {
			System.out.println("Wybierz numer kota z listy:");
			
			for(int i=0; i<kotDao.getKoty().size(); i++) {
				Kot kot = kotDao.getKoty().get(i);
				System.out.println(i+" - "+kot.getImie());
			}
			
				
			do {
			
				try {
					propozycjaWybranegoNrKota = getUserInput();
					
					Pattern wzorNrKota = Pattern.compile("[0-9]+");
					Matcher nrKotaDoSprawdzenia = wzorNrKota.matcher(propozycjaWybranegoNrKota);
					
					if(nrKotaDoSprawdzenia.matches()) {
						nrWybranegoKota = Integer.parseInt(propozycjaWybranegoNrKota);
					}
					else {
						System.out.println("Podaj numer kota z listy!");
					}
				}
				catch(PatternSyntaxException pse){
					System.out.println("B³¹d w wyra¿eniu regularnym!");
				}	
				catch(NumberFormatException ne) {
					System.out.println("To co wpisa³eœ nie jest liczb¹!");
				}
			}
			while(nrWybranegoKota==null);
			
			
			if(nrWybranegoKota>=kotDao.getKoty().size()) {
				System.out.println("Kota o takim numerze nie ma na liœcie.");
			}
			else {
				Kot wybranyKot = kotDao.getKoty().get(nrWybranegoKota);
				String dataUrodzeniaString = sdf.format(wybranyKot.getDataUrodzenia());					
				System.out.println("Szczegó³owe dane o kocie:\nimiê: "+wybranyKot.getImie()+"\nimiê opiekuna: "+wybranyKot.getImieOpiekuna()+"\ndata urodzenia: "+dataUrodzeniaString+"\nwaga: "+wybranyKot.getWaga());
			}
			
		
		}
		
	}
	
	
}

/*
 * KOLEKCJE - grupy klas do przechowywania zbiorów elementów
 * Wszystkie kolekcje rozszerzaj¹ klasê Collection<E>
 * 
 * Rodzaje kolekcji:
 * java.util.List — lista, tj. kolekcja o okreœlonej pozycji (nie mylmy z sortowaniem!). Mo¿emy odwo³aæ siê do elementu po numerze kolejnym, 
 * 		np. ‘podaj element na pozycji 1’. Ten sam obiekt mo¿e wystêpowaæ na kilku pozycjach.
 * java.util.Set — zbiór, tj. kolekcja, która przechowuje obiekty bez okreœlenia pozycji (ale mo¿e przechowywaæ je w sposób uporz¹dkowany 
 * 		— tj. posortowany). Ten sam obiekt mo¿e wystêpowaæ tylko raz w danej kolekcji.
 * java.util.Queue — kolejka, czyli lista umo¿liwiaj¹ca implementacjê kolejek FIFO i FILO. Kolejki dzia³aj¹ analogicznie jak w sklepie, 
 * 		elementy dodawane trafiaj¹ na koniec kolejki, mo¿emy najpierw ‘obs³u¿yæ’ osobê z pocz¹tku kolejki (FIFO) lub z jej koñca (FILO).
 * java.util.Map — mapa, nie jest to stricte kolekcja, ale jako tak¹ bêdziemy j¹ traktowaæ. Mapa przechowuje mapowania klucz-wartoœæ, 
 * 		przy czym klucz musi byæ unikalny. Mo¿na o niej myœleæ jak o indeksie czy spisie treœci (spis treœci mapuje nazwê rozdzia³u na rozdzia³, 
 * 		gdzie nazwa rozdzia³u jest kluczem a sam rozdzia³ (jego treœæ) wartoœci¹).
 * 
 * 
 * 
 * 
 * 
 */
