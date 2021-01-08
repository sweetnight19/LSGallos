package Rapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.salleurl.profile.Profile;
import edu.salleurl.profile.ProfileFactory;
import edu.salleurl.profile.Profileable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Rapper implements Profileable {
    private String realName;
    private String stageName;
    private String birth;
    private String nationality;
    private int level;
    private String photo;
    private int score;
    private boolean winner;
    private String bandera;
    private final ArrayList<String> idiomas = new ArrayList<>();

    public Rapper(String realName, String stageName, String birth2, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth2;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
        score = 0;
        winner = false;
    }

    public Rapper() {
        winner = false;
    }

    /**
     * @return (ArrayList<String>) Devuelve el conjunto de idiomas del pais
     *         correspondiente al rapero
     */
    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    /**
     * @param idioma (String) añade un idioma nuevo a ese rapero
     */
    private void addIdiomas(String idioma) {
        idiomas.add(idioma);
    }

    /**
     * @return (String) Devuelve el url de la foto del rapero
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @return (int) Devuelve el nivel del rapero
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return (String) Devuelve el url correspondiente a la bandera del rapero
     */
    public String getBandera() {
        return bandera;
    }

    /**
     * @param flag (String) Se indica el url de la bandera para el usuario
     */
    private void setBandera(String flag) {
        bandera = flag;
    }

    /**
     * @param level (int) Se indica el nivel del rapero
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return (String) Devuelve el pais de origen del rapero
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @return (String) Devuelve la fecha de nacimiento
     */
    public String getBirth() {
        return birth;
    }

    /**
     * @return (String) Devuelve el nombre real del rapero
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param rapper ArrayList<Rapper>) Estructura de tipo ArraList<> donde estan
     *               los raperos
     * @return (int) Devuelve el número del identicador dentro del ArrayList de
     *         raperos
     */
    public int getWinner(ArrayList<Rapper> rapper) {
        for (int i = 0; i < rapper.size(); i++) {
            if (rapper.get(i).winner) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param winner (boolean) Para indicar quien es el ganador
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * @return (int) Devuelve la puntuacion del rapero
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score (int) Para añadir la puntuación nueva al rapero
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     * @return (String) Devuelve el nombre artístico del rapero
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * @param rapper (ArrayList<Rapper>) Conjunto de raperos para mostrar el ranking
     */
    public static void mostrarRanking(ArrayList<Rapper> rapper) {
        ordenaRappers(rapper);

        System.out.println("------------------------------------");
        System.out.println("| POS | Name               | SCORE |");
        System.out.println("------------------------------------");

        for (int i = 0; i < rapper.size(); i++) {
            System.out.println("  " + (i + 1) + "\t" + rapper.get(i).stageName + "   " + "\t\t" + rapper.get(i).score);
        }
    }

    /**
     * @param rapper (ArrayList<Rapper>) Conjunto de raperos para ordenar
     */
    public static void ordenaRappers(ArrayList<Rapper> rapper) {

        // Collections.sort(rapper, Collections.reverseOrder());
        rapper.sort((o1, o2) -> Float.compare(o2.getScore(), o1.getScore()));

    }

    /**
     * @param rapperHtml (Rapper) Rapero indicado para generar su perfil en Html
     * @throws IOException
     */
    public void createProfileHtml(Rapper rapperHtml) throws IOException {
        System.out.println();
        System.out.println(
                "Getting the information about their country of origin(" + rapperHtml.getNationality() + ")...");
        getCountryLanguage(rapperHtml);
        System.out.println();
        System.out.println("Generating HTML file...");
        System.out.println();
        makeHtmlFile(rapperHtml);
        System.out.println("Done! The profile will open in your default browser.");
    }

    /**
     * @param rapperHtml (Rapper) Rapero indicado para generar su perfil en Html
     * @throws IOException
     */
    private void makeHtmlFile(Rapper rapperHtml) throws IOException {
        // TODO: make loweCamelCase ex "mcGeorgeWatsky.html
        File htmlTemplateFile = new File("HTML/" + rapperHtml.getStageName().toLowerCase() + ".html");
        ProfileFactory factory = new ProfileFactory();
        Profile profile;

        profile = factory.createProfile(htmlTemplateFile, rapperHtml);
        profile.setCountry(rapperHtml.getNationality());
        profile.setFlagUrl(rapperHtml.getBandera());
        for (int i = 0; i < rapperHtml.getIdiomas().size(); i++) {
            profile.addLanguage(rapperHtml.getIdiomas().get(i));
        }
        profile.addExtra("Points", "" + rapperHtml.getScore());
        if (rapperHtml.winner) {
            profile.addExtra("Position", "Winner!");
        }
        profile.writeAndOpen();
    }

    /**
     * @param rapperHtml (Rapper) Rapero indicado para generar su perfil en Html
     * @throws IOException
     */
    private void getCountryLanguage(Rapper rapperHtml) throws IOException {
        int responseCode;
        String url = "https://restcountries.eu/rest/v2/name/" + rapperHtml.getNationality();
        StringBuilder withoutspaces = new StringBuilder();
        String replace = "%20";
        String data;
        StringBuffer response;
        URL urlRequest = new URL(url);
        String readLine;
        HttpURLConnection connection;
        BufferedReader in;
        JsonElement element;
        JsonArray array, arrayLanguages;

        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) != ' ') {
                withoutspaces.append(url.charAt(i));
            } else {
                withoutspaces.append(replace);
            }
        }

        connection = (HttpURLConnection) urlRequest.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            data = response.toString();
            element = JsonParser.parseString(data);
            array = element.getAsJsonArray();
            arrayLanguages = array.get(0).getAsJsonObject().get("languages").getAsJsonArray();
            for (int i = 0; i < arrayLanguages.size(); i++) {
                rapperHtml.addIdiomas(arrayLanguages.get(i).getAsJsonObject().get("name").getAsString());
            }
            rapperHtml.setBandera(array.get(0).getAsJsonObject().get("flag").getAsString());
        } else {
            System.out.println("Getting the information form the RESTapi is not working.");
        }
    }

    /**
     * @return (String) Devuelve la fecha de nacimiento
     */
    @Override
    public String getBirthdate() {
        return birth;
    }

    /**
     * @return (String) Devuelve el nombre real
     */
    @Override
    public String getName() {
        return realName;
    }

    /**
     * @return (String) Devuelve el nombre artistico
     */
    @Override
    public String getNickname() {
        return stageName;
    }

    /**
     * @return (String) Devuelve el url de la foto de perfil del rapero
     */
    @Override
    public String getPictureUrl() {
        return photo;
    }
}