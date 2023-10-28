package org.map.service;

import org.map.domain.Friendship;
import org.map.domain.Pair;
import org.map.domain.User;
import org.map.repository.Repository;
import org.map.utils.Graph;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FriendshipService{

    Repository<Pair<Long,Long>, Friendship> friendshipRepository;
    Repository<Long,User> userRepository;
    Graph<User> Network;
    public FriendshipService(Repository<Pair<Long,Long>, Friendship> friendshipRepository, Repository<Long,User> userRepository) {
        this.Network = new Graph<>();
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    public Friendship saveToRepository(Long el1, Long el2) {

        User user1 = userRepository.findOne(el1);
        User user2 = userRepository.findOne(el2);

        Friendship newFriendship = el1 < el2 ?
                new Friendship(user1,user2, LocalDateTime.now()) :
                new Friendship(user2,user1, LocalDateTime.now());

        return friendshipRepository.save(newFriendship);
    }

    public Friendship getFromRepository(Long idFirstUser, Long idSecondUser) {
        return friendshipRepository.findOne(new Pair<Long,Long>(idFirstUser,idSecondUser));
    }

    public Iterable<Friendship> getAll() {
        return friendshipRepository.findAll();
    }

    public Friendship updateToRepository() {
        return null;
    }


    public Friendship removeFromRepository(Long idFirstUser, Long idSecondUser) {

        Pair<Long,Long> id = idFirstUser < idSecondUser ?
                new Pair<>(idFirstUser,idSecondUser) :
                new Pair<>(idSecondUser,idFirstUser);

        //Remove friendship from repository
        return friendshipRepository.delete(id);
    }

    public void removeAllFriends(User user) {
        loadNetwork();
        //Get all friends of a user
        List<User> allNeighbours = Network.getAllNeighbours(user);

        //Remove all friendships of the user
        allNeighbours.forEach( e -> removeFromRepository(user.getID(),e.getID()));
    }

    private void loadNetwork() {

        List<Friendship> allEdges = new ArrayList<>();
        friendshipRepository.findAll().forEach(allEdges::add);

        //Convert friendship object into a pair of Users
        var list = allEdges.stream().map(el -> new Pair<User,User>(el.getFirstUser(),el.getSecondUser()))
                .toList();

        //Get all users (nodes)
        List<User> allVertices = new ArrayList<>();
        userRepository.findAll().forEach(allVertices::add);

        //Set network adjacency list
        Network.setAdjacencyList(list, allVertices);
    }

    public String showNetwork() {
        loadNetwork();
        return Network.toString();
    }

    public String getCommunities() {
        loadNetwork();
        return Network.showConnectedComponents();
    }

    public Integer getCommunityNumber() {
        loadNetwork();
        return Network.getConnectedComponentsNumber();
    }

    public String getMostSocial() {
        loadNetwork();
        return Network.showMostSocial();
    }

}
