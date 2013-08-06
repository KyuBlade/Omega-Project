package com.team.omega.core;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.team.omega.core.exception.OptimizationException;

public class LocalizationHandler
{

    private PropertiesHandler propHandler;
    private PropertiesHandler defaultPropHandler;

    private static LocalizationHandler instance;

    public LocalizationHandler()
    {
	this.propHandler = new PropertiesHandler();
	this.defaultPropHandler = new PropertiesHandler();

	load(defaultPropHandler, Locale.US);
    }

    public static LocalizationHandler getInstance()
    {
	if (instance == null)
	    instance = new LocalizationHandler();

	return instance;
    }

    public void setLanguage(Locale locale)
    {
	Locale.setDefault(locale);
	load(propHandler, locale);
    }
    
    public String getDialog(String key)
    {
	return propHandler.getProperties(key, defaultPropHandler.getProperties(key, "Undefined"));
    }

    public String getDialog(String key, String... toParse)
    {
	String _result = getDialog(key);
	
	if (toParse.length > 0)
	{
	    Pattern _pattern = Pattern.compile("\\{(.*?)\\}");
	    Matcher _matcher = _pattern.matcher(_result);

	    StringBuffer _sBuff = new StringBuffer();
	    int i = 0;
	    while (_matcher.find())
	    {
		String _toInject;
		if(i > toParse.length - 1)
		    _toInject = getDialog("common.error.undefined");
		else
		    _toInject = toParse[i];
		    
		_matcher.appendReplacement(_sBuff, _toInject);

		i++;
	    }
	    _matcher.appendTail(_sBuff);
	    
	    if(i < toParse.length)
		throw new OptimizationException("You have some useless arguments given");
	    
	    _result = _sBuff.toString();
	}

	return _result;
    }
    
    private void load(PropertiesHandler propHandler, Locale locale)
    {
	propHandler.loadProperties(Constants.DIALOGS_PATH + Constants.DIALOGS_PREFIX + locale.toString());
    }

}
