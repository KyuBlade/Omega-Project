package com.team.omega.ui.base.group;



public interface Groupable 
{

    public void setActorGroup(ActorGroup<? extends Groupable> group);
    public ActorGroup<? extends Groupable> getActorGroup();
    
    public boolean isChecked();
    public void setChecked(boolean isChecked);
    
    public boolean isDisabled();
    public void setDisabled(boolean isDisabled);
    
}
