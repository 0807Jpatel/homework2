package mv.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import mv.MapViewerApp;

/**
 * Created by jappatel on 6/16/16.
 */
public class Controller {
	MapViewerApp app;

	public Controller(MapViewerApp app){
		this.app = app;
	}

	public void mouseClick(Canvas canvas, MouseEvent e, GraphicsContext gc){
		if (e.getButton() == MouseButton.PRIMARY) {
			gc.transform(1, 0, 0, 1, 0, 0);
			gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
					() + 10);
			double offsetX = -(e.getX() - canvas.getWidth()/2);
			double offsetY = -(e.getY() - canvas.getHeight()/2);
			gc.translate(offsetX, offsetY);
			double zoomfactor = 1.25;
			gc.transform(zoomfactor,0,0,zoomfactor,-(zoomfactor-1)*canvas.getWidth()/2,-(zoomfactor-1)*canvas.getHeight()/2);
			app.getWorkspaceComponent().reloadWorkspace();
		}
		if(e.getButton() == MouseButton.SECONDARY){

			app.getWorkspaceComponent().reloadWorkspace();
		}
	}

	public void directionalKey(Canvas canvas, KeyEvent e, GraphicsContext gc){
		switch (e.getCode()) {
			case UP:
				gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
						() + 10);
				gc.translate(0, canvas.getHeight()/20);
				app.getWorkspaceComponent().reloadWorkspace();
				break;
			case DOWN:
				gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
						() + 10);
				gc.translate(0, -canvas.getHeight()/20);
				app.getWorkspaceComponent().reloadWorkspace();
				break;
			case LEFT:
				gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
						() + 10);
				gc.translate(canvas.getWidth()/20, 0);
				app.getWorkspaceComponent().reloadWorkspace();
				break;
			case RIGHT:
				gc.clearRect(0, 0, canvas.getWidth() + 10, canvas.getHeight
						() + 10);

				gc.translate(-canvas.getWidth()/20, 0);
				app.getWorkspaceComponent().reloadWorkspace();
				break;
			default:
				break;
		}
	}
}
