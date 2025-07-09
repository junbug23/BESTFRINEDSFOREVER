//#AUTHOR: This is JUN BROOKS' CODE

package matchmaking;

public class User {
    private String username;
    private String interests;  // comma separated interests
    private int matchScore;

    //Constructs a new User object with the specified username and interests.
    //The matchScore is initialized to 0.
    public User(String username, String interests) {
        this.username = username;
        this.interests = interests;
        this.matchScore = 0;
    }

    //Returns the username of the user.
    public String getUsername() {
        return username;
    }

    //Returns the interests of the user.
    public String getInterests() {
        return interests;
    }

    //Returns the match score of the user. This score represents how well this user matches with another user.
    public int getMatchScore() {
        return matchScore;
    }

    //Sets the match score for the user.
    public void setMatchScore(int score) {
        this.matchScore = score;
    }

    //Returns a string representation of the User object.
    //The format is "username (interests) - Shared Interests: matchScore".
    public String toString() {
        return username + " (" + interests + ") - Shared Interests: " + matchScore;
    }
}