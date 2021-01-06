package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Battle.Score;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;

import Competicion.Competicion;
import Countries.Countries;
import JsonHelper.JsonHelper;
import Rapper.Rapper;
import Themes.Theme;

public class MenuController {
    private int opcio;
    private Scanner scanner;

    public MenuController() {
        this.opcio = 0;
        this.scanner = new Scanner(System.in);
    }

    public void executaMenu(Competicion competicion, ArrayList<Countries> countries, ArrayList<Rapper> rapper)
            throws IOException, ParseException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Welcome to the competition: " + competicion.getName());
        System.out.println();
        System.out.println("Starts on " + competicion.getStartDate());
        System.out.println("Ends on " + competicion.getEndDate());
        System.out.println("It has " + competicion.getPhasesCount() + " phases");
        System.out.println("Currently there are " + rapper.size() + " participants");
        System.out.println();
        if (date.format(formatter).compareTo(competicion.getStartDate()) <= 0) {
            MenuRegistro(rapper, countries);
        } else {
            MenuLogin(competicion, rapper);
        }
    }

    private void MenuRegistro(ArrayList<Rapper> rapper, ArrayList<Countries> countries)
            throws IOException, ParseException {

        System.out.println("The competition hasn't started yet. Do you want to:");
        System.out.println();

        while (opcio != 2) {

            System.out.println("1. Register");
            System.out.println("2. Leave");
            System.out.println();
            System.out.print("Choose an option: ");
            opcio = scanner.nextInt();
            if (opcio == 1) {
                scanner.nextLine();
                Login(rapper, countries);
            }
            if (opcio != 1 && opcio != 2) {
                System.out.println("Enter a correct number!");
                System.out.println();
                System.out.print("Choose an option: ");
                opcio = scanner.nextInt();
            }
        }

    }

    private void MenuLogin(Competicion competicion, ArrayList<Rapper> rapper) throws IOException {
        int flagStageName;
        String name;

        System.out.println("The competition has started. Do you want to:");
        System.out.println();

        while (opcio != 2) {
            System.out.println("1. Login");
            System.out.println("2. Leave");
            System.out.println();
            System.out.print("Choose an option: ");
            try {
                opcio = scanner.nextInt();
                scanner.nextLine();
                switch (opcio) {
                    case 1:
                        System.out.println();
                        System.out.print("Enter your artistic name: ");
                        name = scanner.nextLine();
                        flagStageName = 0;
                        for (int i = 0; i < rapper.size(); i++) {
                            if (name.equals(rapper.get(i).getStageName())) {
                                flagStageName = 1;
                            }
                        }
                        if (flagStageName == 1) {
                            Dashboard(competicion, name, rapper);
                            System.out.println();
                        } else {
                            System.out.println("Bro, there's no " + name + " on ma' list.");
                            System.out.println();
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println();
                        System.out.println("ERROR: Option incorrect");
                        System.out.println();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("ERROR: Option incorrect");
                System.out.println();
                scanner.nextLine();
            }
        }
    }

    private void Login(ArrayList<Rapper> rapper, ArrayList<Countries> countries) throws IOException, ParseException {
        String realName;
        String stageName;
        String birth;
        String nationality;
        int level;
        String photo;

        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please, enter your personal information:");
        System.out.print("- Full name: ");
        realName = scanner.nextLine();
        System.out.print("- Artistic name: ");
        stageName = scanner.nextLine();
        System.out.print("- Date of birth (dd-MM-YYYY): ");
        birth = scanner.nextLine();
        System.out.print("- Country: ");
        nationality = scanner.nextLine();
        System.out.print("- Level: ");
        level = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Photo URL: ");
        photo = scanner.nextLine();
        System.out.println();

        checkInfo(realName, stageName, birth, nationality, level, photo, rapper, countries);
        System.out.println("---------------------------------------------------------------");
        System.out.println();

    }

    private void checkInfo(String realName, String stageName, String birth, String nationality, int level, String photo,
            ArrayList<Rapper> rapper, ArrayList<Countries> countries) throws IOException, ParseException {

        int flagStageName = 0;
        int flagCountries = 0;
        boolean valid;
        Rapper rapper2;

        // Comprobamos si existe el nombre artístico
        for (int i = 0; i < rapper.size(); i++) {
            if (rapper.get(i).getStageName().equals(stageName)) {
                flagStageName = 1;
            }

        }
        if (flagStageName == 1) {
            System.out.println();
            System.out.println("ERROR: The artistic name exist");
            System.out.println();
        }

        // Comprobamos que la fecha introducida tiene el formato correcto
        valid = checkBirthFormat(birth);

        if (valid) {
            // Comprobamos que la fecha introducida és correcta
            // valid = checkBirth(birth);
        }

        // Comprobamos si el pais existe
        flagCountries = checkCountry(countries, nationality);

        if (valid) {
            // Comprobamos que el nivel sea correcto
            valid = checkLevel(level);
        }

        // En el caso que todo sea correcto, procedemos a guardar el nuevo rapero
        if (flagCountries == 1 && valid == true && flagStageName == 0) {
            rapper2 = new Rapper(realName, stageName, birth, nationality, level, photo);

            // Añadimos el nuevo rapero a la memoria
            rapper.add(rapper2);
            System.out.println("Registration complete!");

            // TODO: Falta acabar de implementar la escritura
            // JsonHelper.añadirRapero(rapper2);
        }

    }

    private boolean checkLevel(int level) {
        if (level == 1 || level == 2) {
            return true;
        } else {
            System.out.println("ERROR: The level is incorrect");
            return false;
        }
    }

    private boolean checkBirthFormat(String birth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            sdf.parse(birth);
            sdf.setLenient(false);
            return true;

        } catch (ParseException e) {
            System.out.println("ERROR: The format of the date of birth introduced is not correct.");
            return false;
        }
    }

    private int checkCountry(ArrayList<Countries> countries, String nationality) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getName().equals(nationality)) {
                return 1;
            }
        }
        System.out.println();
        System.out.println("ERROR: The nation does not exist");
        System.out.println();
        return 0;
    }

    /*
     * private boolean checkBirth(String birth) { // FICAR ELS RETURN QUAN ARRIBI AL
     * FINAL DEL CODIG
     * 
     * int date = 0; int month = 0; int year = 0;
     * 
     * String cdate; String cmonth; String cyear;
     * 
     * boolean leap = false; // leap == bisiesto
     * 
     * int x = 0;
     * 
     * // PASANT LES DATES A VARIABLES INDIVIDUALS PER MES ENDAVANT FER LA
     * COMPROBACIÓ
     * 
     * for (int i = 0; i < birth.length(); i++) { if (birth.charAt(i) != '-') { if
     * (i < 2) { // cdate.charAt(x) = birth.charAt(i); } else { x = 0; if (i < 5) {
     * // cmonth.charAt(x) = birth.charAt(i); } else { // cyear.charAt(x) =
     * birth.charAt(i); } } } }
     * 
     * // date = Integer.parseInt(cdate); // month = Integer.parseInt(cmonth); //
     * year = Integer.parseInt(cyear);
     * 
     * // CHECK ANY DE TRASPAS
     * 
     * // ANY DIVIDIBLE PER 4 if (year % 4 == 0) {
     * 
     * // ANY DIVIDIBLE PER 100 if (year % 100 == 0) {
     * 
     * // ANY DIVIDIBLE PER 400 if (year % 400 == 0) { leap = true; // ANY DE
     * TRASPAS } else { leap = false; } } else { leap = true; // ANY DE TRASPAS } }
     * else { leap = false; }
     * 
     * // CHECK DATA CORRECTA
     * 
     * if (month == 2) { if (date <= 28) { return true; } else { if (date == 29) {
     * if (leap == true) { return true; } else { return false; } } } } else { if
     * (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month
     * == 10 || month == 12) { if (date <= 30) { return true; } else { return false;
     * } } else { if (month > 12) { return false; } else { if (date <= 31) { return
     * true; } else { return false; } } } } return false; }
     */

    private void Dashboard(Competicion competicion, String name, ArrayList<Rapper> rapper) throws IOException {

        Random random = new Random();
        int opcio2;
        int score;
        int contrincant;
        String type, consultantHtml;
        ArrayList<Theme> themList = new ArrayList<>();

        // Importamos las rimas
        JsonHelper.importRhymes(themList);

        // competicion.setStatus(1);
        score = 0;

        do {
            type = Score.getType();
            opcio2 = 0;

            for (int i = 0, flag = 0; i < rapper.size() && flag == 0; i++) {
                if (name.equals(rapper.get(i).getStageName())) {
                    score = rapper.get(i).getScore();
                    flag = 1;
                }
            }

            do {
                contrincant = random.nextInt(rapper.size());
            } while (rapper.get(contrincant).getStageName().equals(name));

            System.out.println();
            if (competicion.isFinish() == false) {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("| " + competicion.getName() + " | Phase " + competicion.getStatus() + "/"
                        + competicion.getPhasesCount() + " | Score: " + score + " | Next battle: " + type + " vs "
                        + rapper.get(contrincant).getStageName() + " |");
                System.out.println("---------------------------------------------------------------------------------");

            } else {
                if (rapper.get(0).getStageName().equals(name)) {
                    System.out.println(
                            "---------------------------------------------------------------------------------");
                    System.out.println("| " + competicion.getName() + " | Phase " + competicion.getStatus() + "/"
                            + competicion.getPhasesCount() + " | Score: " + rapper.get(0).getScore() + " | You win, "
                            + name + ". Good save the queen. |");
                    System.out.println(
                            "---------------------------------------------------------------------------------");

                } else {
                    System.out.println(
                            "---------------------------------------------------------------------------------");
                    System.out.println("| " + competicion.getName() + " | Phase " + competicion.getStatus() + "/"
                            + competicion.getPhasesCount() + " | Score: " + rapper.get(0).getScore() + " | You lost, "
                            + name + ". Try to get good. |");
                    System.out.println(
                            "---------------------------------------------------------------------------------");
                }

            }
            System.out.println();
            if (competicion.isFinish() == false) {
                System.out.println("1. Start the battle");
            } else {
                System.out.println("1. Start the battle (deactivated)");
            }
            System.out.println("2. Show ranking");
            System.out.println("3. Create profile");
            System.out.println("4. Leave competition");
            System.out.println();
            System.out.print("Choose an option: ");
            try {
                opcio2 = scanner.nextInt();
                scanner.nextLine();
                switch (opcio2) {
                    case 1:
                        if (competicion.isFinish() == true) {
                            System.out.println();
                            System.out.println("Competition ended. You can't battle anyone else!");
                        } else {
                            if (competicion.getStatus() == 1) {
                                competicion.getFase(rapper, name, contrincant, themList, type);
                                competicion.batallaInicial(rapper, themList, type, contrincant, name);
                                rapper = competicion.getResult(rapper);
                                Rapper.ordenaRappers(rapper);
                                competicion.setStatus(2);
                                Competicion.eliminarRaperos(rapper, competicion.getPhasesCount(),
                                        competicion.getStatus());
                            } else {
                                if (competicion.getPhasesCount() == 3 && competicion.getStatus() == 2) {
                                    // Tenemos 3 fases
                                    competicion.getFase(rapper, name, contrincant, themList, type);
                                    competicion.batallaInicial(rapper, themList, type, contrincant, name);
                                    rapper = competicion.getResult(rapper);
                                    Rapper.ordenaRappers(rapper);
                                    competicion.setStatus(3);
                                    Competicion.eliminarRaperos(rapper, competicion.getPhasesCount(),
                                            competicion.getStatus());
                                } else {
                                    if (competicion.getPhasesCount() == 3 && competicion.getStatus() == 3) {
                                        // Final de la 3 fase
                                        competicion.batallaInicial(rapper, themList, type, contrincant, name);
                                        competicion.setFinish(true);
                                    } else {
                                        // Final de la 2 fase
                                        competicion.batallaInicial(rapper, themList, type, contrincant, name);
                                        competicion.setFinish(true);
                                    }
                                    Rapper.ordenaRappers(rapper);
                                    rapper.get(0).setWinner(true);
                                }
                            }
                        }

                        break;
                    case 2:
                        // Show ranking
                        Rapper.mostrarRanking(rapper);
                        break;
                    case 3:
                        // Create Profile
                        System.out.println();
                        System.out.print("Enter the name of the rapper: ");
                        consultantHtml = scanner.nextLine();
                        for (int i = 0, flag = 0; i < rapper.size() && flag == 0; i++) {
                            if (rapper.get(i).getName().equals(consultantHtml)
                                    || rapper.get(i).getStageName().equals(consultantHtml)) {
                                rapper.get(0).createProfileHtml(rapper.get(i));
                                flag = 1;
                            }
                            if (i == rapper.size() - 1) {
                                System.out.println();
                                System.out.println("ERROR: The rapper does not exist!!");
                                System.out.println();
                            }
                        }

                        break;
                    case 4:
                        // Leave competition;
                        break;
                    default:
                        System.out.println("ERROR: Option incorrect");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Option incorrect");
                scanner.nextLine();
            }
        } while (opcio2 != 4);
    }
}