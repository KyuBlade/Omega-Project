package com.team.omega.core.screen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.team.omega.core.Constants;
import com.team.omega.ui.ProgressBar;

public class LoadingScreen extends BaseScreen implements Runnable
{

    private Table progressTable;
    private Label progressText;
    private ProgressBar progressBar;

    private List<Callable<Void>> tasks = new LinkedList<>();
    private Thread thread;

    public LoadingScreen(ScreenManager screenManager)
    {
	super(screenManager, Constants.LOADING_SCREEN_DEPTH);

	progressTable = new Table(skin);
	progressText = new Label("0%", skin);
	progressText.setAlignment(Align.center);
	progressBar = new ProgressBar(skin, "loading");
	progressBar.setWidth(400f);
	progressBar.setHeight(35f);

	progressTable.stack(progressBar, progressText);
	progressTable.debug();
	layout.add().minHeight(600f).row();
	layout.add(progressTable);
	layout.debug();

	thread = new Thread(this);

	isActive = false;
    }

    @Override
    public void render(float delta)
    {
	super.render(delta);
    }

    public void addTask(Callable<Void> task)
    {
	tasks.add(task);
    }

    public void load()
    {
	isActive = true;
	thread.start();
    }

    public float getCurrentProgress()
    {
	return progressBar.getPercent();
    }

    public void setCurrentProgress(float currentProgress)
    {
	progressBar.setPercent(currentProgress);
	progressText.setText(Math.round(currentProgress * 100) + "%");
    }

    @Override
    public void run()
    {
	Iterator<Callable<Void>> _iter = tasks.iterator();
	while (_iter.hasNext())
	{
	    try
	    {
		Thread.sleep(500);
	    } catch (InterruptedException e1)
	    {
		e1.printStackTrace();
	    }
	    try
	    {
		_iter.next().call();
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }
	}
	progressText.setText("Finished");
    }

}
