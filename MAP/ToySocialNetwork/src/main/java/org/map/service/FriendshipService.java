package org.map.service;

import org.map.domain.Friendship;
import org.map.domain.Pair;
import org.map.domain.User;
import org.map.repository.Repository;
import org.map.utils.Graph;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendshipService{

    Repository<Pair<Long,Long>, Friendship> friendshipRepository;
    Repository<Long,User> userRepository;
    Graph<User> Network;
    public FriendshipService(Repository<Pair<Long,Long>, Friendship> friendshipRepository, Repository<Long,User> userRepository) {
        this.Network = new Graph<>();
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    public Optional<Friendship> saveToRepository(Long el1, Long el2) {

        //Find the 2 users
        Optional<User> optionalUser1 = userRepository.findOne(el1);
        Optional<User> optionalUser2 = userRepository.findOne(el2);

        if(optionalUser1.isPresent() && optionalUser2.isPresent()) {
            Friendship newFriendship = el1 < el2 ?
                    new Friendship(optionalUser1.get(),optionalUser2.get(), LocalDateTime.now()) :
                    new Friendship(optionalUser2.get(),optionalUser1.get(), LocalDateTime.now());

            return friendshipRepository.save(newFriendship);
        }
        return Optional.empty();
    }

    public Optional<Friendship> getFromRepository(Long idFirstUser, Long idSecondUser) {
        return friendshipRepository.findOne(new Pair<>(idFirstUser,idSecondUser));
    }

    public Iterable<Friendship> getAll() {
        return friendshipRepository.findAll();
    }

    public Friendship updateToRepository() {
        return null;
    }


    public Optional<Friendship> removeFromRepository(Long idFirstUser, Long idSecondUser) {

        Pair<Long,Long> id = idFirstUser < idSecondUser ?
                new Pair<>(idFirstUser,idSecondUser) :
                new Pair<>(idSecondUser,idFirstUser);

        //Remove friendship from repository
        return friendshipRepository.delete(id);
    }

    public void removeAllFriends(User user) {
        loadNetwork();

        //Remove all friendships of the user
        Network.getAllNeighbours(user)
                .forEach( e -> removeFromRepository(user.getID(),e.getID()));
    }

    private void loadNetwork() {

        List<Friendship> allFriendships = new ArrayList<>();
        friendshipRepository.findAll().forEach(allFriendships::add);

        //Convert friendship object into a pair of Users
        var allEdges = allFriendships
                .stream()
                .map(friendship -> new Pair<>(friendship.getFirstUser(),friendship.getSecondUser()))
                .toList();

        //Get all users (nodes)
        List<User> allVertices = new ArrayList<>();
        userRepository.findAll().forEach(allVertices::add);

        //Set network adjacency list
        Network.setAdjacencyList(allEdges, allVertices);
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
