package ru.clevertec.service.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(ObjectType objectType, String string) {
        super("Object" + objectType + " with Id " + string + " not found");
    }
}
