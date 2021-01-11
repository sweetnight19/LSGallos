package JsonHelper;

import Competicion.Competicion;
import Countries.Countries;
import Rapper.Rapper;
import Themes.Theme;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonHelper {
    private static JsonElement element;

    /**
     * @param path    (Path) Direccion de donde se encuentra el fichero JSON con los
     *                raperos
     * @param rappers (ArrayList<Rapper>) Estructura de tipo ArraList<> donde se
     *                almacenaran los raperos
     */
    public static void importRappers(Path path, ArrayList<Rapper> rappers) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(path);
        element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
            rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
        }
        System.out.println();
    }

    /**
     * @param path      (Path) Direccion de donde se encuentra el fichero JSON con
     *                  los paises
     * @param countries (ArrayList<Rapper>) Estructura de tipo ArraList<> donde se
     *                  almacenaran los paises
     */
    public static void importCountries(Path path, ArrayList<Countries> countries) throws IOException {
        String json = Files.readString(path);
        element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("countries").size(); i++) {
            countries.add(new Countries(object.getAsJsonArray("countries").get(i).getAsString()));
        }

    }

    /**
     * @param path (Path) Direccion de donde se encuentra el fichero JSON con los
     *             datos de la competicion
     * @return (Competicion) Devuelve una estructura de clase Competicion con todos
     *         los datos
     */
    public static Competicion importCompetition(Path path) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(path);
        element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        JsonObject jsonObject = object.getAsJsonObject("competition").getAsJsonObject();

        return gson.fromJson(jsonObject, Competicion.class);
    }

    /**
     * @param rapper (Rapper) rapero nuevo que se acaba de registrar y interesa
     *               añadirlo en el fichero JSON
     */
    public static void anadirRapero(Rapper rapperNuevo) throws IOException {

        Path p = Paths.get("JSON/competicio.json");
        String json = Files.readString(p);
        JsonElement element2;
        JsonObject object = new JsonObject();
        element = JsonParser.parseString(json);

        object.addProperty("realName", rapperNuevo.getRealName());
        object.addProperty("stageName", rapperNuevo.getStageName());
        object.addProperty("birth", rapperNuevo.getBirth());
        object.addProperty("nationality", rapperNuevo.getNationality());
        object.addProperty("level", rapperNuevo.getLevel());
        object.addProperty("photo", rapperNuevo.getPhoto());

        element2 = object.getAsJsonObject();
        element.getAsJsonObject().getAsJsonArray("rappers").add(element2);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter("JSON/competicio.json");
        fw.write(gson.toJson(element));
        fw.close();
    }

    /**
     * @param themList (ArrayList<Theme>) Estructura de tipo ArrayList<> donde se
     *                 almacenaran los temas
     */
    public static void importRhymes(ArrayList<Theme> themList) throws IOException {
        Path p = Paths.get("JSON/batalles.json");
        Gson gson = new Gson();
        String json = Files.readString(p);
        JsonElement element2 = JsonParser.parseString(json);
        JsonObject object = element2.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("themes").size(); i++) {
            themList.add(gson.fromJson(object.getAsJsonArray("themes").get(i).getAsJsonObject(), Theme.class));
        }
    }
}