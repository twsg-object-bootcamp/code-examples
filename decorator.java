import java.util.function.BiFunction;
import java.util.function.Function;

class Request {}

interface Middleware {
    public String handle(Request req);
}

class Logging implements Middleware {
    private final Middleware inner;

    Logging(Middleware inner) {
        this.inner = inner;
    }

    @Override
    public String handle(Request req) {
        return inner.handle(req) + ": Logging";
    }
}

class Authentication implements Middleware {
    private final Middleware inner;

    Authentication(Middleware inner) {
        this.inner = inner;
    }

    @Override
    public String handle(Request req) {
        return inner.handle(req) + ": Authentication";
    }
}

class Controller implements Middleware {
    @Override
    public String handle(Request req) {
        return "Base";
    }
}

class Main {
    public static void main(String[] args) {
        Request request = new Request();
        Middleware outermost = new Logging(new Authentication(new Controller()));
        outermost.handle(request);

//        Function<String, String> logging = (res -> res + ": Logging");
//        Function<String, String> authentication = (res -> res + ": Authentication");
//        Function<Request, String> controller = (req -> "Base");
//        logging.compose(authentication).compose(controller).apply(request);
//        controller.andThen(authentication).andThen(logging).apply(request);


//        BiFunction<Request, String, String> fn = (req, res) -> res + ": Authentication";
    }

//    public static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> f, T x) {
//        return (y) -> f.apply(x, y);
//    }
}