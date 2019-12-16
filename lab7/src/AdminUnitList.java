import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    public AdminUnitList() {}

    public AdminUnitList(Stream<AdminUnit> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    public void read(String filename) throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(filename);
        while (reader.next())
        {
            AdminUnit au = new AdminUnit();
            au.name = reader.get("name");
            au.adminLevel = reader.getInt("admin_level");
            au.population = reader.getDouble("population");
            au.area = reader.getDouble("area");
            au.density = reader.getDouble("density");
            au.bbox = new BoundingBox();
            au.bbox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
            au.bbox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
            au.bbox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
            au.bbox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
            units.add(au);
        }
    }

    public void list(PrintStream out) {
        for (var a : units) {
            out.printf(a.toString());
        }
    }

    public void list(PrintStream out, int offset, int limit) {
        int i = 0;
        int printed = 0;
        for (var a : units) {
            if (i < offset) {
                i++;
                continue;
            }
            out.printf(a.toString());
            printed++;
            if (printed >= limit)
                break;
        }
    }

    public AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList output = new AdminUnitList();
        for (var a : units) {
            if (a.name.contains(pattern) || (regex && a.name.matches(pattern)))
                output.units.add(a);
        }
        return output;
    }

    public AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    public AdminUnitList sort(Comparator<AdminUnit> cmp) {
        var clone = new AdminUnitList();
        Collections.copy(clone.units, units);
        return clone.sortInplace(cmp);
    }

    public AdminUnitList sortInplaceByName() {
        Comparator<AdminUnit> cp = Comparator.comparing(a -> a.name);
        return sortInplace(cp);
    }

    public AdminUnitList sortInplaceByArea() {
        Comparator<AdminUnit> cp = new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit adminUnit, AdminUnit t1) {
                return (int)(100*(adminUnit.area - t1.area));
            }
        };
        return sortInplace(cp);
    }

    public AdminUnitList sortInplaceByPopulation() {
        return sortInplace( (a,b) -> Double.compare(a.population, b.population) );
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred) {
        return new AdminUnitList(units.stream().filter(pred));
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        return new AdminUnitList(units.stream().filter(pred).limit(limit));
    }

    public AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        return new AdminUnitList(units.stream().filter(pred).skip(offset).limit(limit));
    }

    public AdminUnitList getNeighbours(AdminUnit unit, double maxDistance) throws ExecutionControl.NotImplementedException {
        AdminUnitList aul = new AdminUnitList();
        for (var i = 0; i < units.size(); i++)
        {
            if (units.get(0).adminLevel == unit.adminLevel)
                if (units.get(0).bbox.distanceTo(unit.bbox) < maxDistance || units.get(0).bbox.intersects(unit.bbox))
                    aul.units.add(units.get(0));
        }
        return aul;
    }
}