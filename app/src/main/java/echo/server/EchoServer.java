package echo.server;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    public static ServerSocket startServer() {
        try {
            System.out.println("Creating Socket...");
            ServerSocket server_socket = createSocket();
            System.out.println("Socket Created!");
            return server_socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static ServerSocket createSocket() throws IOException {
        return new ServerSocket(0);
    }
}
