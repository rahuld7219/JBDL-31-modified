package com.gfg;

import com.sun.net.httpserver.HttpServer;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SampleHttpServer {

  public static void main(String[] args) {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0); // creating a server (now it is done by tomcat)
      server.createContext("/app", new  MyHttpHandler()); // accept all request types like GET, POST, DELETE,etc., Handler have the business logic to send as a response (now this is done using @RequestMapping, etc. in Spring)
      server.createContext("/hello", new HelloHandler()); // for every request to a specified path, same handler object is used(i.e, singleton), this object will be part of EJB container or spring container (whichever we are using)
      server.setExecutor(Executors.newFixedThreadPool(10));
      server.start();
      System.out.println(" Server started on port 8001");
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
