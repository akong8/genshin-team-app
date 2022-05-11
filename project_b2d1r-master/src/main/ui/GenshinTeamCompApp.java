package ui;

import model.Character;
import model.GenshinTeam;
import persistence.TeamReader;
import persistence.TeamWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the Genshin Impact Team Composition Application
public class GenshinTeamCompApp {

    private static final String TEAM_FILE = "./data/genshinteam.json";
    private Scanner userInput;
    private GenshinTeam team;
    private TeamWriter teamWriter;
    private TeamReader teamReader;

    // EFFECTS: runs Genshin team composition application
    public GenshinTeamCompApp() throws FileNotFoundException {
        team = new GenshinTeam();
        teamReader = new TeamReader(TEAM_FILE);
        teamWriter = new TeamWriter(TEAM_FILE);
        runGenshinTeamCompApp();
    }

    // Method referenced from Teller App in
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp

    // MODIFIES: this
    // EFFECTS: processes user input to determine if app needs to keep running
    private void runGenshinTeamCompApp() {
        boolean runApp = true;
        String userCommand = null;
        start();

        homeScreen();

        while (runApp) {
            userCommand = userInput.next();

            if (userCommand.equals("6")) {
                runApp = false;
            } else {
                processUserCommand(userCommand);
            }
        }
        System.out.print("See you soon, Traveler!");
    }

    // Method referenced from Teller App in
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processUserCommand(String userCommand) {
        if (userCommand.equals("1")) {
            System.out.println("Welcome to the dream team creator! \n");
            chooseCharacter();
        } else if (userCommand.equals("2")) {
            viewAdvicePage();
        } else if (userCommand.equals("3")) {
            viewTeam();
        } else if (userCommand.equals("4")) {
            saveTeam();
        } else if (userCommand.equals("5")) {
            loadTeam();
        } else {
            System.out.println("ERROR! Selection is not valid, please try again..");
        }
    }

    // EFFECTS: displays home screen to user with application options
    private void homeScreen() {
        System.out.println("Welcome to the Genshin Impact Team Composition Application, Traveler! \n");
        System.out.println("What do you wish to do? Please select one of the options from 1-6.");
        System.out.println("\t(1) Add characters to a team");
        System.out.println("\t(2) Paimon's team advice corner");
        System.out.println("\t(3) View current team");
        System.out.println("\t(4) Save team");
        System.out.println("\t(5) Load team");
        System.out.println("\t(6) End current session");
        System.out.println("------------------------------------");
    }

    // EFFECTS: initializer
    private void start() {
        team = new GenshinTeam();
        userInput = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: process user input and display current team members
    private void viewTeam() {
        userInput = new Scanner(System.in);
        boolean viewingTeam = true;

        System.out.println("Enter a team name:");
        String teamName = userInput.nextLine();
        System.out.println("\n" + teamName + ":");

        for (Character character : team.getTeam()) {
            System.out.println(character.getName() + ", " + character.getVision());
        }
        System.out.println("\nEnter 'x' to return to the home screen.");

        while (viewingTeam) {
            ;
            if (userInput.next().charAt(0) == 'x') {
                viewingTeam = false;
            }
        }
        homeScreen();
    }

    // MODIFIES: this
    // EFFECTS: recommends team composition based on user input
    private void adviceCorner() {
        userInput = new Scanner(System.in);
        System.out.println("Don't know where to start? Welcome to Paimon's advice corner!");
        System.out.println("\nPlease enter a character name and vision to start:");
        Character character = new Character(userInput.next(), userInput.next());
        System.out.println("Team suggestions for " + character.getName() + ":\n");

        if (character.getVision().equals("anemo")) {
            System.out.println("Recommended for support use, anemo can fit into any team for crowd control.");
            System.out.println("NOTE: Xiao is an anemo dps, so having another anemo character for support is ideal!");
            System.out.println("\nEnter 'B' to go back or 'N' to return to the home screen.");
        } else if (character.getVision().equals("cryo")) {
            System.out.println("1. Permafreeze team: cryo (dps), cryo (sub dps), hydro (support) & anemo (support");
            System.out.println("2. Melt team: cryo (dps), pyro (sub dps), pyro/cryo (support) & anemo (support)");
            System.out.println("\nEnter 'B' to go back or 'N' to return to the home screen.");
        } else if (character.getVision().equals("electro")) {
            System.out.println("1. Taser team: electro (dps), electro (sub dps), hydro (support) & hydro (support)");
            System.out.println("\nEnter 'B' to go back or 'N' to return to the home screen.");
        } else if (character.getVision().equals("hydro") || character.getVision().equals("pyro")) {
            System.out.println("1. Vaporize team: hydro (dps), hydro/pyro (sub dps), pyro (support) & anemo (support)");
            System.out.println("\nEnter 'B' to go back or 'N' to return to the home screen.");
        } else if (character.getVision().equals("geo")) {
            System.out.println("1. Mono-geo team: all geo team");
            System.out.println("\nEnter 'B' to go back or 'N' to return to the home screen.");
        }
    }


    // MODIFIES: this
    // EFFECTS: determines whether user wishes to continue viewing characters or return to
    // home screen
    private void viewAdvicePage() {
        boolean viewAdvice = true;

        while (viewAdvice) {
            adviceCorner();

            if (userInput.next().charAt(0) == 'N') {
                viewAdvice = false;
            }
        }
        homeScreen();
    }

    // REQUIRES: team is not full (<= 4)
    // MODIFIES: this
    // EFFECTS: adds character given by user into the current team
    private void addCharacter() {
        userInput = new Scanner(System.in);
        System.out.println("Please enter a character name and vision (e.g. xiao anemo):");
        team.addCharacter(new Character(userInput.next(), userInput.next()));
        System.out.println("Character successfully added.");
        System.out.println("Add another character? Please enter 'Y' or 'N'");
    }

    // MODIFIES: this
    // EFFECTS: determines whether to add another character to the team or return to
    // home screen based on user input
    private void chooseCharacter() {
        boolean addCharacter = true;

        while (addCharacter) {
            if (team.isFull()) {
                System.out.println("This team is full!");
                System.out.println("\nWould you like to view your current team members?");

                if (userInput.next().charAt(0) == 'N') {
                    homeScreen();
                    break;
                }
                viewTeam();
                break;
            }
            addCharacter();

            if (userInput.next().charAt(0) == 'N') {
                addCharacter = false;
                viewTeam();
            }
        }
    }

    // Method taken from WorkRoomApp class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: saves team to file
    private void saveTeam() {
        try {
            teamWriter.open();
            teamWriter.write(team);
            teamWriter.close();
            System.out.println("Successfully saved team to " + TEAM_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Unable to write to file: " + TEAM_FILE);
        }
    }

    // Method taken from WorkRoomApp class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // MODIFIES: this
    // EFFECTS: loads team from file
    private void loadTeam() {
        try {
            team = teamReader.readTeam();
            System.out.println("Successfully loaded team from " + TEAM_FILE);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read from file: " + TEAM_FILE);
        }
    }
}


