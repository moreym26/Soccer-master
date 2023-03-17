package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author Malory Morey
 * @version 3/16/2023
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    private Hashtable<String, SoccerPlayer> database = new Hashtable<>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName, int uniformNumber, String teamName) {

       String key = firstName + " ## "+ lastName;
        if (database.containsKey(key)) {
            return false;
        }
        else {
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(key, newPlayer);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = firstName + " ## "+ lastName;
        if (database.containsKey(key)) {
            database.remove(key);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = firstName + " ## "+ lastName;
        if (database.containsKey(key)) {
            return database.get(key);
        }
        else {
            return null;
        }

    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = firstName + " ## "+ lastName;
        SoccerPlayer newPlayer = database.get(key);
        if (database.containsKey(key)) {
            newPlayer.goalsScored++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = firstName + " ## "+ lastName;
        SoccerPlayer newPlayer = database.get(key);
        if (database.containsKey(key)) {
            newPlayer.yellowCards++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = firstName + " ## "+ lastName;
        SoccerPlayer newPlayer = database.get(key);
        if (database.containsKey(key)) {
            newPlayer.redCards++;
            return true;
        }
        else {
            return false;
        }    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int numbPlayers = 0;
        if (teamName == null) {
            return database.size();
        }
        else {
            for (SoccerPlayer newPlayer: database.values()) {
                if (newPlayer.getTeamName().equals(teamName)) {
                    numbPlayers++;
                }
            }


        }
        return numbPlayers;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        int numbPlayer = 0;
        if (teamName == null) {
            for (SoccerPlayer newPlayer: database.values()) {
                numbPlayer++;
                if (idx ==0) {
                    return newPlayer;
                }
                else if (idx == numbPlayer) {
                    return newPlayer;
                }
            }
        }
        else if (idx > database.size()) {
            return null;
        }
        else {
            for (SoccerPlayer newPlayer: database.values()) {
                if (newPlayer.getTeamName().equals(teamName)) {
                    numbPlayer++;
                    if (idx == 0) {
                        return newPlayer;
                    }
                    else if (idx == numbPlayer) {
                        return newPlayer;
                    }
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String firstName = scanner.nextLine();
                String lastName = scanner.nextLine();
                String teamName = scanner.nextLine();
                int uniform = Integer.parseInt(scanner.nextLine());
                int goals = Integer.parseInt(scanner.nextLine());
                int yellowCards = Integer.parseInt(scanner.nextLine());
                int redCards = Integer.parseInt(scanner.nextLine());
                SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniform, teamName);
                for (int i = 0; i < goals; i ++ ) {
                    newPlayer.bumpGoals();
                }
                for (int i = 0; i < redCards; i ++ ) {
                    newPlayer.bumpRedCards();
                }
                for (int i = 0; i < yellowCards; i ++ ) {
                    newPlayer.bumpYellowCards();
                }
                String key = firstName + " " + lastName;
                database.put(key, newPlayer);
            }


        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
           return false;
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (SoccerPlayer newPlayer: database.values()) {
            printWriter.println(logString(newPlayer.getFirstName()));
            printWriter.println(logString(newPlayer.getLastName()));
            printWriter.println(logString(newPlayer.getTeamName()));
            printWriter.println(logString(Integer.toString(newPlayer.getUniform())));
            printWriter.println(logString(Integer.toString(newPlayer.getGoals())));
            printWriter.println(logString(Integer.toString(newPlayer.getYellowCards())));
            printWriter.println(logString(Integer.toString(newPlayer.getRedCards())));
        }
            printWriter.close();
            return true;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
