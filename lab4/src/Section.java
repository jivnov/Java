import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;

    Section setTitle(String title){}

    Section addParagraph(String paragraphText){}

    Section addParagraph(Paragraph p){}

    void writeHTML(PrintStream out){}

}
