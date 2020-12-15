import Rapper.Rapper;

import com.google.gson.JsonIOException;

import Competicion.Competicion;
import Countries.Countries;
import JsonHelper.JsonHelper;

public class App {
    public static void main(String[] args) throws Exception {
        Rapper[] rapper;
        Countries[] countries;
        Competicion[] competicion;

        //rapper = JsonHelper.importRappers("/JSON/competició.json");
        countries = JsonHelper.importCountries("/JSON/competició.json");
        //competicion = JsonHelper.importcCompeticions("/JSON/competició.json");
        System.out.println("Hello, World!");

    }

}
