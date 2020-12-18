package JsonHelper;

import Competicion.Competicion;
import Countries.Countries;
import Rapper.Rapper;
import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class JsonHelper {

    public static void importRappers(Path path, ArrayList<Rapper> rappers) throws IOException {
        Gson gson=new Gson();
        String json = Files.readString(path);
        JsonElement element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
            rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
        }
    }

    public static void importCountries(Path path, ArrayList<String> countries) throws IOException {
        String json = Files.readString(path);
        JsonElement element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("countries").size(); i++) {
            countries.add(object.getAsJsonArray("countries").get(i).getAsString());
        }

    }

    public static Competicion importCompetition(Path path) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(path);
        JsonElement element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        JsonObject jsonObject = object.getAsJsonObject("competition").getAsJsonObject();

        return gson.fromJson(jsonObject, Competicion.class);
    }
}