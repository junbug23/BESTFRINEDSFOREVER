//AUTHOR#This is JUN BROOKS' CODE
package matchmaking;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        // Show interest options
        System.out.println("Pick your interests by number:");
        System.out.println("1: music, sports, hiking");
        System.out.println("2: music, movies");
        System.out.println("3: sports, reading");
        System.out.println("4: cooking");
        System.out.print("Enter interest choice (1-4): ");

        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
        }

        // Map number to interest set
        String interests;
        switch (choice) {
            case 1: interests = "music, sports, hiking"; break;
            case 2: interests = "music, movies"; break;
            case 3: interests = "sports, reading"; break;
            case 4: interests = "cooking"; break;
            default: interests = "";
        }

        User currentUser = new User(username, interests);

        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.add(currentUser);
        allUsers.add(new User("bob", "music, sports, hiking, movies"));
        allUsers.add(new User("charlie", "music, movies"));
        allUsers.add(new User("diana", "sports, reading"));
        allUsers.add(new User("eve", "cooking"));

        ArrayList<User> matches = MatchHelper.getTopMatches(currentUser, allUsers);

        System.out.println("\nMatches for " + currentUser.getUsername() + ":");
        for (User match : matches) {
            System.out.println(match);
        }

        scanner.close();
    }
}
