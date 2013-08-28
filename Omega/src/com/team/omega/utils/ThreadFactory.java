package com.team.omega.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;


public class ThreadFactory
{

    private static ThreadPoolExecutor poolExec;
   
    static {
	poolExec = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
	poolExec.setRejectedExecutionHandler(new RejectedExecutionHandler() {
	    
		@Override
		public void rejectedExecution(Runnable runnable,ThreadPoolExecutor executor)
		{
			Gdx.app.debug("ThreadFactory", "Task Rejected");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			executor.execute(runnable);
		}
		
	});
    }
    
    public static void newThread(Runnable runnable)
    {
	poolExec.execute(runnable);
    }
    
}
