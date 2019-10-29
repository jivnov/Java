import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(Photo photoUrl){ // String change to Photo
        this.photo = photoUrl;
        return this;
    }

    Section addSection(String sectionTitle){

        // utwórz sekcję o danym tytule i dodaj do sections
        return ???;
    }

    Document addSection(Section s){
        return this;
    }


    void writeHTML(PrintStream out){
    // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
    }
}
