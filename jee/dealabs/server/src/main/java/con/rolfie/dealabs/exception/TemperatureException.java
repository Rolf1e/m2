package con.rolfie.dealabs.exception;

public abstract class TemperatureException extends Exception {

    protected TemperatureException(final String message) {
        super(message);
    }

    protected TemperatureException(final Throwable e) {
        super(e);
    }
}
