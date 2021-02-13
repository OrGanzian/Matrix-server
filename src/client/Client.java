package client;

import server.Index;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Client {
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
                {1,1,1},
                {1,1,1},

        };

        toServer.writeObject("matrix");
        // according to protocol, after "matrix" string, send 2d int array
        toServer.writeObject(source);

        toServer.writeObject("start index");
        toServer.writeObject(new Index(1,0));
        toServer.writeObject("end index");
        toServer.writeObject(new Index(2,2));
        toServer.writeObject("shortest path");
        LinkedList<LinkedList<Index>> paths = new LinkedList<LinkedList<Index>>((Collection<? extends LinkedList<Index>>) fromServer.readObject());
        System.out.println("Only shortest paths : " + paths);

        //       toServer.writeObject("stop");
//        toServer.writeObject(new Index(1,1));
//
//        Collection<Index> adjacentIndices = new ArrayList<>((Collection<Index>)fromServer.readObject());
//        System.out.println("Neighbors: " + adjacentIndices);
//
//        toServer.writeObject("TaskTwo");

        toServer.writeObject("stop");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("All streams are closed");


    }
}
