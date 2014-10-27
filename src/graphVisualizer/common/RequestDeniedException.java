/**
 * 
 */
package graphVisualizer.common;


/**
 * @author Willy Lund
 *
 */
public class RequestDeniedException
        extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8979985895255728771L;

    /**
     * 
     */
    public RequestDeniedException() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public RequestDeniedException(String message) {

        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public RequestDeniedException(Throwable cause) {

        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public RequestDeniedException(String message, Throwable cause) {

        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public RequestDeniedException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
