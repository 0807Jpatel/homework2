package mv.data;

import java.util.ArrayList;

/**
 * Created by jappatel on 6/14/16.
 */
public class SubRegions {
	ArrayList<Double[]> x;
	ArrayList<Double[]> y;
	ArrayList<double[]> xCord;
	ArrayList<double[]> yCord;

	public ArrayList<Double[]> getX() {
		return x;
	}

	public void setX(ArrayList<Double[]> x) {
		this.x = x;
	}

	public ArrayList<Double[]> getY() {
		return y;
	}

	public void setY(ArrayList<Double[]> y) {
		this.y = y;
	}


	public ArrayList<double[]> getXCord(double width) {
		xCord = new ArrayList<>();
		for (int q = 0; q < x.size(); q++) {
			int c = x.get(q).length;
			double[] temp = new double[c];
			for(int i = 0; i < c; i++){
				temp[i] =  ((x.get(q)[i] + 180)/360) * width;
			}
			xCord.add(temp);
		}
		return xCord;
	}

	public ArrayList<double[]> getYCord(double height) {
		yCord = new ArrayList<>();
		for (int q = 0; q < y.size(); q++) {
			int c = y.get(q).length;
			double[] temp = new double[c];
			for(int i = 0; i < c; i++){
				temp[i] =  ((y.get(q)[i] * -1 + 90)/180) * height;
			}
			yCord.add(temp);
		}
		return yCord;
	}

}
