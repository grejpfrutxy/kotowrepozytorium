package pl.kobietydokodu.bazakotow.model;

import java.util.ArrayList;
import java.util.List;

public class KotDAO {
	
	public List<Kot> koty = new ArrayList<Kot>();
	
	
	public boolean dodajKota(Kot kot) {
		return koty.add(kot);
	}
	
	
	public List<Kot> getKoty() {
		return koty;
	}

}
