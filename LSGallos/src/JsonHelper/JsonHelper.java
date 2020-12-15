package JsonHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import Competicion.Competicion;
import Countries.Countries;
import Rapper.Rapper;

public class JsonHelper {

    /*
     * public static solver[] importJson(String filename) throws IOException {
     * 
     * ArrayList<Solver> arrayList = new ArrayList<>();
     * 
     * Gson gson = new Gson(); JsonReader reader = new JsonReader(new
     * FileReader(filename)); JsonArray solvers =
     * JsonParser.parseReader(reader).getAsJsonArray();
     * 
     * reader.close();
     * 
     * Solver[] solver2 = gson.fromJson(solvers.getAsJsonArray(), Solver[].class);
     * 
     * arrayList.addAll(Arrays.asList(solver2)); return arrayList.toArray(new
     * Solver[0]);
     * 
     * }
     */

    public static Rapper[] importRappers(String filename) throws IOException {
        ArrayList<Rapper> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filename));

        JsonArray rapperArray = JsonParser.parseReader(reader).getAsJsonArray();
        reader.close();

        Rapper[] rappers = gson.fromJson(rapperArray.getAsJsonArray(), Rapper[].class);

        arrayList.addAll(Arrays.asList(rappers));
        return arrayList.toArray(new Rapper[0]);

    }

    public static Countries[] importCountries(String filename) throws IOException {
        ArrayList<Countries> arrayList = new ArrayList<>();

        JsonObject gsonObj = gsonObj.getAsJsonObject();

        JsonReader reader = new JsonReader(new FileReader(filename));

        JsonArray rapperArray = JsonParser.parseReader(reader).getAsJsonArray();
        reader.close();

        Countries[] countries = gsonObj.fromJson(rapperArray.getAsJsonArray(), Countries[].class);

        arrayList.addAll(Arrays.asList(countries));
        return arrayList.toArray(new Countries[0]);

    }

    public static Competicion[] importcCompeticions(String filename) throws IOException {
        ArrayList<Competicion> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filename));

        JsonArray competicioArray = JsonParser.parseReader(reader).getAsJsonArray();
        reader.close();

        Competicion[] competicions = gson.fromJson(competicioArray.getAsJsonArray(), Competicion[].class);

        arrayList.addAll(Arrays.asList(competicions));
        return arrayList.toArray(new Competicion[0]);

    }

}
