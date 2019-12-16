import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt;
    String[] current;

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException
    {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException
    {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException
    {
        this(filename, ",", true);
    }

    public CSVReader(Reader freader, String delimiter, boolean hasHeader) throws IOException
    {
        this.reader = new BufferedReader(freader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader)
            parseHeader();
        else
            columnLabelsToInt = new HashMap<>();
    }

    void parseHeader() throws IOException
    {
        String line = reader.readLine();
        if (line == null)
            return;
        String[] header = line.split(delimiter);
        columnLabelsToInt = new HashMap<>(header.length);
        for (int i = 0; i < header.length; i++)
        {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    public boolean next() throws IOException
    {
        String line = reader.readLine();
        if (line == null || line.length() < 1)
            return false;
        String[] quotes = line.split("[,]{0,1}[\"']{1}[,]{0,1}");
        List<String> fields = new ArrayList<>();
        for (var i = 0; i < quotes.length; i++) {
            if (i % 2 == 1) {
                fields.add(quotes[i]);
                continue;
            }
            String[] part = quotes[i].split(delimiter);
            if (part.length > 0)
                fields.addAll(Arrays.asList(part));
        }
        current = new String[fields.size()];
        current = fields.toArray(current);
        return true;
    }

    public String get(int index)
    {
        return current[index];
    }

    public String get(String index)
    {
        return current[columnLabelsToInt.get(index)];
    }

    public int getInt(String index)
    {
        return Integer.parseInt(get(index));
    }

    public long getLong(String index)
    {
        return Long.parseLong(get(index));
    }

    public double getDouble(String index)
    {
        return Double.parseDouble(get(index));
    }

    public int getInt(int index)
    {
        return Integer.parseInt(get(index));
    }

    public long getLong(int index)
    {
        return Long.parseLong(get(index));
    }

    public double getDouble(int index)
    {
        return Double.parseDouble(get(index));
    }

    public List<String> getColumnLabels()
    {
        return columnLabels;
    }

    public int getRecordLength()
    {
        return current.length;
    }

    public boolean isMissing(int index)
    {
        if (index < 0 || index >= current.length)
            return true;
        String val = get(index);
        return val.length() <= 0;
    }

    public boolean isMissing(String index)
    {
        if (columnLabelsToInt.containsKey(index))
            return isMissing(columnLabelsToInt.get(index));
        else
            return true;
    }

    public static void main(String[] args) throws IOException
    {
        CSVReader reader = null;
        try {
            reader = new CSVReader("/Users/andreizhyunou/IdeaProjects/Obiektowe/lab6/src/titanic-part.csv", ",", true);
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        if (reader == null)
            return;
        while (reader.next())
        {
            String id = reader.get("PassengerId");
            String name = reader.get("Name");
            String fare = reader.get("Fare");
            System.out.printf("%s \t| %s \t| %s\n", id, name, fare);
        }
    }
}
