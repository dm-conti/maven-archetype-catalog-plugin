package it.mexican.plugin.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;


interface HttpOperator {
	
	HttpResponse prepareAndExecuteGetRequest(String url);
	
	HttpResponse prepareAndExecutePutRequest(String url, HttpEntity entity);
	
	String entityToString(HttpEntity entity);
	
	byte[] entityToBytes(HttpEntity entity);
	
	public Credentials getCredentials();
	
	public HttpGet prepareGetRequest(String url);
	
	public HttpPut preparePutRequest(String url, HttpEntity entity, String contentType);
	
	public HttpResponse prepareAndExecutePutRequest(String url, HttpEntity entity, String contentType);
	
	public HttpResponse execute(HttpRequestBase request);
}