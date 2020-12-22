package client;

import server.Index;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

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
                {1,0,1},
                {0,0,1},
                {1,1,1}
        };

        toServer.writeObject("matrix");
        // according to protocol, after "matrix" string, send 2d int array
        toServer.writeObject(source);

        toServer.writeObject("stop");
//        toServer.writeObject(new Index(1,1));
//
//        Collection<Index> adjacentIndices = new ArrayList<>((Collection<Index>)fromServer.readObject());
//        System.out.println("Neighbors: " + adjacentIndices);
//
//        toServer.writeObject("TaskTwo");
//        toServer.writeObject(new Index(1,2));
//        Collection<Index> reachableIndices = new ArrayList<>((Collection<Index>)fromServer.readObject());
//        System.out.println("Reachables : " + reachableIndices);

        toServer.writeObject("stop");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("All streams are closed");


    }
}
