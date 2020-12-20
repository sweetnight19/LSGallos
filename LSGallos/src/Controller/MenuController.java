package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Competicion.Competicion;
import Countries.Countries;
import JsonHelper.JsonHelper;
import Rapper.Rapper;

public class MenuController {
    private int opcio;
    private Scanner scanner;

    public MenuController() {
        this.opcio = 0;
        this.scanner = new Scanner(System.in);
    }

    public void executaMenu(Competicion competicion, ArrayList<Countries> countries, ArrayList<Rapper> rapper)
            throws IOException {
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

    private void MenuRegistro(ArrayList<Rapper> rapper, ArrayList<Countries> countries) throws IOException {

        System.out.println("The competition hasn't started yet. Do you want to:");
        System.out.println();

        while (opcio != 2) {

            System.out.println("1. Register");
            System.out.println("2. Leave");
            System.out.println();
            System.out.print("Choose an option: ");
            opcio = scanner.nextInt();
            if (opcio == 1) {
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

    private void MenuLogin(Competicion competicion, ArrayList<Rapper> rapper) {
        int flagStageName;
        String name;

        System.out.println("The competition has started. Do you want to:");
        System.out.println();

        while (opcio != 2) {
            System.out.println("1. Login");
            System.out.println("2. Leave");
            System.out.println();
            System.out.print("Choose an option: ");
            opcio = scanner.nextInt();
            scanner.nextLine();
            if (opcio == 1) {
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
                    Dashboard(competicion);
                    System.out.println();
                } else {
                    System.out.println("Bro, there's no " + name + " on ma' list.");
                    System.out.println();
                }
            }
            if (opcio != 1 && opcio != 2) {
                System.out.println("Enter a correct number!");
                System.out.println();
                System.out.print("Choose an option: ");
                opcio = scanner.nextInt();
            }

        }

    }

    private void Login(ArrayList<Rapper> rapper, ArrayList<Countries> countries) throws IOException {
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
        System.out.print("- Date of birth (dd/MM/YYYY): ");
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
            ArrayList<Rapper> rapper, ArrayList<Countries> countries) throws IOException {

        int flagStageName = 0;
        int flagCountries = 0;
        Rapper rapper2;
        for (int i = 0; i < rapper.size(); i++) {
            if (rapper.get(i).getStageName().equals(stageName)) {
                flagStageName = 1;
            }

        }
        if (flagStageName == 1) {
            System.out.println();
            System.out.println("ERROR: The artistic name exist");
            System.out.println();
        } else {
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getName().equals(nationality)) {
                    flagCountries = 1;
                }
            }
            if (flagCountries == 1) {
                rapper2 = new Rapper(realName, stageName, birth, nationality, level, photo);

                // Añadimos el nuevo rapero a la memoria
                rapper.add(rapper2);
                System.out.println("Registration complete!");

                // Falta acabar de implementar la escritura
                JsonHelper.añadirRapero(rapper2);
            } else {
                System.out.println("ERROR: The nation does not exist");
            }
        }
    }

    private void Dashboard(Competicion competicion) {
        int opcio2;

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| " + competicion.getName() + " | Phase 1/" + competicion.getPhasesCount()
                + " | Score: 0 | Next battle: escrita vs Trueno |");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("1. Start the battle");
        System.out.println("2. Show ranking");
        System.out.println("3. Create profile");
        System.out.println("4. Leave competition");
        System.out.println();
        System.out.print("Choose an option: ");
        opcio2 = scanner.nextInt();
        scanner.nextLine();
    }
}
