package com.team.omega.core;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class PropertiesHandler
{

    private Properties properties;

    public PropertiesHandler()
    {

    }

    public PropertiesHandler(String filename)
    {
	loadProperties(filename);
    }

    public void loadProperties(String filename)
    {
	properties = new Properties();
	try
	{
	    FileHandle _fHdl = Gdx.files.internal(filename + ".properties");
	    properties.load(_fHdl.read());
	} catch (GdxRuntimeException | IOException ex)
	{
	    Gdx.app.error("PropertiesHandler", "Unable to load file properties " + filename + ".properties");
	}
    }

    public String getProperties(String key, String defaultProp)
    {
	String _prop = properties.getProperty(key, defaultProp);

	return _prop;
    }

}
