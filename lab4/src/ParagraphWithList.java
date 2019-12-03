import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();

    ParagraphWithList(String text)
    {
        super(text);
    }

    ParagraphWithList addItem(String item)
    {
        list.addItem(item);
        return this;
    }

    @Override
    void writeHTML(PrintStream out)
    {
        super.writeHTML(out);
        list.writeHTML(out);
    }
}