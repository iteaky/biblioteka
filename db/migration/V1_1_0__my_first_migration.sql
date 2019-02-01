

      CREATE SEQUENCE  IF NOT EXISTS INTEGERID START WITH 1 INCREMENT BY 1;

      CREATE TABLE IF NOT EXISTS PUBLISHING (
                   ID INT NOT NULL PRIMARY KEY,
                   NAME VARCHAR(255) NOT NULL,
                   CITY VARCHAR(255) NOT NULL,
                   PHONE VARCHAR(255) NOT NULL,
                   EMAIL VARCHAR(255) NOT NULL
                   );

        CREATE TABLE IF NOT EXISTS AUTHOR (
                   ID INT NOT NULL PRIMARY KEY,
                   NAME VARCHAR(255) NOT NULL,
                   SECONDNAME VARCHAR(255) NOT NULL,
                   BIRTHDAY DATE,
                   );

        CREATE TABLE IF NOT EXISTS BOOK (
                  ID INT NOT NULL PRIMARY KEY,
                  NAME VARCHAR(255) NOT NULL,
                  DATEOFREALISE DATE,
                  PUBLISHINGID INT NOT NULL ,
                  CONSTRAINT FK_PUBLISHINGID FOREIGN KEY (PUBLISHINGID)
                  REFERENCES PUBLISHING(ID)
                  );


       CREATE TABLE IF NOT EXISTS AUTHORANDBOOK (
                ID INT NOT NULL PRIMARY KEY ,
                AUTHORID INT NOT NULL ,
                BOOKID INT NOT NULL ,
                CONSTRAINT FK_AUTHORID FOREIGN KEY (AUTHORID)
                REFERENCES AUTHOR(ID),
                CONSTRAINT FK_BOOKID FOREIGN KEY (BOOKID)
                REFERENCES BOOK(ID)
                );