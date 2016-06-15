package mv.data;

import saf.components.AppDataComponent;
import mv.MapViewerApp;

/**
 *
 * @author McKillaGorilla
 */
public class DataManager implements AppDataComponent {
    MapViewerApp app;
	private SubRegions[] subRegions;

	public DataManager(MapViewerApp initApp) {
        app = initApp;
    }

	public void inisubRegions(int NumberOfSubregions){
		subRegions = new SubRegions[NumberOfSubregions];
	}

	public SubRegions[] getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(SubRegions[] subRegions) {
		this.subRegions = subRegions;
	}

	@Override

    public void reset() {
		subRegions = new SubRegions[0];
    }
}
