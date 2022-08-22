package com.flora.safetynetalerts.exceptions.exception;

public class NoSuchFirestationExistsException extends RuntimeException {

        private String message;

        public NoSuchFirestationExistsException() {}

        public NoSuchFirestationExistsException(String msg)
        {
            super(msg);
            this.message = msg;
        }
}
