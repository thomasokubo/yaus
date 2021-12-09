package com.tto.yaus.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebServer {
    private final ExecutorService executor;
    private final ServerSocket server;
    private final SocketService service;

    public WebServer(int port, SocketService socketService) throws IOException {
        service = socketService;
        server = new ServerSocket(port);
        executor = Executors.newFixedThreadPool(4);
    }

    public void start() {
        executor.execute(() -> {
            try {
                Socket socket = server.accept();
                service.run(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void stop() throws IOException, InterruptedException {
        executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        executor.shutdown();
        server.close();
    }
}
