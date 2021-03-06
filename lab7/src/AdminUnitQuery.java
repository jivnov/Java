import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    AdminUnitQuery where(Predicate<AdminUnit> pred){
        p = pred;
        src.filter(pred, offset, this.limit);
        return this;
    }

    AdminUnitQuery and(Predicate<AdminUnit> pred){
        p = p.and(pred);
        return this;
    }

    AdminUnitQuery or(Predicate<AdminUnit> pred){
        p = p.or(pred);
        return this;
    }

    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp = cmp;
        src.units.sort(cmp);
        return this;
    }

    AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }

    AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    AdminUnitList execute(){
        return where(p).sort(cmp).src;
    }
}