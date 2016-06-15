/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv.gui;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import mv.data.DataManager;
import mv.data.SubRegions;
import saf.components.AppWorkspaceComponent;
import mv.MapViewerApp;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author McKillaGorilla
 */
public class Workspace extends AppWorkspaceComponent {
	private final String BodyCOlor = "#238f23";
	private final String OceanColor = "#00a4cb";
	private final String outlineColor = "#000000";



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
		canvas= new Canvas();
		gc = canvas.getGraphicsContext2D();
		workspace.getChildren().add(canvas);
		canvas.heightProperty().bind(workspace.heightProperty());
		canvas.widthProperty().bind(workspace.widthProperty());

		//Clip the Image in the workspace




		handler();
	}


    @Override
    public void reloadWorkspace() {
	    DataManager datamanager = (DataManager)app.getDataComponent();
	    SubRegions[] subRegionsArray = datamanager.getSubRegions();

	    gc.setFill(Paint.valueOf(OceanColor));
	    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	    gc.setFill(Paint.valueOf(BodyCOlor));
	    gc.setStroke(Paint.valueOf(outlineColor));
	    gc.setLineWidth(.3);

	    for (SubRegions temp : subRegionsArray) {
		    ArrayList<double[]> tempx = temp.getXCord(canvas.getWidth());
		    ArrayList<double[]> tempy = temp.getYCord(canvas.getHeight());

		    for (int b = 0; b < tempx.size(); b++) {
			    double[] x = tempx.get(b);
			    double[] y = tempy.get(b);

			    gc.fillPolygon(x, y, x.length);
			    gc.strokePolygon(x, y, x.length);

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


	private void handler(){
		canvas.setOnMouseClicked(e-> {
			if(e.getButton() == MouseButton.PRIMARY){
				canvas.setScaleX(canvas.getScaleX() * 1.2);
				canvas.setScaleY(canvas.getScaleY() * 1.2);
			}
			else if(e.getButton() == MouseButton.SECONDARY){
				canvas.setScaleX(canvas.getScaleX()/1.2);
				canvas.setScaleY(canvas.getScaleY()/1.2);
			}
		});
	}
}
