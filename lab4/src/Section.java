import java.util.*;
import java.io.PrintStream;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<Paragraph>();

    Section(String title)
    {
        this.title = title;
    }

    Section setTitle(String title)
    {
        this.title = title;
        return this;
    }

    Section addParagraph(String text)
    {
        return addParagraph(new Paragraph(text));
    }

    Section addParagraph(Paragraph p)
    {
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out)
    {
        out.printf("<h2>%s</h2>\n", title);
        for (Paragraph p : paragraphs)
            p.writeHTML(out);
    }
}