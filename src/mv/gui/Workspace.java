/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv.gui;

import com.sun.javafx.tk.Toolkit;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import mv.data.DataManager;
import mv.data.SubRegions;
import saf.components.AppWorkspaceComponent;
import mv.MapViewerApp;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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

	private void initGUI() {
		removeButtons();
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
		workspace.getChildren().add(canvas);
		canvas.heightProperty().bind(workspace.heightProperty());
		canvas.widthProperty().bind(workspace.widthProperty());
		handler();
	}


	@Override
	public void reloadWorkspace() {

		DataManager datamanager = (DataManager) app.getDataComponent();
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
		app.getGUI().getAppPane().getCenter().getStyleClass().add("CENTER");
	}

	@Override
	public void initStyle() {

	}

	private void removeButtons() {
		FlowPane flowPane = (FlowPane) app.getGUI().getAppPane().getTop();
		flowPane.getChildren().remove(0);
		flowPane.getChildren().remove(1);
	}


	private void handler() {

		canvas.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				gc.transform(1, 0, 0, 1, 0, 0);
				gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
						() + 10);
				double offsetX = -(e.getX() - canvas.getWidth()/2);
				double offsetY = -(e.getY() - canvas.getHeight()/2);
				gc.translate(offsetX, offsetY);
				double zoomfactor = 1.25;
				gc.transform(zoomfactor,0,0,zoomfactor,-(zoomfactor-1)*canvas.getWidth()/2,-(zoomfactor-1)*canvas.getHeight()/2);

				reloadWorkspace();
			}
			if(e.getButton() == MouseButton.SECONDARY){

				reloadWorkspace();
			}
		});

		workspace.getScene().setOnKeyPressed(e -> {
			switch (e.getCode()) {
				case UP:
					gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
							() + 10);
					gc.translate(0, canvas.getHeight()/20);
					reloadWorkspace();
					break;
				case DOWN:
					gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
							() + 10);
					gc.translate(0, -canvas.getHeight()/20);
					reloadWorkspace();
					break;
				case LEFT:
					gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
							() + 10);
					gc.translate(canvas.getWidth()/20, 0);
					reloadWorkspace();
					break;
				case RIGHT:
					gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
							() + 10);

					gc.translate(-canvas.getWidth()/20, 0);
					reloadWorkspace();
					break;
				default:
					break;
			}
		});

	}

	public void resetZoom(){
		gc.clearRect(0, 0, canvas.getWidth()+10, canvas.getHeight()+10);
	}
}