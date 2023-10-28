package org.map.utils;

import org.map.domain.Entity;
import org.map.domain.Pair;

import javax.naming.PartialResultException;
import java.nio.file.SimpleFileVisitor;
import java.util.*;
import java.util.stream.Collectors;

public class Graph<E extends Entity<?>> {

   private final Map<E, List<E>> adjacencyList;

    public Graph() {
       adjacencyList = new HashMap<>();
   }

   public void setAdjacencyList(List<Pair<E,E>> edges, List<E> vertices) {
       adjacencyList.clear();

       vertices.forEach(this::addVertex);
       edges.forEach( el -> addEdge(el.getFirst(),el.getSecond()));

   }

   public void addVertex(E vertex) {
        if(!adjacencyList.containsKey(vertex))
            adjacencyList.put(vertex, new ArrayList<E>());
   }

   public void addEdge(E source, E destination) {

       if(!adjacencyList.containsKey(source))
           addVertex(source);

       if(!adjacencyList.containsKey(destination))
           addVertex(destination);


       //bidirectional
       adjacencyList.get(source).add(destination);
       adjacencyList.get(destination).add(source);
   }

   public Integer getVertexCount() {
       return adjacencyList.keySet().size();
   }

   public Integer getEdgesCount() {
       return adjacencyList.values()
               .stream()
               .mapToInt(List::size)
               .sum() / 2;
   }

   public boolean hasVertex(E vertex) {
       return adjacencyList.containsKey(vertex);
   }

   public boolean hasEdge(E source, E destination) {
       return adjacencyList.get(source).contains(destination);
   }


   @Override
   public String toString() {
       StringBuilder builder = new StringBuilder();

       for(E vertex:  adjacencyList.keySet()) {
           builder.append(vertex.getID());
           builder.append(": ");
           for(E neighbour : adjacencyList.get(vertex) ) {
               builder.append(neighbour.getID());
               builder.append(" ");
           }
           builder.append("\n");
       }

       return builder.toString();
   }

   public List<E> getAllNeighbours(E verice) {
       return adjacencyList.get(verice);
   }

   public List<E> getAllVertices() {
       return new ArrayList<>(adjacencyList.keySet());
   }

/*   List<Pair<E,E>> getAllEdges() {

   }*/

    public Integer getConnectedComponentsNumber() {
        return getConnectedComponents().size();
    }

    public String showConnectedComponents() {
        List<List<E>> components = getConnectedComponents();
        StringBuilder componentsString = new StringBuilder();
        //Can I get the index?

        for(int i = 0; i < components.size(); i++) {

            componentsString.append(i+1);
            componentsString.append(": ");
            components.get(i).forEach( el -> {
                componentsString.append(el.getID());
                componentsString.append(" ");
            });
            componentsString.append("\n");

        }


        return componentsString.toString();
    }

    public List<List<E>> getConnectedComponents() {

        Map<E,Boolean> visited = new HashMap<>();
        adjacencyList.keySet().forEach( v -> visited.put(v,false));

        List<List<E>> components = new ArrayList<>();

        adjacencyList.keySet().forEach( v -> {
            if(!visited.get(v)) {
                //System.out.println(v.getID());
                components.add(getConnectedComponent(v,visited));
            }
        });

        return components;
    }

    public List<E> getConnectedComponent(E start, Map<E,Boolean> visited) {
        List<E> components = new LinkedList<>();
        DFS(start,components,visited);
        return components;
    }

    private void DFS(E currentVertex, List<E> components, Map<E,Boolean> visited) {

        visited.replace(currentVertex,true);
        components.add(currentVertex);
        adjacencyList.get(currentVertex).forEach( neighbour -> {
            if(!visited.get(neighbour)) {
                visited.replace(neighbour,true);
                DFS(neighbour,components,visited);
            }
        });
    }


    public String showMostSocial() {

        StringBuilder result = new StringBuilder();
        List<E> mostSocial = getMostSocial();
        if(mostSocial.isEmpty()) {
            return "There are no communities in the network";
        }

        mostSocial.forEach( e -> {
            result.append(e.getID());
            result.append(" ");
        });

        return result.toString();

    }


    public List<E> getMostSocial() {

        //Get all Connected Components
        List<List<E>> components = getConnectedComponents();

        //Index of the most social component
        int indexResult = 0;
        int maxLength = -1;

        for(int i = 0; i < components.size(); i++) {

            int length = getComponentLength(components.get(i));
            if(length > maxLength) {
                maxLength = length;
                indexResult = i;
            }

        }

        if(maxLength == -1)
            return new LinkedList<>();

        return components.get(indexResult);
    }

    int getComponentLength(List<E> components) {

        Map<E,Boolean> visited = new HashMap<>();
        adjacencyList.keySet().forEach( v -> visited.put(v,false));

        int maxLength = -1;
        for( var el : components) {
            int len = DFS2(el,visited);
            if(len > maxLength)
                maxLength = len;
        }


        return maxLength;
    }

    private int DFS2(E currentVertex, Map<E,Boolean> visited) {

        visited.replace(currentVertex,true);
        int longestPath = 0;

        for( var neighbour : adjacencyList.get(currentVertex)) {
            if(!visited.get(neighbour)) {

               int pathLength = 1 + DFS2(neighbour,visited);
               longestPath = Math.max(longestPath,pathLength);
            }
        }

        visited.replace(currentVertex,false);
        return longestPath;

    }



}
