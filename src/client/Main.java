package client;

import server.Index;
import server.Matrix;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main {

     private int[][] source = {
            {1,1,1},
            {1,1,1},
            {0,0,0}


    };



    //Collection<Collection<Index>> findPaths(Matrix matrix) {
    LinkedList<LinkedList<Index>> findAllPaths() {

         Matrix matrix = new Matrix(this.source);

        int size=matrix.getPrimitiveMatrix().length;



        Index start = new Index(0, 0);
        Index end = new Index(1, 1);

        LinkedList<LinkedList<Index>> allPaths = new LinkedList<>();



        if(matrix.getValue(start)==0||matrix.getValue(end)==0){
            return allPaths;
        }


        Queue< LinkedList<Index> > queue =new LinkedList();

        HashSet<Index> visited = new HashSet<Index>();

        LinkedList<Index> startList = new LinkedList<Index>();
        startList.add(start);
        queue.add(startList);

        while (!queue.isEmpty()) {
            LinkedList<Index> curr = queue.poll();
            Index lastIndex = curr.getLast();
            if (lastIndex.equals(end)) {
                allPaths.add(curr);
                continue;
            }

            LinkedList<Index> lastIndexNeighbors = (LinkedList<Index>) matrix.getReachables(lastIndex);
            int numOfNeighbors = lastIndexNeighbors.size();
            if (numOfNeighbors == 0) {
                continue;
            }

            for (int i = 0; i <numOfNeighbors ; i++) {
                LinkedList<Index> ListToPush= (LinkedList<Index>) curr.clone();
                if (visited.contains(lastIndexNeighbors.get(i))) {
                    continue;
                }
                ListToPush.addLast(lastIndexNeighbors.get(i));
                queue.add(ListToPush);

            }

            visited.add(lastIndex);

        }


        return allPaths;
    }


    public static void main(String[] args) {
        Main a = new Main();
        LinkedList<LinkedList<Index>> paths = a.findAllPaths();
        if (paths.isEmpty()) {
            System.out.println("no paths exsist!!");
        }   else {
            System.out.println(paths);
        }





    }


}



