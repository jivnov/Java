import java.io.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        AdminUnitList list = new AdminUnitList();
        list.read("/Users/andreizhyunou/IdeaProjects/Obiektowe/lab6/src/titanic-part.csv");
        System.out.println("Default:");
        list.list(System.out, 0, 5);
        list.sortInplaceByName();
        System.out.println("Sorted by name:");
        list.list(System.out, 0, 5);
        System.out.println("Filtered:");
        list.filter(a->a.name.startsWith("K")).list(System.out, 0, 5);
        System.out.println("Query:");
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);
    }
}