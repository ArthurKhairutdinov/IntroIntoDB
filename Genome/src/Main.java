import java.io.*;
import java.sql.*;

public class Main {

    public static final String DB_URL="jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_USERNAME="postgres";
    public static final String DB_PASSWORD="qwerty360";

    public static void main(String[] args) throws IOException, SQLException {
        Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        int k = 3;
        String fileName1 = "Genome_1-1.txt";
        String fileName2 = "Genome_2-1.txt";

        String dbName1 = "genome1";
        String dbName2 = "genome2";
        GenomeDAO.addGenome(con,fileName1,dbName1,k);
        GenomeDAO.addGenome(con,fileName2,dbName2,k);
        System.out.println(GenomeDAO.compareWithJ(con,dbName1,dbName2));

        k=5;
        dbName1 = "genome3";
        dbName2 = "genome4";
        GenomeDAO.addGenome(con,fileName1,dbName1,k);
        GenomeDAO.addGenome(con,fileName2,dbName2,k);
        System.out.println(GenomeDAO.compareWithJ(con,dbName1,dbName2));

        k=9;
        dbName1 = "genome5";
        dbName2 = "genome6";
        GenomeDAO.addGenome(con,fileName1,dbName1,k);
        GenomeDAO.addGenome(con,fileName2,dbName2,k);
        System.out.println(GenomeDAO.compareWithJ(con,dbName1,dbName2));
    }
}
