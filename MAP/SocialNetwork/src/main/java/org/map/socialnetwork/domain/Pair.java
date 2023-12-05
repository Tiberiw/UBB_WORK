package org.map.socialnetwork.domain;

import java.util.Objects;

public class Pair<E1, E2> {

    private E1 e1;
    private E2 e2;

    public Pair(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 getFirst() {
        return e1;
    }

    public E2 getSecond() {
        return e2;
    }

    public void setFirst(E1 e1) {
        this.e1 = e1;
    }

    public void setSecond(E2 e2) {
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return e1 + " " + e2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(e1, pair.e1) && Objects.equals(e2, pair.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }
}
