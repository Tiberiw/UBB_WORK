package org.map.domain;

import org.map.repository.InMemoryRepository;
import org.map.repository.Repository;
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



    private SocialNetwork() {
        Repository<Long, User> userRepository = new InMemoryRepository<>(new UserValidator());
        Repository<Pair<Long, Long>, Friendship> friendshipRepository = new InMemoryRepository<>(new FriendshipValidator());
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
