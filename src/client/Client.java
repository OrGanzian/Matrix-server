package client;

import server.Index;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public class Client {

    static int MAX_SIZE=50;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // In order to request something over TCP from a server, we need a port number and an IP address
        Socket socket = new Socket("127.0.0.1",8081);
        // socket is an abstraction of 2-way data pipe
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // use decorators
        ObjectInputStream fromServer = new ObjectInputStream(inputStream);
        ObjectOutputStream toServer = new ObjectOutputStream(outputStream);

        int[][] source = {
                {1,1,1},
                {1,0,1},
                {1,0,0},
                {0,0,0},
                {1,1,1},

        };

        toServer.writeObject("matrix");
        toServer.writeObject(source);
        toServer.writeObject("start index");
        toServer.writeObject(new Index(0,0));
        toServer.writeObject("end index");
        toServer.writeObject(new Index(2,2));

        // all SCC
        toServer.writeObject("all scc");
        LinkedList<Set<Index>> allScc = new LinkedList<Set<Index>>((Collection<? extends Set<Index>>) fromServer.readObject());
        if (allScc.isEmpty()) {
            System.out.println("There are no connected indices in this matrix.");
        } else {
            System.out.println("All SSC in the matrix : " + allScc);
        }


        // shortest path

        toServer.writeObject("shortest paths");
        try {
            LinkedList<LinkedList<Index>> paths = new LinkedList<LinkedList<Index>>((Collection<? extends LinkedList<Index>>) fromServer.readObject());
            if (source.length < MAX_SIZE) {
                if (paths.isEmpty()) {
                    System.out.println("There are no paths between these indices.");
                } else {
                    System.out.println("Only shortest paths : " + paths);
                }

            } else {
                System.out.println("Matrix is Over 50X50, please try a new matrix");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }



        // Number Of Submarines

        toServer.writeObject("submarines");
        Integer NumberOfSubmarines = new Integer((Integer) fromServer.readObject());
        System.out.println("Number Of Submarines : " + NumberOfSubmarines);

        // all paths

        toServer.writeObject("all paths");
        LinkedList<LinkedList<Index>> allPaths = new LinkedList<LinkedList<Index>>((Collection<? extends LinkedList<Index>>) fromServer.readObject());

        if (allPaths.isEmpty()) {
            System.out.println("There are no paths between these indices.");
        } else {
            System.out.println("All paths available : " + allPaths);
        }



        // close connection
        toServer.writeObject("stop");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("All streams are closed");


    }
}
