package app.server;

import core.dao.UserDao;
import core.dao.LabWorkDao;
import storage.postgres.PostgresUserDao;
import storage.postgres.PostgresLabWorkDao;
import storage.mongo.MongoUserDao;
import storage.mongo.MongoLabWorkDao;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class ServerMain {
    private static final Logger log = Logger.getLogger("ServerMain");

    public static void main(String[] args) throws Exception {
        // выбираем источник по флагу
        boolean usePg = false;
        for (String a : args) {
            if ("--postgres".equalsIgnoreCase(a)) usePg = true;
            if ("--mongo".equalsIgnoreCase(a))    usePg = false;
        }

        UserDao udao = usePg
                ? new PostgresUserDao()
                : new MongoUserDao();
        LabWorkDao lwDao = usePg
                ? new PostgresLabWorkDao()
                : new MongoLabWorkDao();

        int port = 12345;
        try (ServerSocket ss = new ServerSocket(port)) {
            log.info("Listening on port " + port);
            ForkJoinPool pool = new ForkJoinPool(4);
            while (true) {
                Socket sock = ss.accept();
                log.info("Accepted connection from " + sock.getInetAddress());
                pool.execute(new Session(sock, udao, lwDao));
            }
        }
    }
}
