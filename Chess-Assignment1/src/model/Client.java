package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Database (registry/loginlist) of all players involved
 * 
 */

public class Client {
	
	// get player by name
	private Map<String, Player> playerList;
	private Map<String, Player> logged;
	public Client() 
	{
		playerList = new HashMap<>();
		logged = new HashMap<>();
		
		try
		{
			register("a", "ab");
			register("b", "ab");
			register("c", "ab");
		} catch (ClientException e) {}
	}

	public Player login(String name, String password) throws ClientException
	{
		if (!playerList.containsKey(name))
			throw new ClientException("Player with name: " + name + " doesn't exist.");
		if (!playerList.get(name).getPassword().equals(password))
			throw new ClientException("Incorrect password.");
		if (logged.containsKey(name))
			throw new ClientException("Player already logged in.");
		
		// store this player so it can't login again
		logged.put(name, new Player(name, password));
		return logged.get(name);
	}
	
	// reset logging process  once a new login process started
	public void clearLogged()
	{
		logged.clear();
	}
	
	public Player register(String name, String password) throws ClientException
	{
		if (password.length() < 2 || password.length() > 10)
			throw new ClientException("Please choose a password between 2 and 10 characters");
		if (password.equals(name) || password.equals("abcdefg"))
			throw new ClientException("Please choose a stronger password.");
		if (playerList.containsKey(name))
			throw new ClientException("Player already added");
		return playerList.put(name, new Player(name, password));
	}
	
	
	
	
	
	
	
	
	
	public class ClientException extends Exception
	{

		String message;
		public ClientException(String string) {
			message = string;
		}
		
		public String toString()
		{
			return message;
		}
	}
	
	
	
}

