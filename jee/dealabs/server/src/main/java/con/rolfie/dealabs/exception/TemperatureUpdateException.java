package con.rolfie.dealabs.exception;

public class TemperatureUpdateException extends TemperatureException {

    public TemperatureUpdateException(final String message) {
        super(message);
    }

    public TemperatureUpdateException(final Throwable e) {
        super(e);
    }

}
