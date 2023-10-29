package org.map.ui;

import org.map.domain.Friendship;
import org.map.domain.User;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.service.FriendshipService;
import org.map.service.UserService;

import java.util.*;
import java.util.function.*;

public class ConsoleUI {

    private final UserService userService;
    private final FriendshipService friendshipService;
    private final Map<String,Consumer<String[]>> commands;
    Supplier<String> requestGetter;
    Consumer<String> requestHandler;
    private boolean run;
    public ConsoleUI(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.commands = new HashMap<>();
        this.run = true;
        initCommands();
        initRequests();
        addInitialUsers();
    }

    private void initCommands() {

        BiFunction<String[], Integer, Boolean> lengthCheck = (params,size) -> params.length == size;

        commands.put("add_user", params -> {
            if(!lengthCheck.apply(params,3)) {
                System.out.println("Provide 2 parameters!");
                return;
            }
            String firstName = params[1];
            String lastName = params[2];

            //Try to add user
            Optional<User> newUser = userService.saveToRepository(firstName,lastName);

            newUser.ifPresent( user -> {
                System.out.println(user);
                System.out.println("User created!");
            });
        });

        commands.put("rm_user", params -> {
            if(!lengthCheck.apply(params,2)) {
                System.out.println("Provide 1 parameter!");
                return;
            }

            String id = params[1];
            //Try to get user
            Optional<User> oldUser = userService.getFromRepository(Long.valueOf(id));

            oldUser.ifPresent( user -> {

                friendshipService.removeAllFriends(user);

                Optional<User> oldUser2 = userService.removeFromRepository(Long.valueOf(id));
                oldUser2.ifPresent( usr -> {
                    System.out.println(usr);
                    System.out.println("User removed");
                });

            });
        });

        commands.put("get_all_users", params -> {
            System.out.println("All Users:");
            userService.getAll().forEach(user -> System.out.println(user.toString()));
        });

        commands.put("add_friendship", params -> {
            if(!lengthCheck.apply(params,3)) {
                System.out.println("Provide 2 parameters!");
                return;
            }

            Long firstId = Long.valueOf(params[1]);
            Long secondId = Long.valueOf(params[2]);

            //Try to add friendship
            Optional<Friendship> newFriendship = friendshipService.saveToRepository(firstId, secondId);
            newFriendship.ifPresent( friendship -> {
                System.out.println(friendship);
                System.out.println("Friendship added!");
            });
        });

        commands.put("rm_friendship", params -> {
            if(!lengthCheck.apply(params,3)) {
                System.out.println("Provide 2 parameters!");
                return;
            }

            Long firstId = Long.valueOf(params[1]);
            Long secondId = Long.valueOf(params[2]);

            //Try to remove friendship
            Optional<Friendship> oldFriendship = friendshipService.removeFromRepository(firstId, secondId);

            oldFriendship.ifPresent(friendship -> {
                System.out.println(friendship);
                System.out.println("Friendship removed!");
            });

        });

        commands.put("get_all_friendships", params -> {
            System.out.println("All Friendships:");
            friendshipService.getAll().forEach(friendship -> System.out.println(friendship.toString()));
        });

        commands.put("show_network", params -> System.out.println(friendshipService.showNetwork()));

        commands.put("get_components_nr", params -> System.out.println(friendshipService.getCommunityNumber()));

        commands.put("get_components", params -> System.out.println(friendshipService.getCommunities()));

        commands.put("get_most_social", params -> System.out.println(friendshipService.getMostSocial()));

        commands.put("exit", params -> this.run = false);

    }

    private void initRequests() {
        //Get the commands from the command line
        requestGetter = new Scanner(System.in)::nextLine;

        //Process the command and call the handler function
        requestHandler = command -> {
            //Get command string and split the parameters
            String[] params = command.split("\\s");

            //Validate the command
            Predicate<String> commandValidator = this.commands::containsKey;

            //Execute the function
            if(commandValidator.test(params[0]))
                try {
                    commands.get(params[0]).accept(params);
                }catch(IllegalArgumentException | ValidatorException | RepositoryException e) {
                    System.out.println(e.getMessage());
                }
            else {
                System.out.println("Incorrect command!");
            }
        };
    }


    private void addInitialUsers() {

        Arrays.asList(
                //Initial Users
                new String[]{"add_user", "a", "a"},
                new String[]{"add_user" , "b", "b"},
                new String[]{"add_user", "c", "c"},
                new String[]{"add_user", "d", "d"},
                new String[]{"add_user", "e", "e"},
                new String[]{"add_user", "f", "f"},
                new String[]{"add_user", "r", "f"},
                new String[]{"add_user", "t", "f"},
                new String[]{"add_user", "h", "f"},

                //Initial Friendships
                new String[]{"add_friendship","1", "2"},
                new String[]{"add_friendship","1", "3"},
                new String[]{"add_friendship","3", "4"},
                new String[]{"add_friendship","5", "6"},
                new String[]{"add_friendship","5", "7"},
                new String[]{"add_friendship","5", "8"},
                new String[]{"add_friendship","5", "9"}
        ).forEach( command -> commands.get(command[0]).accept(command));
    }

    public void run() {
        while(run)
            this.requestHandler.accept(requestGetter.get());
    }
}
