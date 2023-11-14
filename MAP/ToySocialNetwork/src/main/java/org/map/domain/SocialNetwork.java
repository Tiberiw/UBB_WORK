package org.map.domain;

import org.map.repository.database.FriendshipDatabaseRepository;
import org.map.repository.Repository;
import org.map.repository.database.UserDatabaseRepository;
import org.map.service.FriendshipService;
import org.map.service.UserService;
import org.map.ui.ConsoleUI;
import org.map.validator.FriendshipValidator;
import org.map.validator.UserValidator;

public class SocialNetwork {

    private static SocialNetwork instance = null;
    UserService userService;
    FriendshipService friendshipService;
    ConsoleUI consoleUI;

    /// TODO IMPLEMENT SECOND INNER JOIN
    /// TODO IMPLEMENT MODIFY FRIENDSHIP

    private SocialNetwork() {

        String url = "jdbc:postgresql://localhost:5432/social_network";
        String username = "postgres";
        String password = "2103";
        Repository<Long, User> userRepository = new UserDatabaseRepository(url, username, password, new UserValidator());
        Repository<Pair<Long, Long>, Friendship> friendshipRepository = new FriendshipDatabaseRepository(url, username, password, new FriendshipValidator(), userRepository);
        userService = new UserService(userRepository);
        friendshipService = new FriendshipService(friendshipRepository, userRepository);
        consoleUI  = new ConsoleUI(userService,friendshipService);
    }

    public static SocialNetwork getInstance() {
       if(instance == null)
           instance = new SocialNetwork();

       return instance;
    }

    public void run() {
        consoleUI.run();
    }




}
