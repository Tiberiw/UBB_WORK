package org.map.ui;

import org.map.domain.Friendship;
import org.map.domain.User;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.service.FriendshipService;
import org.map.service.UserService;

import java.util.Scanner;

public class ConsoleUI {

    private UserService userService;
    private FriendshipService friendshipService;
    private static final Scanner userInput = new Scanner(System.in);

    public ConsoleUI(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
    }

    private void addUser(String[] params) {
        if(params.length != 3) {
            System.out.println("Provide 2 parameters!");
            return;
        }

        String firstName = params[1];
        String lastName = params[2];

        User newUser = userService.saveToRepository(firstName,lastName);
        System.out.println(newUser.toString());
        System.out.println("User created!");
    }

    private void rmUser(String[] params) {
        if(params.length != 2) {
            System.out.println("Provide 1 parameter!");
            return;
        }
        String id = params[1];
        User oldUser = userService.getFromRepository(Long.valueOf(id));
        friendshipService.removeAllFriends(oldUser);
        var user = userService.removeFromRepository(Long.valueOf(id));
        System.out.println(oldUser.toString());
        System.out.println("User removed");
    }

    private void getAllUsers() {
        System.out.println("All Users:");
        userService.getAll().forEach(user -> System.out.println(user.toString()));
    }

    private void addFriendship(String[] params) {
        if(params.length != 3) {
            System.out.println("Provide 2 parameters!");
            return;
        }

        Long firstId = Long.valueOf(params[1]);
        Long secondId = Long.valueOf(params[2]);

        Friendship newFriendship = friendshipService.saveToRepository(firstId, secondId);
        System.out.println(newFriendship.toString());
        System.out.println("Friendship added!");
    }

    private void rmFriendship(String[] params) {
        if(params.length != 3) {
            System.out.println("Provide 2 parameters!");
            return;
        }

        Long firstId = Long.valueOf(params[1]);
        Long secondId = Long.valueOf(params[2]);

        Friendship oldFriendship = friendshipService.removeFromRepository(firstId, secondId);
        System.out.println(oldFriendship.toString());
        System.out.println("Friendship removed!");
    }

    private void getAllFriendships() {
        System.out.println("All Friendships:");
        friendshipService.getAll().forEach(friendship -> System.out.println(friendship.toString()));
    }

    private void showNetwork() {
        System.out.println(friendshipService.showNetwork());
    }

    private void showComponentsNr() {
        System.out.println(friendshipService.getCommunityNumber());
    }

    private void showComponents() {
        System.out.println(friendshipService.getCommunities());
    }

    private void showMostSocial() {
        System.out.println(friendshipService.getMostSocial());
    }

    private void addInitUsers() {
        String[] user1= {"add_user", "a", "a"};
        String[] user2 = {"add_user" , "b", "b"};
        String[] user3 = {"add_user", "c", "c"};
        String[] user4 = {"add_user", "d", "d"};
        String[] user5 = {"add_user", "e", "e"};
        String[] user6 = {"add_user", "f", "f"};
        String[] user7 = {"add_user", "r", "f"};
        String[] user8 = {"add_user", "t", "f"};
        String[] user9 = {"add_user", "h", "f"};
        addUser(user1);
        addUser(user2);
        addUser(user3);
        addUser(user4);
        addUser(user5);
        addUser(user6);
        addUser(user7);
        addUser(user8);
        addUser(user9);

        String[] friendship1 = {"add_friendship","1", "2"};
        String[] friendship2 = {"add_friendship","1", "3"};
        String[] friendship3 = {"add_friendship","3", "4"};

        String[] friendship4 = {"add_friendship","5", "6"};
        String[] friendship5 = {"add_friendship","5", "7"};
        String[] friendship6 = {"add_friendship","5", "8"};
        String[] friendship7 = {"add_friendship","5", "9"};

        addFriendship(friendship1);
        addFriendship(friendship2);
        addFriendship(friendship3);
        addFriendship(friendship4);
        addFriendship(friendship5);
        addFriendship(friendship6);
        addFriendship(friendship7);

    }


    public void run() {

        addInitUsers();

        while(true) {
            String command = userInput.nextLine();
            if(command.equalsIgnoreCase("exit"))
                return;

            if(command.equalsIgnoreCase(""))
                continue;

            String[] params = command.split("\\s");

            try {
                switch (params[0]) {

                    case "add_user":
                        addUser(params);
                        break;

                    case "rm_user":
                        rmUser(params);
                        break;

                    case "get_all_users":
                        getAllUsers();
                        break;

                    case "add_friendship":
                        addFriendship(params);
                        break;

                    case "rm_friendship":
                        rmFriendship(params);
                        break;

                    case "get_all_friendships":
                        getAllFriendships();
                        break;

                    case "show_network":
                        showNetwork();
                        break;


                    case "get_components_nr":
                        showComponentsNr();
                        break;

                    case "get_components":
                        showComponents();
                        break;

                    case "get_most_social":
                        showMostSocial();
                        break;


                    default:
                        System.out.println("Incorrect command!");
                }

            }catch(IllegalArgumentException e) {
                e.printStackTrace();
            }catch(ValidatorException | RepositoryException e) {
                System.out.println(e.getMessage());
            }




        }


    }



}
