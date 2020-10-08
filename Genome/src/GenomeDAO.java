import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenomeDAO {
    public static void addGenome(Connection con, String fileName,String dbName, int k) throws IOException, SQLException {
        FileReader reader = new FileReader(fileName);
        int[] letters = new int[k];
        for(int i = 0; i < k; i++){
            letters[i] = reader.read();
        }
        Integer ch ;
        while((ch = reader.read())!=-1){
            String str = "";
            for(int i = 0; i < k; i++){
                str += (char) letters[i];
            }
            String sql = "insert into "+dbName+"(gen) values('"+str+"');";
            Statement statement = con.createStatement();
            statement.execute(sql);
            for(int i = 0; i < k-1; i++){
                letters[i] = letters[i+1];
            }
            letters[k-1] = ch;
        }
    }

    public static double compareWithJ(Connection con, String dbName1, String dbName2) throws SQLException {
        String sql = "select ((select count(*) from ((select gen from "+dbName1+") " +
                "intersect all (select gen from "+dbName2+")) as st1)::float8" +
                "/(select count(*) from ((select gen from "+dbName1+") " +
                "union all (select gen from "+dbName2+")) as st2)::float8);";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getDouble(1);
    }
}
