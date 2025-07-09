//#AUTHOR This is JUN BROOKS' CODE
package matchmaking;

public class UserInputSimulator {

    // Simulate user choosing username and interests by method parameters
    // This method creates and returns a User object based on the provided username and an integer choice
    // that maps to a predefined set of interests.
    public User getUserProfile(String usernameChoice, int interestChoice) {
        String interests = "";

        // Assigns a string of interests based on the numerical interestChoice.
        switch (interestChoice) {
            case 1:
                interests = "music, sports, hiking";
                break;
            case 2:
                interests = "music, movies";
                break;
            case 3:
                interests = "sports, reading";
                break;
            case 4:
                interests = "cooking";
                break;
            default:
                interests = "music"; // Default interests if the choice is not recognized.
        }

        // Creates and returns a new User object with the chosen username and determined interests.
        return new User(usernameChoice, interests);
    }
}