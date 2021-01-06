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
    private ArrayList<String> idiomas = new ArrayList<String>();

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

    
    /** 
     * @return ArrayList<String>
     */
    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    
    /** 
     * @param idioma
     */
    private void addIdiomas(String idioma) {
        idiomas.add(idioma);
    }

    public Rapper() {
        winner = false;
    }

    
    /** 
     * @return String
     */
    public String getPhoto() {
        return photo;
    }

    
    /** 
     * @return int
     */
    public int getLevel() {
        return level;
    }

    
    /** 
     * @return String
     */
    public String getBandera() {
        return bandera;
    }

    
    /** 
     * @param flag
     */
    private void setBandera(String flag) {
        bandera = flag;
    }

    
    /** 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    
    /** 
     * @return String
     */
    public String getNationality() {
        return nationality;
    }

    
    /** 
     * @return String
     */
    public String getBirth() {
        return birth;
    }

    
    /** 
     * @return String
     */
    public String getRealName() {
        return realName;
    }

    
    /** 
     * @param rapper
     * @return int
     */
    public int getWinner(ArrayList<Rapper> rapper) {
        for (int i = 0; i < rapper.size(); i++) {
            if (rapper.get(i).winner == true) {
                return i;
            }
        }
        return 0;
    }

    
    /** 
     * @param winner
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    
    /** 
     * @return int
     */
    public int getScore() {
        return score;
    }

    
    /** 
     * @return String
     */
    public String getScoreHtml() {
        return "" + score;
    }

    
    /** 
     * @param score
     */
    public void setScore(int score) {
        this.score += score;
    }

    
    /** 
     * @return String
     */
    public String getStageName() {
        return stageName;
    }

    
    /** 
     * @param rapper
     */
    public static void mostrarRanking(ArrayList<Rapper> rapper) {
        // Collections.sort(rapper, Collections.reverseOrder());
        // rapper.sort((o1, o2) -> Float.compare(o2.getScore(), o1.getScore()));
        ordenaRappers(rapper);

        System.out.println("------------------------------------");
        System.out.println("| POS | Name               | SCORE |");
        System.out.println("------------------------------------");

        for (int i = 0; i < rapper.size(); i++) {
            System.out.println("  " + (i + 1) + "\t" + rapper.get(i).stageName + "   " + "\t\t" + rapper.get(i).score);
        }
    }

    
    /** 
     * @param rapper
     */
    public static void ordenaRappers(ArrayList<Rapper> rapper) {

        // Collections.sort(rapper, Collections.reverseOrder());
        rapper.sort((o1, o2) -> Float.compare(o2.getScore(), o1.getScore()));

    }

    
    /** 
     * @param rapperHtml
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
     * @param rapperHtml
     * @throws IOException
     */
    private void makeHtmlFile(Rapper rapperHtml) throws IOException {
        //TODO: make loweCamelCase ex "mcGeorgeWatsky.html
        File htmlTemplateFile = new File("HTML/" + rapperHtml.getStageName().toLowerCase() + ".html");
        ProfileFactory factory = new ProfileFactory();
        Profile profile;

        profile = factory.createProfile(htmlTemplateFile, rapperHtml);
        profile.setCountry(rapperHtml.getNationality());
        profile.setFlagUrl(rapperHtml.getBandera());
        for (int i = 0; i < rapperHtml.getIdiomas().size(); i++) {
            profile.addLanguage(rapperHtml.getIdiomas().get(i));
        }
        profile.addExtra("Points", rapperHtml.getScoreHtml());
        if (rapperHtml.winner == true) {
            profile.addExtra("Position", "Winner!");
        }
        profile.writeAndOpen();
    }

    
    /** 
     * @param rapperHtml
     * @throws IOException
     */
    private void getCountryLanguage(Rapper rapperHtml) throws IOException {
        int responseCode;
        String url = "https://restcountries.eu/rest/v2/name/" + rapperHtml.getNationality(), withoutspaces = "",
                replace = "%20", data;
        StringBuffer response;
        URL urlRequest = new URL(url);
        String readLine = null;
        HttpURLConnection connection;
        BufferedReader in;
        JsonElement element;
        JsonArray array, arrayLanguages;

        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) != ' ') {
                withoutspaces += url.charAt(i);
            } else {
                withoutspaces += replace;
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
     * @return String
     */
    @Override
    public String getBirthdate() {
        return birth;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getName() {
        return realName;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getNickname() {
        return stageName;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getPictureUrl() {
        return photo;
    }
}