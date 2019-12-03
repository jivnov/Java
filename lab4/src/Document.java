import java.util.*;
import java.io.PrintStream;

public class Document {
    String title = "";
    Photo photo = new Photo("");
    List<Section> sections = new ArrayList<Section>();

    Document(String title)
    {
        this.title = title;
    }

    Document setTitle(String title)
    {
        this.title = title;
        return this;
    }

    Document setPhoto(String url)
    {
        photo = new Photo(url);
        return this;
    }

    Section addSection(String secTitle)
    {
        addSection(new Section(secTitle));
        return sections.get(sections.size() - 1);
    }

    Document addSection(Section s)
    {
        sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out)
    {
        out.printf("<h1>%s</h1>\n", title);
        photo.writeHTML(out);
        for (Section s : sections)
            s.writeHTML(out);
    }
}