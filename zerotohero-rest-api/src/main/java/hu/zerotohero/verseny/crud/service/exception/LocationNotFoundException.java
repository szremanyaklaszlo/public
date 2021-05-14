package hu.zerotohero.verseny.crud.service.exception;

public class LocationNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -699247213462298167L;

    public LocationNotFoundException(String message) {
        super(message);
    }

}
