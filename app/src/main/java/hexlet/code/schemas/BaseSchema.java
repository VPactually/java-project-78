package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> validations = new ArrayList<>();

    protected final void addPredicate(Predicate<Object> predicate) {
        this.validations.add(predicate);
    }

    public final boolean isValid(Object obj) {
        Object res = obj;
        for (Predicate<Object> validation : validations) {
            if (!validation.test(res)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return created for @Override
     */
    public BaseSchema required() {
        addPredicate(o -> o != null);
        return this;
    }
}
