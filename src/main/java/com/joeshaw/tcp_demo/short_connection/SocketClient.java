package com.joeshaw.tcp_demo.short_connection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 10000;
        // 与服务端建立连接
        for (int i=0;i<10;i++){
            Socket socket = new Socket(host, port);
            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            String message = "你好Server,我是Client";
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println("接收到Server数据: " + sb);

            inputStream.close();
            outputStream.close();
            socket.close();
            Thread.sleep(1000*5);
        }

    }
}
