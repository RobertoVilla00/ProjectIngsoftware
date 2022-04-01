package It.polimi.ingsw.Model;


import java.util.ArrayList;
import java.util.Collections;


public class Bag {

	private ArrayList<Color> Students;



	public Bag(int dimesion) {
		Students= new ArrayList<Color>();
		for(int i=0;i<dimesion;i++){
			Students.add(Color.GREEN);
		}
		for(int i=0;i<dimesion;i++){
			Students.add(Color.BLUE);
		}
		for(int i=0;i<dimesion;i++){
			Students.add(Color.YELLOW);
		}
		for(int i=0;i<dimesion;i++){
			Students.add(Color.PINK);
		}
		for(int i=0;i<dimesion;i++){
			Students.add(Color.RED);
		}
		this.ShuffleBag();
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

	public Color FillCard() {
		Color StudentColor=getFirstElement();
		RemoveFirstElement();
		return StudentColor;

	}

	public void ShuffleBag() {
		Collections.shuffle(Students);
	}

	public void AddStudent(Color color){
		Students.add(color);
		this.ShuffleBag();
	}

}
