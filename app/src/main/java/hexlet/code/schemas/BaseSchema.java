package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    List<Predicate<Object>> validations = new ArrayList<>();

    public void addPredicate(Predicate<Object> predicate) {
        this.validations.add(predicate);
    }

    public boolean isValid(Object obj) {
        for (Predicate<Object> validation : validations) {
            if (!validation.test(obj)) {
                return false;
            }
        }
        return true;
    }

    abstract BaseSchema required();
}
