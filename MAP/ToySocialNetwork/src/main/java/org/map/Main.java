package org.map;

import org.map.domain.SocialNetwork;

public class Main {
    public static void main(String[] args) {

        SocialNetwork app = SocialNetwork.getInstance();
        app.run();

    }
}