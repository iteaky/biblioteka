package service;

import org.h2.tools.Server;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.*;


/** alter sequence INTEGERID restart with 1
 *
 */
public class ConnectionUtil {
    public static final String SQL_FOR_ID = "SELECT NEXTVAL('\"biblioteka\".INTEGERID') as id";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:h2:file:~/h2/biblioteka","","");

    }


//    public static void initDataBase() throws SQLException {
//        Server server = Server.createTcpServer().start();
//        System.out.println("Server started and connection is open.");
//        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");
//        Connection connection = getConnection();
//        Statement query = connection.createStatement();
//
//        query.execute("CREATE SEQUENCE  IF NOT EXISTS INTEGERID START WITH 1 INCREMENT BY 1;");
//
//        query.execute("CREATE TABLE IF NOT EXISTS PUBLISHING (\n" +
//                "    ID INT NOT NULL PRIMARY KEY,\n" +
//                "    NAME VARCHAR(255) NOT NULL,\n" +
//                "    CITY VARCHAR(255) NOT NULL,\n" +
//                "    PHONE VARCHAR(255) NOT NULL,\n" +
//                "    EMAIL VARCHAR(255) NOT NULL,\n" +
//                ");");
//
//        query.execute("CREATE TABLE IF NOT EXISTS AUTHOR (\n" +
//                "    ID INT NOT NULL PRIMARY KEY,\n" +
//                "    NAME VARCHAR(255) NOT NULL,\n" +
//                "    SECONDNAME VARCHAR(255) NOT NULL,\n" +
//                "    BIRTHDAY DATE,\n" +
//                ");");
//
//        query.execute("CREATE TABLE IF NOT EXISTS BOOK (\n" +
//                "    ID INT NOT NULL PRIMARY KEY,\n" +
//                "    NAME VARCHAR(255) NOT NULL,\n" +
//                "    DATEOFREALISE DATE,\n" +
//                "    PUBLISHINGID INT NOT NULL ,\n" +
//                "    CONSTRAINT FK_PUBLISHINGID FOREIGN KEY (PUBLISHINGID)\n" +
//                "    REFERENCES PUBLISHING(ID),\n" +
//                ");");
//
//
//        query.execute("CREATE TABLE IF NOT EXISTS AUTHORANDBOOK (\n" +
//                "    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
//                "    AUTHORID INT NOT NULL ,\n" +
//                "    BOOKID INT NOT NULL ," +
//                "    PRIMARY KEY (ID),\n" +
//                "    CONSTRAINT FK_AUTHORID FOREIGN KEY (AUTHORID)\n" +
//                "    REFERENCES AUTHOR(ID),\n" +
//                "    CONSTRAINT FK_BOOKID FOREIGN KEY (BOOKID)\n" +
//                "    REFERENCES BOOK(ID)\n" +
//                ");");
//        connection.close();
//
//    }
}