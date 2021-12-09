package com.tto.yaus.webserver;

import java.io.IOException;
import java.net.Socket;

public interface SocketService {
    void run(Socket socket) throws IOException;
}
