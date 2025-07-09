package matchmaking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MatchHelper {

    // Calculates and returns a list of users sorted by their match score with the current user.
    // The list will not include the current user.
    public static ArrayList<User> getTopMatches(User currentUser, ArrayList<User> allUsers) {
        ArrayList<User> filtered = new ArrayList<>();

        // Iterate through all users to calculate match scores, excluding the current user
        for (User user : allUsers) {
            if (!user.getUsername().equals(currentUser.getUsername())) {
                int score = calculateMatchScore(currentUser.getInterests(), user.getInterests());
                user.setMatchScore(score); // Set the calculated score for the user
                filtered.add(user); // Add the user to the filtered list
            }
        }

        // Sort the filtered list of users in descending order based on their match score
        Collections.sort(filtered, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                // Compare in descending order (higher score first)
                return u2.getMatchScore() - u1.getMatchScore();
            }
        });

        return filtered;
    }

    // Calculates the match score between two sets of interests. The score is determined
    // by the number of common interests between the two strings. Interests are case-insensitive
    // and leading/trailing spaces are ignored.
    private static int calculateMatchScore(String interests1, String interests2) {
        // Convert interests strings to lowercase arrays, splitting by comma
        String[] a = interests1.toLowerCase().split(",");
        String[] b = interests2.toLowerCase().split(",");
        int score = 0;

        // Compare each interest from the first set with each interest from the second set
        for (String interestA : a) {
            for (String interestB : b) {
                // If interests match after trimming whitespace, increment the score
                if (interestA.trim().equals(interestB.trim())) {
                    score++;
                }
            }
        }

        return score;
    }
}