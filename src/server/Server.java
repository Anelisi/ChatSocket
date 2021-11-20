package server;

import clients.ClientHendler;

import java.io.IOException;
import java.net.ServerSocket;

import static java.lang.System.*;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public  void startServer() {
        try {
            while (!serverSocket.isClosed()) {
               final var socket = serverSocket.accept();

                out.println("A new cliente has connected...");
                ClientHendler clientHendler = new ClientHendler(socket);

                Thread thread = new Thread(clientHendler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();

    }

}
