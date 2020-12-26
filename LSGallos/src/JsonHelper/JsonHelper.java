package JsonHelper;

import Competicion.Competicion;
import Countries.Countries;
import Rapper.Rapper;
import Themes.Theme;
import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonHelper {
    private static JsonElement element;

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

    public static void importCountries(Path path, ArrayList<Countries> countries) throws IOException {
        String json = Files.readString(path);
        element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        for (int i = 0; i < object.getAsJsonArray("countries").size(); i++) {
            countries.add(new Countries(object.getAsJsonArray("countries").get(i).getAsString()));
        }

    }

    public static Competicion importCompetition(Path path) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(path);
        element = JsonParser.parseString(json);
        JsonObject object = element.getAsJsonObject();

        JsonObject jsonObject = object.getAsJsonObject("competition").getAsJsonObject();

        return gson.fromJson(jsonObject, Competicion.class);
    }

    public static void añadirRapero(Rapper rapper) throws IOException {
        /*
         * Versio del Artur
         * 
         * JsonElement element; JsonObject json = new JsonObject(); Path p =
         * Paths.get("JSON/competicio.json"); Gson gson = new
         * GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
         * 
         * json.add("competition",
         * JsonParser.parseString(gson.toJson(competition)).getAsJsonObject()); element
         * = json.get("competition").getAsJsonObject().get("countries");
         * json.add("countries", element);
         * json.get("competition").getAsJsonObject().remove("countries");
         * 
         * element = json.get("competition").getAsJsonObject().get("rappers");
         * json.add("rappers", element);
         * json.get("competition").getAsJsonObject().remove("rappers");
         * 
         * Files.writeString(p, gson.toJson(json));
         */

        /*
         * Versio meva
         * 
         * Path p = Paths.get("JSON/competicio.json"); String json =
         * Files.readString(p); element = JsonParser.parseString(json);
         * 
         * JsonObject object = new JsonObject();
         * //object.getAsJsonArray("rappers").add(element);
         * object.addProperty("realName", rapper.getRealName());
         * object.addProperty("stageName", rapper.getStageName());
         * object.addProperty("birth", rapper.getBirth());
         * object.addProperty("nationality", rapper.getNationality());
         * object.addProperty("level", rapper.getLevel()); object.addProperty("photo",
         * rapper.getPhoto());
         * 
         * element.getAsJsonObject().getAsJsonArray("rappers").add(object.toString());
         * Gson gson = new GsonBuilder().setPrettyPrinting().create(); FileWriter fw =
         * new FileWriter("JSON/competicio.json"); fw.write(gson.toJson(element));
         * fw.close();
         */

        /*
         * Versio del Pol
         * 
         * element.getAsJsonArray().get(0).getAsJsonObject().add("name", new
         * JsonPrimitive("Test"));
         * 
         * Gson gson = new GsonBuilder().setPrettyPrinting().create(); FileWriter fw =
         * new FileWriter("JSON/competicio.json"); fw.write(gson.toJson(element));
         * fw.close();
         * 
         * rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).
         * getAsJsonObject(), Rapper.class));
         */

        /*
         * Versio de la Raquel
         * 
         * JsonParser parser = new JsonParser(); try (FileReader reader = new
         * FileReader("JSON/competicio.json")){ JsonObject rapper = new JsonObject();
         * rapper.put("realName", rapper.getNomComplet()); rapper.put("stageName",
         * rapper.getNomArtistic()); rapper.put("birth", rapper.getDataNaixement());
         * rapper.put("nationality", rapper.getPaisString()); rapper.put("level",
         * rapper.getNivell()); rapper.put("photo", rapper.getFoto()); JsonObject
         * competitionFile = (JsonObject) parser.parse(reader); JsonArray arrayRappers =
         * (JsonArray) competitionFile.get("rappers"); arrayRappers.add(rapper);
         * competitionFile.replace("rappers", arrayRappers);
         * 
         * try (FileWriter writer = new FileWriter("JSON/competicio.json")){
         * //ObjectMapper mapper = new ObjectMapper(); //Object json =
         * mapper.readValue(competitionFile.toJSONString(), Object.class); //String
         * jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
         * writer.write(competitionFile.toString()); writer.flush();
         * 
         * }catch (IOException e){
         * System.out.println("IO Exeption a al registrar rapero al fitcher: " +
         * e.getMessage()); } } catch (IOException | ParseException e){
         * System.out.println(e.getMessage()); }
         */
    }

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