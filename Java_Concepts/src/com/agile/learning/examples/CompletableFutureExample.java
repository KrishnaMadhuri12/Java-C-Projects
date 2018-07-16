package com.agile.learning.examples;

import java.util.concurrent.*;
/****
 * Java 8 got lambdas, streams, completableFuture
 * 
 * @author kkompell
 *
 */
public class CompletableFutureExample {

	public static void main(String[] args)
	{
		CompletableFuture<Integer> future= new CompletableFuture<>();
		future.thenApply(data ->data *2)
		.exceptionally(throwable ->{
			System.out.println(throwable);
			return 0;
		})
		.thenAccept(data  -> System.out.println(data));
		//sleep(2000);
		//future.complete(7);
		//future.completeExceptionally(new RuntimeException("Learning someting new: SO I throw exception"));
		sleep(5000);
	}
	
	private static boolean sleep(int ms)
	{
		try
		{
			Thread.sleep(ms);
			return true;
		}
		catch(InterruptedException e)
		{
			return false;
		}
	}
	
}
