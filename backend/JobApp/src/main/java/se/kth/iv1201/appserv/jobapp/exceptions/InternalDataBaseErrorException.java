package se.kth.iv1201.appserv.jobapp.exceptions;

public class InternalDataBaseErrorException extends Exception{
    private static final long serialVersionUID = 6355945960847848819L;

    public InternalDataBaseErrorException(String msg){
        super(msg);
    }
}
