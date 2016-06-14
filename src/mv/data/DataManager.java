package mv.data;

import saf.components.AppDataComponent;
import mv.MapViewerApp;

/**
 *
 * @author McKillaGorilla
 */
public class DataManager implements AppDataComponent {
    MapViewerApp app;
	Double[] x;
	Double[] y;
	double[] xCord;
	double[] yCord;

	public DataManager(MapViewerApp initApp) {
        app = initApp;
    }

	public Double[] getX() {
		return x;
	}

	public void setX(Double[] x) {
		this.x = x;
	}

	public Double[] getY() {
		return y;
	}

	public void setY(Double[] y) {
		this.y = y;
	}

	public void getXCord(){
		xCord = new double[x.length];
		for(int z = 0; z < x.length; z++){
			xCord[z] = x[z];
		}
	}

	public void getYCord(){
		yCord = new double[y.length];
		for(int z = 0; z < y.length; z++){
			yCord[z] = y[z];
		}
	}

    @Override
    public void reset() {

    }
}
