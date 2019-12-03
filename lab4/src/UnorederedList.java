import java.util.*;
import java.io.PrintStream;

class UnorderedList {
    List<String> items = new ArrayList<String>();

    UnorderedList addItem(String item)
    {
        items.add(item);
        return this;
    }

    void writeHTML(PrintStream out)
    {
        out.printf("<ul>\n");
        for (String s : items)
            out.printf("<li>%s</li>\n", s);
        out.printf("</ul>\n");
    }
}