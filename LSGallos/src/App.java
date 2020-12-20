import Competicion.Competicion;
import Controller.MenuController;
import Countries.Countries;
import JsonHelper.JsonHelper;
import Rapper.Rapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Rapper> rapper = new ArrayList<>();
        ArrayList<Countries> countries = new ArrayList<>();
        Competicion competicion;
        MenuController menuController = new MenuController();
        Path p = Paths.get("JSON/competicio.json");

        JsonHelper.importRappers(p, rapper);
        JsonHelper.importCountries(p, countries);
        competicion = JsonHelper.importCompetition(p);
        menuController.executaMenu(competicion, countries, rapper);
        System.out.println();
    }

}
