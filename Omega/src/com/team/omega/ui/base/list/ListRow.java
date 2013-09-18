package com.team.omega.ui.base.list;



public class ListRow
{

    private String[] content;
    private String formatedContent;
    private Object[] store;
    
    public ListRow()
    {
	
    }
    
    public ListRow(String[] content, Object[] store)
    {
	this.content = content;
	this.store = store;
    }

    public String[] getContent()
    {
	return content;
    }
    
    private void formatContent()
    {
	StringBuilder _sBuild = new StringBuilder();
	for(String _s : content)
	    _sBuild.append(_s).append("\t");
	
	formatedContent = _sBuild.toString();
    }
    
    public String getFormatedContent()
    {
	return formatedContent;
    }
    public void setContent(String[] content)
    {
	this.content = content;
	
	formatContent();
    }

    public Object[] getStore()
    {
	return store;
    }

    public void setStore(Object[] store)
    {
	this.store = store;
    }

}
