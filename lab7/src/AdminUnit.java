public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();

    public String toString() {
        return String.format("%s level:%d pop:%f.2 area:%f.2 den:%f.2\n", name, adminLevel, population, area, density);
    }
}