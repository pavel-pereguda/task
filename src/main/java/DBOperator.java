import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperator {

        public static void main(String[] args) {
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/taskigor?autoReconnect=true" +
                    "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String uName ="pavel";
            String pwd = "111";
            Connection conn=null;

            try {
                Class.forName(Driver);
                conn = DriverManager.getConnection(url, uName, pwd);
                Statement stmt = conn.createStatement();
                ReadExcelData excelData = new ReadExcelData("Test3.xlsx");
                CreateQueries createQueries = new CreateQueries();
                stmt.execute(createQueries.createTable(excelData.getRowCount()));
                System.out.println("CreateTable successfully");
                stmt.executeUpdate(createQueries.fillWithData(excelData.getData(),excelData.getRowCount()));
                System.out.println("Fill with data");

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally{
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }
