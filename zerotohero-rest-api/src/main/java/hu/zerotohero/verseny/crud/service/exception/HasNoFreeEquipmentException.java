package hu.zerotohero.verseny.crud.service.exception;

public class HasNoFreeEquipmentException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4318458238862718771L;

    public HasNoFreeEquipmentException() {
        super();
    }
    
    public HasNoFreeEquipmentException(String message) {
        super(message);
    }

}
