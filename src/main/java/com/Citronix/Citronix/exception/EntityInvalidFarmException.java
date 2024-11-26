package com.Citronix.Citronix.exception;

public class EntityInvalidFarmException extends RuntimeException{
    public EntityInvalidFarmException(){
    }

    public EntityInvalidFarmException(String message){
        super(message);
    }
}
