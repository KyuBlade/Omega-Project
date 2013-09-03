package com.team.omega.core.exception;


@SuppressWarnings("serial")
public class UnsupportedFileFormatException extends Exception
{

    public UnsupportedFileFormatException(String message)
    {
	super(message);
    }
    
    public UnsupportedFileFormatException(String message, Throwable throwable)
    {
	super(message, throwable);
    }
    
}
