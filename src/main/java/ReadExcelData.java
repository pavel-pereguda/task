import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;


class ReadExcelData {

    private String filePath;
    private int rowCount;
    private Multimap<Integer, String> data;

    public ReadExcelData(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        getFileData();
    }
    @SuppressWarnings("deprecation")
    public Multimap<Integer, String> getFileData() {
        InputStream is = null;
        try {
            is = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StreamingReader reader = StreamingReader.builder()
                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .sheetIndex(0)        // index of sheet to use (defaults to 0)
                .read(is);            // InputStream or File for XLSX file (required)
        int i = 0;
        data = new ArrayListMultimap<>();
        for (Row r : reader) {
            i++;
            for (Cell c : r) {
                data.put(i, c.getStringCellValue());
                }
            }
        rowCount = data.get(1).size();
        return data;
    }
    public int getRowCount() {
        return rowCount;
    }

    public Multimap<Integer, String> getData() {
        return data;
    }
}