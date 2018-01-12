package mi.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class JDCConnectionPool {
    private class ConnectionReaper extends Thread {

        private JDCConnectionPool pool;
        private final long delay = 300000;

        ConnectionReaper(JDCConnectionPool pool) {
            this.pool = pool;
        }

        public void run() {
            while (true) {
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                }
                pool.reapConnections();
            }
        }
    }

    private Vector connections;
    private String url, user, password;
    final private long timeout = 600000000;
    private ConnectionReaper reaper;
    final private int poolsize = 10;

    public JDCConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        connections = new Vector(poolsize);
        reaper = new ConnectionReaper(this);
        reaper.start();
    }

    public synchronized void reapConnections() {

        long stale = System.currentTimeMillis() - timeout;
        Enumeration connlist = connections.elements();

        while ((connlist != null) && (connlist.hasMoreElements())) {
            JDCConnection conn = (JDCConnection) connlist.nextElement();

            if ((conn.inUse()) && (stale > conn.getLastUse()) &&
                    (!conn.validate())) {

                try {
                    conn.close();
                    removeConnection(conn);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void closeConnections() {

        Enumeration connlist = connections.elements();

        while ((connlist != null) && (connlist.hasMoreElements())) {
            JDCConnection conn = (JDCConnection) connlist.nextElement();
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            removeConnection(conn);
        }
    }

    private synchronized void removeConnection(JDCConnection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        connections.removeElement(conn);
    }


    public synchronized Connection getConnection() throws SQLException {

        JDCConnection c;

        try {
            for (int i = 0; i < connections.size(); i++) {
                c = (JDCConnection) connections.elementAt(i);
                if (c.lease()) {
                    return c;
                }
            }

        } catch (Exception t) {

            System.out.println(" t " + t.getMessage());


        }
        System.out.println(" attempting to create new connection ");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            c = new JDCConnection(conn, this);
            c.lease();
            connections.addElement(c);
            return c;

        } catch (Exception e) {
            System.out.println(" unable to open new connection ");
            e.printStackTrace();
        }
        return null;

    }

    public synchronized void returnConnection(JDCConnection conn) {
        conn.expireLease();
    }
}
