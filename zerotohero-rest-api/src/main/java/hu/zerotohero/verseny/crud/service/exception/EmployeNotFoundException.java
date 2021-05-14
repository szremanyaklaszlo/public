package hu.zerotohero.verseny.crud.service.exception;

public class EmployeNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -5834199556491837860L;

    public EmployeNotFoundException(String message) {
        super(message);
    }

}
