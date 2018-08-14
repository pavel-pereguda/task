import com.google.common.collect.Multimap;
import java.util.*;

/**
 * Created by Pavel on 30.07.2018.
 */
public class CreateQueries {

    public  String createTable(int rowCount) {
        int counter = 0;
        StringBuffer sb = new StringBuffer("create table task(");
        for (int i = 0; i < rowCount; i++) {
            counter++;
            sb.append(String.format("%s " + "varchar(25),", "col"+counter));
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return String.valueOf(sb);
    }
    public  String fillWithData(Multimap<Integer,String> data, int rowCount) {
        int counter = 0;
        StringBuffer sb = new StringBuffer("insert into task (");
        for (int i = 0; i < rowCount; i++) {
            counter++;
            sb.append(String.format("%s, " ,"col"+counter));
        }
        sb.setLength(sb.length() - 2);
        sb.append(") values ( ");

        Set keySet = data.keySet();
        Iterator keyIterator = keySet.iterator();
        while (keyIterator.hasNext()){
            List<String> values= (List<String>) data.get((Integer) keyIterator.next());
            for(int j=0;j<values.size();j++){
               sb.append(String.format("'%s', ", values.get(j)));
            }
            sb.setLength(sb.length() - 2);
            sb.append("),(");
        }
        sb.setLength(sb.length()-2);
        sb.append(";");

        return String.valueOf(sb);
    }
}
