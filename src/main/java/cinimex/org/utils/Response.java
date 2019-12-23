package cinimex.org.utils;

import lombok.Data;

import static cinimex.org.utils.ResponseStatus.ERROR;
import static cinimex.org.utils.ResponseStatus.OK;

@Data
public class Response {
    private final Object data;
    private final ResponseStatus status;
    private final String message;

    public static Response success(Object data, String message) {
        return new Response(data, OK, message);
    }

    public static Response success(Object data) {
        return new Response(data, OK, null);
    }

    public static Response success() {
        return new Response(null, OK, null);
    }

    public static Response fail(String message) {
        return new Response(null, ERROR, message);
    }

    public static Response fail(Object errorDetails, String message) {
        return new Response(errorDetails, ERROR, message);
    }
}
