/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv.gui;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import mv.data.DataManager;
import mv.data.SubRegions;
import saf.components.AppWorkspaceComponent;
import mv.MapViewerApp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author McKillaGorilla
 */
public class Workspace extends AppWorkspaceComponent {
    private MapViewerApp app;

	private Canvas canvas;
	private GraphicsContext gc;
    
    public Workspace(MapViewerApp initApp) {
        app = initApp;
        workspace = new Pane();
	    super.activateWorkspace(app.getGUI().getAppPane());
	    initGUI();
    }

	private void initGUI(){
		removeButtons();
		Group group = new Group();
		canvas= new Canvas(2000, 1000);
		gc = canvas.getGraphicsContext2D();

		Affine affine = new Affine();
		affine.setMxx(1);
		affine.setMyy(1);
		affine.setTx(canvas.getWidth()/2);
		affine.setTy(canvas.getHeight()/2);

		gc.setTransform(affine);
		group.getChildren().add(canvas);
		workspace.getChildren().add(group);
	}


    @Override
    public void reloadWorkspace() {
	    String headColor = "#00b300";
	    String outlineColor = "#000000";
	    DataManager datamanager = (DataManager)app.getDataComponent();
	    SubRegions[] subRegionsArray = datamanager.getSubRegions();

	    for (SubRegions temp : subRegionsArray) {
		    ArrayList<double[]> tempx = temp.getXCord(canvas.getWidth());
		    ArrayList<double[]> tempy = temp.getYCord(canvas.getHeight());


		    for (int b = 0; b < tempx.size(); b++) {
			    double[] x = tempx.get(b);
			    double[] y = tempy.get(b);

			    gc.setFill(Paint.valueOf(headColor));
			    gc.fillPolygon(x, y, x.length);
			    gc.setStroke(Paint.valueOf(outlineColor));
			    gc.setLineWidth(.2);
			    gc.strokePolygon(x, y, x.length);

//			    System.out.println(Arrays.toString(x) + " " + Arrays.toString
//					    (y));

		    }

	    }
	    gc.stroke();

    }

    @Override
    public void initStyle() {
        
    }

	private void removeButtons(){
		FlowPane flowPane = (FlowPane) app.getGUI().getAppPane().getTop();
		flowPane.getChildren().remove(0);
		flowPane.getChildren().remove(1);
	}
}
