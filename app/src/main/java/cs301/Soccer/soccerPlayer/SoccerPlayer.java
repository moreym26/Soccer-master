package cs301.Soccer.soccerPlayer;

/**
 * Models a soccer player, with statistics
 *
 * @author Vegdahl
 * @author Nuxoll
 *
 */
public class SoccerPlayer {

    // instance variables
    public String firstName; // first name
    public String lastName; // last name
    public int uniformNum; // uniform number
    public int goalsScored; // goals scored
    public int yellowCards; // yellow cards shown
    public int redCards; // red cards shown
    public String teamName; // name of team

    /**
     * SoccerPlayer constructor.  The parameters are copied in. Remaining fields
     * are set to zero.
     *
     * @param first first name
     * @param last last name
     * @param uniform uniform number
     * @param team name of team
     */
    public SoccerPlayer(String first, String last, int uniform, String team) {
        firstName = first;
        lastName = last;
        uniformNum = uniform;
        teamName = team;
        goalsScored = 0;
        yellowCards = 0;
        redCards = 0;
    }

    // "get methods"
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getUniform() {
        return uniformNum;
    }
    public int getGoals() {
        return goalsScored;
    }
    public int getYellowCards() {
        return yellowCards;
    }
    public int getRedCards() {
        return redCards;
    }
    public String getTeamName() {
        return teamName;
    }

    // change uniform number to new one
    public void changeUniform(int newNumber) {
        uniformNum = newNumber;
    }

    // "bump" methods, which increment the various stat fields
    public void bumpGoals() {
        goalsScored++;
    }
    public void bumpYellowCards() {
        yellowCards++;
    }
    public void bumpRedCards() {
        redCards++;
    }
}
