package xyz.ftuan.platform.passport.exception;

/**
 * Created by LUOXC on 2017/3/6.
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 6631653622620471284L;

    private int status = 400;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ServiceException(int status) {
        super();
        this.status = status;
    }

    public ServiceException(int status, String message) {
        super(message);
        this.status = status;
    }

    public ServiceException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public ServiceException(int status, Throwable cause) {
        super(cause);
        this.status = status;
    }
}
