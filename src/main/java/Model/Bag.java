package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bag {

	private ArrayList<Color> Students;



	public Bag() {
		Students= new ArrayList<Color>();
		for(int i=0;i<26;i++){
			Students.add(Color.GREEN);
		}
		for(int i=0;i<26;i++){
			Students.add(Color.BLUE);
		}
		for(int i=0;i<26;i++){
			Students.add(Color.YELLOW);
		}
		for(int i=0;i<26;i++){
			Students.add(Color.PINK);
		}
		for(int i=0;i<26;i++){
			Students.add(Color.RED);
		}
		Collections.shuffle(Students);
	}


	public Color getFirstElement(){
		return Students.get(0);
	}
	public void RemoveFirstElement(){
		Students.remove(0);
	}

	public Color FillClouds() {
		Color StudentColor=getFirstElement();
		RemoveFirstElement();
		return StudentColor;
	}

	public Color FillIsland() {
		Color StudentColor=getFirstElement();
		RemoveFirstElement();
		return StudentColor;

	}

	public void ShuffleBag() {
		Collections.shuffle(Students);
	}


}
