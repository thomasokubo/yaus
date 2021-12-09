package com.tto.yaus.webserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@ExtendWith(MockitoExtension.class)
public class WebServerTest {
    @Test
    public void acceptRequest() throws IOException, InterruptedException {
        int port = 8001;
        SocketService service = new FakeSocketService();
        WebServer webServer = new WebServer(port, service);

        webServer.start();

        Socket socket = new Socket("localhost", port);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF("Message received!");
        output.flush();
        output.close();

        webServer.stop();
    }

    private class FakeSocketService implements SocketService {
        @Override
        public void run(Socket socket) throws IOException {
            DataInputStream input = new DataInputStream(socket.getInputStream());

            System.out.println(input.readUTF());

            input.close();
            socket.close();
        }
    }
}
