package ui;

import model.Character;
import model.EventLog;
import model.Event;
import model.GenshinTeam;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

// Represents a Genshin Impact team composition app GUI
public class GenshinTeamCompAppGUI extends JFrame implements ActionListener {

    // Fields
    private static final String TEAM_FILE = "./data/genshinteam.txt";
    private Character character;
    private JList list;
    private JList teamList;
    private DefaultListModel listModel = new DefaultListModel();
    private GenshinTeam team = new GenshinTeam();


    private JPanel homeScreen;
    private JPanel addCharacterPanel;
    private JPanel addCharNorthPanel;
    private JPanel southPanel;
    private JPanel advicePanel;
    private JPanel adviceNorthPanel;
    private JPanel adviceSouthPanel;
    private JPanel viewCharacterPanel;
    private JPanel headerPanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton homeButton;
    private JButton generate;
    private JButton reset;

    private JTextField name;
    private JTextField vision;
    private JTextField input;
    private JTextField output;

    private AudioStream backgroundMusic;

    // MODIFIES: this
    // EFFECTS: creates and runs Genshin team composition app GUI
    public GenshinTeamCompAppGUI() {
        super("Genshin Team Composition Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        homeScreen();
        addCharacterScreen();
        adviceScreen();
        viewCharacterScreen();

        JLabel titleLabel = new JLabel("Welcome to the Genshin Impact Team Composition Application, Traveler!",
                SwingConstants.CENTER);
        titleLabel.setFont(new Font("HYWenHei", Font.BOLD, 15));
        JLabel menuLabel = new JLabel("What do you wish to do? Please select from the options below.",
                SwingConstants.CENTER);
        menuLabel.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        homeScreen.add(titleLabel);
        homeScreen.add(menuLabel);

        homeScreenButtons();
        addHomeButtons(button1, button2, button3, button4, button5, button6);
        setButtonActions();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates home screen with given background colour
    private void homeScreen() {
        homeScreen = new JPanel(new GridLayout(0, 1, 5, 5));
        homeScreen.setBackground(Color.lightGray);

        playBackgroundMusic();

        add(homeScreen, BorderLayout.CENTER);

        homeScreen.setVisible(true);
    }

    // EFFECTS: creates home screen buttons with their given labels
    private void homeScreenButtons() {
        button1 = new JButton("Add characters to a team");
        button2 = new JButton("Paimon's team advice corner");
        button3 = new JButton("View current team");
        button4 = new JButton("Save team");
        button5 = new JButton("Load Team");
        button6 = new JButton("End current session");
    }

    // MODIFIES: this
    // EFFECTS: adds button to given panel with given properties
    private void addHomeButton(JButton button, JPanel panel) {
        button.setBackground(Color.WHITE);
        button.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // Method referenced from https://github.com/pkhiani/AuctionApp
    // MODIFIES: this
    // EFFECTS: calls addHomeButton and adds given buttons to given panel
    private void addHomeButtons(JButton button1, JButton button2, JButton button3, JButton button4, JButton button5,
                                JButton button6) {
        addHomeButton(button1, homeScreen);
        addHomeButton(button2, homeScreen);
        addHomeButton(button3, homeScreen);
        addHomeButton(button4, homeScreen);
        addHomeButton(button5, homeScreen);
        addHomeButton(button6, homeScreen);
    }

    // MODIFIES: this
    // EFFECTS: sets home screen buttons to their respective actions
    private void setButtonActions() {
        button1.addActionListener(this);
        button1.setActionCommand("Add characters to a team");
        button2.addActionListener(this);
        button2.setActionCommand("Paimon's team advice corner");
        button3.addActionListener(this);
        button3.setActionCommand("View current team");
        button4.addActionListener(this);
        button4.setActionCommand("Save team");
        button5.addActionListener(this);
        button5.setActionCommand("Load team");
        button6.addActionListener(this);
        button6.setActionCommand("End current session");
    }

    // EFFECTS: displays home screen, sets all other panels' visibility to false
    private void returnToHome() {
        playSound("./data/returnToHome.wav");
        playBackgroundMusic();

        homeScreen.setVisible(true);
        addCharacterPanel.setVisible(false);
        viewCharacterPanel.setVisible(false);
        advicePanel.setVisible(false);
    }

    // EFFECTS: creates add character screen with given layout and content
    private void addCharacterScreen() {
        addCharacterPanel = new JPanel(new BorderLayout());
        addCharacterContent();
    }

    // MODIFIES: this
    // EFFECTS: creates the central panel of add character screen with given contents
    private void addCharCentralPanel() {
        JPanel centralPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        JLabel image = new JLabel(new ImageIcon("./data/characters.png"));
        centralPanel.add(image);

        JScrollPane scroll = new JScrollPane(centralPanel);

        addCharacterPanel.add(scroll, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates north panel of add character screen with given contents
    private void addCharNorthPanel() {
        JLabel label3 = new JLabel("Name:");
        addLabel(label3);
        name = new JTextField(10);
        addCharNorthPanel.add(name);
        JLabel label4 = new JLabel("Vision:");
        addLabel(label4);
        vision = new JTextField(10);
        addCharNorthPanel.add(vision);
    }

    // MODIFIES: this
    // EFFECTS: creates add character panel with given contents
    private void addCharacterContent() {
        addCharNorthPanel = new JPanel(new GridLayout(0, 1, 5, 10));
        JLabel label = new JLabel("Welcome to the Genshin Team Creator!", SwingConstants.CENTER);
        label.setFont(new Font("HYWenHei", Font.BOLD, 15));
        JLabel label2 = new JLabel("Please enter a name and vision (e.g. xiao anemo):");
        addCharNorthPanel.add(label);
        addLabel(label2);
        addCharNorthPanel();

        addCharCentralPanel();

        southPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JButton addCharacter = new JButton("Add character");
        addCharacter.setPreferredSize(new Dimension(150, 75));
        addCharacter.addActionListener(this);
        addCharacter.setActionCommand("Add character");
        addButtonChar(addCharacter);

        resetTeamButton();

        homeButton = new JButton("Return to home screen");
        homeButton.addActionListener(this);
        homeButton.setActionCommand("Return to home screen");
        addButtonChar(homeButton);

        addCharacterPanel.add(addCharNorthPanel, BorderLayout.NORTH);
        addCharacterPanel.add(southPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds label with given properties to given panel
    private void addLabel(JLabel label) {
        label.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        addCharNorthPanel.add(label);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to south panel with given properties
    private void addButtonChar(JButton button) {
        button.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        southPanel.add(button);
    }

    // EFFECTS: initializes and displays add character screen
    private void displayAddCharacterScreen() {
        playSound("./data/click.wav");
        add(addCharacterPanel, BorderLayout.CENTER);

        AudioPlayer.player.stop(backgroundMusic);

        addCharacterPanel.setVisible(true);
        homeScreen.setVisible(false);
        viewCharacterPanel.setVisible(false);
        advicePanel.setVisible(false);
    }

    // EFFECTS: constructs warning frame when team is full
    private void fullTeamPopUp() {
        JOptionPane.showMessageDialog(null,
                "This team is full! Redirecting you to view team...",
                "Full team warning", JOptionPane.WARNING_MESSAGE);
        displayViewCharacterScreen();
    }

    // MODIFIES: this, listModel
    // EFFECTS: processes user input and adds them to a team, also resets text fields
    private void addCharacterToTeam() {
        character = new Character(name.getText(), vision.getText());

        if (listModel.getSize() == 4) {
            fullTeamPopUp();
        } else {
            listModel.addElement(character.getName() + ", " + character.getVision());
            team.addCharacter(character);

            int answer = JOptionPane.showConfirmDialog(null,
                    "Character added. Add another?", "Added successfully", JOptionPane.YES_NO_OPTION);

            if (answer == JOptionPane.NO_OPTION) {
                displayViewCharacterScreen();
            }

        }
        name.requestFocusInWindow();
        name.setText("");
        vision.requestFocusInWindow();
        vision.setText("");
    }

    // MODIFIES: this
    // EFFECTS: clears team of all characters
    private void resetTeam() {
        listModel.clear();
        team.resetTeam();
        JOptionPane.showMessageDialog(null, "This team has been reset.",
                "Reset team warning", JOptionPane.WARNING_MESSAGE);

    }

    // MODIFIES: this
    // EFFECTS: constructs button to reset team
    private void resetTeamButton() {
        reset = new JButton("Reset team");
        reset.addActionListener(this);
        reset.setActionCommand("Reset team");
        addButtonChar(reset);
    }

    // MODIFIES: this
    // EFFECTS: adds given components to given panel
    private void addToAdvicePanel(JTextField input, JButton button1, JTextField output, JButton button2) {
        adviceNorthPanel.add(input);
        adviceSouthPanel.add(button1);
        advicePanel.add(output);
        adviceSouthPanel.add(button2);
    }

    // MODIFIES: this
    // EFFECTS: constructs advice panel with given contents
    private void adviceScreen() {
        advicePanel = new JPanel(new BorderLayout());
        adviceNorthPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        JLabel header = new JLabel("Welcome to Paimon's Advice corner!", SwingConstants.CENTER);
        header.setFont(new Font("HYWenHei", Font.BOLD, 15));
        JLabel header2 = new JLabel("Enter a vision for a team suggestion:");
        adviceNorthPanel.add(header, BorderLayout.NORTH);
        addLabelAdvice(header2);
        JLabel label = new JLabel("Vision:");
        addLabelAdvice(label);
        input = new JTextField(10);

        output = new JTextField("You have not entered a vision yet.");
        output.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        output.setHorizontalAlignment(JTextField.CENTER);

        advicePanel.add(adviceNorthPanel, BorderLayout.NORTH);
        adviceSouthPanel();
        addToAdvicePanel(input, generate, output, homeButton);
    }

    // MODIFIES: this
    // EFFECTS: constructs the south panel of the advice screen with given content
    private void adviceSouthPanel() {
        adviceSouthPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        advicePanel.add(adviceSouthPanel, BorderLayout.SOUTH);

        generate = new JButton("Generate");
        generate.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        generate.setPreferredSize(new Dimension(150, 75));
        generate.addActionListener(this);
        generate.setActionCommand("Generate");

        homeButton = new JButton("Return to home screen");
        homeButton.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        homeButton.addActionListener(this);
        homeButton.setActionCommand("Return to home screen");
    }

    // MODIFIES: this
    // EFFECTS: resets text fields
    private void resetText() {
        requestFocusInWindow();
        input.setText("");
    }

    // MODIFIES: this
    // EFFECTS: prints out suggestions based on user input from text field
    private void printSuggestions() {
        if (input.getText().equals("cryo")) {
            resetText();
            output.setText("Cryo team suggestion: Permafreeze team -> cryo, cryo, anemo, hydro");
        } else if (input.getText().equals("pyro") || input.getText().equals("hydro")) {
            resetText();
            output.setText("Pyro/Hydro team suggestion: Vapourize team -> pyro/hydro, pyro/hydro, anemo, free");
        } else if (input.getText().equals("electro")) {
            resetText();
            output.setText("Electro team suggestion: Taser team -> electro, electro, anemo, hydro");
        } else if (input.getText().equals("geo")) {
            resetText();
            output.setText("Geo team suggestion: Mono-Geo team -> All around geo team.");
        } else if (input.getText().equals("anemo")) {
            resetText();
            output.setText("Anemo team suggestion (main dps): Dream team -> anemo, anemo, free, free");
        } else if (input.getText().equals("dendro")) {
            resetText();
            output.setText("There are currently no playable dendro characters!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds label to given panel with given properties
    private void addLabelAdvice(JLabel label) {
        label.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        adviceNorthPanel.add(label, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds panel, initializes, and displays advice screen
    private void displayAdviceScreen() {
        playSound("./data/click.wav");
        add(advicePanel, BorderLayout.CENTER);

        AudioPlayer.player.stop(backgroundMusic);

        advicePanel.setVisible(true);
        homeScreen.setVisible(false);
        addCharacterPanel.setVisible(false);
        viewCharacterPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs view character screen with given contents
    private void viewCharacterScreen() {
        viewCharacterPanel = new JPanel(new BorderLayout());
        viewCharacterPanel.setBackground(Color.white);

        headerPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        headerPanel.setBackground(Color.WHITE);

        viewCharacterNorthPanel();
        teamList = new JList(listModel);
        teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teamList.setLayoutOrientation(JList.VERTICAL);
        teamList.setVisibleRowCount(-1);
        teamList.setFont(new Font("HYWenHei", Font.PLAIN, 15));
        headerPanel.add(teamList);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JLabel venti = new JLabel(new ImageIcon("./data/venti.png"));
        homeButton = new JButton("Return to home screen");
        homeButton.setPreferredSize(new Dimension(150, 75));
        homeButton.setFont(new Font("HYWenHei", Font.PLAIN, 12));
        homeButton.addActionListener(this);
        homeButton.setActionCommand("Return to home screen");
        buttonPanel.add(homeButton);
        buttonPanel.add(venti);

        viewCharacterPanel.add(headerPanel, BorderLayout.NORTH);
        viewCharacterPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: constructs north panel with given header text for view character screen
    private void viewCharacterNorthPanel() {
        JLabel titleLabel = new JLabel("Party Setup:");
        titleLabel.setFont(new Font("HYWenHei", Font.BOLD, 15));
        headerPanel.add(titleLabel);

    }

    // MODIFIES: this
    // EFFECTS: initializes and displays view character screen
    private void displayViewCharacterScreen() {
        playSound("./data/click.wav");
        add(viewCharacterPanel);

        AudioPlayer.player.stop(backgroundMusic);

        viewCharacterPanel.setVisible(true);
        homeScreen.setVisible(false);
        addCharacterPanel.setVisible(false);
        advicePanel.setVisible(false);
    }

    // Referenced from https://stackoverflow.com/questions/59143706/save-jlist-into-txt-file
    //MODIFIES: this
    // EFFECTS: saves team to file
    private void saveTeam() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(TEAM_FILE));
            int size = teamList.getModel().getSize();
            for (int i = 0; i < size; i++) {
                bw.write(listModel.getElementAt(i).toString());
                bw.newLine();
            }
            bw.close();
            JOptionPane.showMessageDialog(null, "Successfully saved team!");
            System.out.println("Successfully saved team to " + TEAM_FILE);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to write to file: " + TEAM_FILE);
        }
    }

    // Referenced from https://stackoverflow.com/questions/5930353/how-to-load-a-txt-file-into-a-jlist
    // MODIFIES: this, team
    // EFFECTS: loads team from file
    private void loadTeam() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            Vector<String> team = new Vector<>();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                team.add(line);
            }
            reader.close();
            JOptionPane.showMessageDialog(null,
                    "Successfully loaded team!");

            headerPanel.remove(teamList);

            list = new JList(team);
            list.setFont(new Font("HYWenHei", Font.PLAIN, 15));
            headerPanel.add(list);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read from file: " + TEAM_FILE);
        }
    }

    // Method referenced from https://github.com/pkhiani/AuctionApp
    // EFFECTS: reads audio from file and plays it
    private void playSound(String sound) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(sound));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("ERROR: Could not play sound!");
        }
    }

    // Referenced from https://www.youtube.com/watch?v=3q4f6I5zi2w
    // EFFECTS: reads audio from file and plays it
    private void playBackgroundMusic() {
        InputStream music;
        try {
            music = new FileInputStream("./data/genshin theme.wav");
            backgroundMusic = new AudioStream(music);
            AudioPlayer.player.start(backgroundMusic);
        } catch (Exception e) {
            System.out.println("ERROR: Could not play!");
        }
    }

    // EFFECTS: exits app and prints event log to console
    private void onExit() {
        printLog(EventLog.getInstance());

        System.exit(0);
    }

    // EFFECTS: prints events in event log
    public void printLog(EventLog el) {
        for (Event e: el) {
            System.out.println(e.getDescription() + " on " + e.getDate());
        }
    }

    // MODIFIES: this
    // EFFECTS: assigns action for each button command
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add characters to a team")) {
            displayAddCharacterScreen();
        } else if (e.getActionCommand().equals("View current team")) {
            displayViewCharacterScreen();
        } else if (e.getActionCommand().equals("Return to home screen")) {
            returnToHome();
        } else if (e.getActionCommand().equals("Add character")) {
            addCharacterToTeam();
        } else if (e.getActionCommand().equals("Paimon's team advice corner")) {
            displayAdviceScreen();
        } else if (e.getActionCommand().equals("Save team")) {
            saveTeam();
        } else if (e.getActionCommand().equals("Load team")) {
            loadTeam();
        } else if (e.getActionCommand().equals("End current session")) {
            onExit();
        } else if (e.getActionCommand().equals("Generate")) {
            printSuggestions();
        } else if (e.getActionCommand().equals("Reset team")) {
            resetTeam();
        }
    }
}


