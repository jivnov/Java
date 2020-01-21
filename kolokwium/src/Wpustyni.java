import java.io.*;
import java.nio.charset.Charset;

public class Wpustyni {

    static String readFile(String name, Charset charset){
        StringBuilder s = new StringBuilder();
        try( BufferedReader file = new BufferedReader(new InputStreamReader( new FileInputStream(name), charset))){
            for(;;){
                int c=file.read();
                if(c<0)break;
                s.append((char)c);
            }
            return s.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String name = "/Users/andreizhyunou/IdeaProjects/Obiektowe/kolokwium/src/w-pustyni.txt";
        Charset charset = Charset.lookupExtendedCharset("cp1250");

        System.out.println(readFile(name, charset));
    }
}

