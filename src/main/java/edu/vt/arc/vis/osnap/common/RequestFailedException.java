/**
 * 
 */
package edu.vt.arc.vis.osnap.common;


/**
 * @author whlund15
 *
 */
public class RequestFailedException
        extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1096896279167853717L;

    /**
     * 
     */
    public RequestFailedException() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public RequestFailedException(String message) {

        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public RequestFailedException(Throwable cause) {

        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public RequestFailedException(String message, Throwable cause) {

        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public RequestFailedException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
