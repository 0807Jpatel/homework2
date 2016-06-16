/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.*;

import mv.data.DataManager;
import mv.data.SubRegions;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;

/**
 *
 * @author McKillaGorilla
 */
public class FileManager implements AppFileComponent {

	private static final String JSON_NUMBER_OF_SUBREGIONS =
			"NUMBER_OF_SUBREGIONS";

	private static final String JSON_SUBREGIONS = "SUBREGIONS";

	private static final String JSON_NUMBER_OF_SUBREGION_POLYGONS =
			"NUMBER_OF_SUBREGION_POLYGONS";

	private static final String JSON_SUBREGION_POLYGONS = "SUBREGION_POLYGONS";

	private static final String JSON_X = "X";
	private static final String JSON_Y = "Y";

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	    DataManager dataManager = (DataManager)data;
		dataManager.reset();
		JsonObject jsonObject = loadJSONFile(filePath);
	    int numberOfSubRegions = jsonObject.getInt(JSON_NUMBER_OF_SUBREGIONS);
	    JsonArray firstSubRegions = jsonObject.getJsonArray(JSON_SUBREGIONS);



	    SubRegions[] subRegionArray = new SubRegions[numberOfSubRegions];

	    for(int x = 0; x < numberOfSubRegions; x++){


		    ArrayList<Double[]> xCord = new ArrayList<>();
		    ArrayList<Double[]> yCord = new ArrayList<>();
			SubRegions temp = new SubRegions();

		    JsonObject subRegionItem = firstSubRegions.getJsonObject(x);
		    int numberOfPolygons = subRegionItem.getInt
				    (JSON_NUMBER_OF_SUBREGION_POLYGONS);
		    JsonArray subPolygons = subRegionItem.getJsonArray
				    (JSON_SUBREGION_POLYGONS);

		    for(int z = 0; z < numberOfPolygons; z++) {
			    JsonArray jsonArray = subPolygons.getJsonArray(z);

			    ArrayList<Double> xs = new ArrayList<>();
				ArrayList<Double> ys = new ArrayList<>();

			    for(int q = 0; q < jsonArray.size(); q++){
					JsonObject xyObject = jsonArray.getJsonObject(q);
				    xs.add(getDataAsDouble(xyObject, JSON_X));
				    ys.add(getDataAsDouble(xyObject, JSON_Y));
			    }

			    xCord.add(xs.toArray(new Double[xs.size()]));
			    yCord.add(ys.toArray(new Double[ys.size()]));
		    }

			temp.setX(xCord);
		    temp.setY(yCord);
		    subRegionArray[x] = temp;
	    }

		dataManager.setSubRegions(subRegionArray);

    }
    
    private double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    public int getDataAsInt(JsonObject json, String dataName) {
        JsonValue value = json.get(dataName);
        JsonNumber number = (JsonNumber)value;
        return number.bigIntegerValue().intValue();
    }
    
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
