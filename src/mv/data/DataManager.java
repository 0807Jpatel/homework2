package mv.data;

import saf.components.AppDataComponent;
import mv.MapViewerApp;

/**
 *
 * @author McKillaGorilla
 */
public class DataManager implements AppDataComponent {
    MapViewerApp app;
    
    public DataManager(MapViewerApp initApp) {
        app = initApp;
    }
    
    @Override
    public void reset() {
        
    }
}
