package org.map;


import org.map.domain.Friendship;
import org.map.domain.Pair;
import org.map.domain.SocialNetwork;
import org.map.domain.User;
import org.map.repository.InMemoryRepository;
import org.map.repository.Repository;
import org.map.service.UserService;
import org.map.ui.ConsoleUI;
import org.map.validator.FriendshipValidator;
import org.map.validator.UserValidator;
import org.map.validator.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SocialNetwork app = SocialNetwork.getInstance();
        app.run();


    }
}