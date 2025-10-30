package fiap_adj8.feedback_platform.feedback_app.application.db;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class DbRunner<CUSTOM_EXCEPTION extends Throwable> {

    private final Function<Exception, CUSTOM_EXCEPTION> exceptionMapper;

    protected DbRunner(Function<Exception, CUSTOM_EXCEPTION> exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    public <T> T run(Supplier<T> supplier) throws CUSTOM_EXCEPTION {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw exceptionMapper.apply(e);
        }
    }
}

