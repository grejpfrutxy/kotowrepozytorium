package pl.kobietydokodu.bazakotow.model;

import java.util.Date;

public class Kot {
	
	private String imie;
	private Date dataUrodzenia;
	private Float waga;
	private String imieOpiekuna;
	
	//metoda lub pole static (statyczne) jest w³aœnoœci¹ klasy, a nie konkretnego obiektu
	//metody static nie maj¹ dostêpu do pól konkretnych obiektów, a jedynie do innych pól i metod statycznych
	
	public String przedstawSie() {
		return "Mam na imiê "+this.imie+", urodzi³em siê "+this.dataUrodzenia+", wa¿ê "+this.waga+" kg, a moim opiekunem jest "+this.imieOpiekuna+".";
	}
	

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public Float getWaga() {
		return waga;
	}

	public void setWaga(Float waga) {
		this.waga = waga;
	}

	public String getImieOpiekuna() {
		return imieOpiekuna;
	}

	public void setImieOpiekuna(String imieOpiekuna) {
		this.imieOpiekuna = imieOpiekuna;
	}
	
	
	/*public static void main(String[] args) {
		Kot kot = new Kot();
		kot.setImie("Stefan");
		//kot.setDataUrodzenia(Date("2018-01-01");
		//kot.setWaga(10);
		kot.setImieOpiekuna("Joasia");
		System.out.println(kot.przedstawSie());
	}*/
}
