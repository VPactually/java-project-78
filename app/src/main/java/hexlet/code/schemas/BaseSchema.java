package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private List<Predicate<T>> validations = new ArrayList<>();

    protected final void addPredicate(Predicate<T> predicate) {
        this.validations.add(predicate);
    }

    public final boolean isValid(T obj) {
        for (Predicate<T> validation : validations) {
            if (!validation.test(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return created for @Override
     */
    public BaseSchema<T> required() {
        addPredicate(o -> o != null);
        return this;
    }
}
