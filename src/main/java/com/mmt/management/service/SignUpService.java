package com.mmt.management.service;

public interface SignUpService 
{
	public boolean checkIfUserExisted(String _username);
	
	public boolean doUserSignUp(String _username, String _password);
}
