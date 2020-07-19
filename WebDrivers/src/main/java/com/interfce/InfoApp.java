package com.interfce;

import javax.security.auth.login.LoginException;

import com.Exception.AlreadyRegistered;

public interface InfoApp {
	public void verifyTitle();
	public void alreadyRegistered() throws LoginException, InterruptedException, AlreadyRegistered ;
	public void signinaccountPage() throws InterruptedException ;
	public void Login() ;

}
