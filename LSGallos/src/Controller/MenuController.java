package Controller;

import Competicion.Competicion;
import Rapper.Rapper;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MenuController {
    private int opcio;
    private Scanner scanner;

    public MenuController() {
        this.opcio = 0;
        this.scanner = new Scanner(System.in);
    }

    public void executaMenu(Competicion competicion, ArrayList<String> countries, ArrayList<Rapper> rapper) {
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
            MenuRegistro();
        } else {
            MenuLogin();
        }
    }

    private void MenuRegistro() {
        System.out.println("The competition hasn't started yet. Do you want to:");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Leave");
        System.out.println();
        System.out.print("Choose an option: ");
        opcio = scanner.nextInt();
    }

    private void MenuLogin() {
        System.out.println("The competition has started. Do you want to:");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Leave");
        System.out.println();
        System.out.print("Choose an option: ");
        opcio = scanner.nextInt();
    }
}
