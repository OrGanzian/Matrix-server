package client;


import java.util.LinkedList;
import java.util.List;

public class Test{

    //function goes to the end and then builds the every path.
    public static LinkedList<LinkedList<String>> getWays(int[][] distanceMatrix, int row, int column) {
        LinkedList<LinkedList<String>> paths = new LinkedList<LinkedList<String>>();
        if(distanceMatrix[row][column] == 0) { // we arrived to the end
            paths.add(new LinkedList<>());
            paths.get(0).add(row + "," + column);
            return paths;
        }
        LinkedList<LinkedList<String>> upPaths;
        LinkedList<LinkedList<String>> downPaths;
        LinkedList<LinkedList<String>> leftPaths;
        LinkedList<LinkedList<String>> rightPaths;
        //check if can go up
        if(row - 1 >= 0 && distanceMatrix[row][column] - 1 == distanceMatrix[row - 1][column]) {
            upPaths = getWays(distanceMatrix,row - 1,column);
            //now we add the current cell to all the paths
            for (LinkedList<String> path: upPaths) {
                path.addFirst(row + "," + column);
            }
            //add the founded path to all paths list
            paths.addAll(upPaths);
        }
        //check if can go down
        if(row + 1 < distanceMatrix.length && distanceMatrix[row][column] - 1 == distanceMatrix[row + 1][column]) {
            downPaths = getWays(distanceMatrix,row + 1,column);
            //now we add the current cell to all the paths
            for (LinkedList<String> path: downPaths) {
                path.addFirst(row + "," + column);
            }
            //add the founded path to all paths list
            paths.addAll(downPaths);
        }
        //check if can go left
        if(column - 1 >= 0 && distanceMatrix[row][column] - 1 == distanceMatrix[row][column - 1]) {
            leftPaths = getWays(distanceMatrix,row,column - 1);
            //now we add the current cell to all the paths
            for (LinkedList<String> path: leftPaths) {
                path.addFirst(row + "," + column);
            }
            //add the founded path to all paths list
            paths.addAll(leftPaths);
        }
        //check if can go right
        if(column + 1 < distanceMatrix[0].length && distanceMatrix[row][column] - 1 == distanceMatrix[row][column + 1]) {
            rightPaths = getWays(distanceMatrix,row - 1,column);
            //now we add the current cell to all the paths
            for (LinkedList<String> path: rightPaths) {
                path.addFirst(row + "," + column);
            }
            //add the founded path to all paths list
            paths.addAll(rightPaths);
        }
        return paths;
    }

    //function to check how many paths exist
    public static int numberOfPaths(int m, int n)
    {
        // If either given row number is first or
        // given column number is first
        if (m == 1 || n == 1)
            return 1;

        // If diagonal movements are allowed then
        // the last addition is required.
        return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
        // + numberOfPaths(m-1, n-1);
    }

    public static void main(String []args){
       Integer a=0;
        System.out.println(a.compareTo(0));


    }
}