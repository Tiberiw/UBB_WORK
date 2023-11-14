package org.map.domain;

import org.map.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

public class Friendship extends Entity<Pair<Long,Long>>{

    private User user1;
    private User user2;
    private LocalDateTime date;

    public Friendship(User user1, User user2, LocalDateTime date) {
        this.user1 = user1;
        this.user2 = user2;
        this.date = date;
        this.id = createId(user1.getID(),user2.getID());
    }

    public Friendship(Pair<Long,Long> id, User user1, User user2, LocalDateTime date) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.date = date;
    }

    private static Pair<Long,Long> createId(Long userId1, Long userId2) {
        return userId1 < userId2 ? new Pair<>(userId1,userId2) : new Pair<>(userId2,userId1);
    }

    public User getFirstUser() {
        return user1;
    }

    public User getSecondUser() {
        return user2;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return date.format(Utils.formatter);
    }

    @Override
    public String toString() {
        return String.format("User1: " + user1.toString() +
                "User2: " + user2.toString() +
                "Date: " + date.format(Utils.formatter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(user1, that.user1) && Objects.equals(user2, that.user2) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user1, user2, date);
    }
}
