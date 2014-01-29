package it.mexican.plugin.util;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpTemplate implements HttpOperator {
	private Credentials credentials;
	private AuthScope authScope;
	private HttpClient httpClient;
	private HttpGet get;
	private HttpPut put;
	
	public HttpTemplate(String authHost,int authPort, String username, String password) {
		buildSecurityClient(authHost, authPort, username, password);
	}
	
	public Credentials getCredentials(){
		return this.credentials;
	}
	
	public HttpGet prepareGetRequest(String url){
		return new HttpGet(url);
	}
	
	public HttpResponse prepareAndExecuteGetRequest(String url){
		this.get = prepareGetRequest(url);
		return execute(get);
	}
	
	public HttpPut preparePutRequest(String url, HttpEntity entity, String contentType){
		this.put = new HttpPut(url);
		put.setEntity(entity);
		put.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
		return put;
	}
	
	public HttpResponse prepareAndExecutePutRequest(String url, HttpEntity entity, String contentType){
		this.put = preparePutRequest(url, entity, contentType);
		return execute(put);
	}
	
	public HttpResponse prepareAndExecutePutRequest(String url, HttpEntity entity){
		return prepareAndExecutePutRequest(url, entity, "text/xml");
	}
	
	public String entityToString(HttpEntity entity){
		String result = null;
		try {
			result = IOUtils.toString(entity.getContent()); }
		catch (IOException ioe) {
			throw new RuntimeException("an error occurred while decoding response entity content", ioe); }
		return result;
	}
	
	public byte[] entityToBytes(HttpEntity entity){
		return entityToString(entity).getBytes();
	}
	
	public HttpResponse execute(HttpRequestBase request){
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private void buildSecurityClient(String authHost,int authPort, String username, String password) {
		
		this.credentials = new UsernamePasswordCredentials(username, password);
		this.authScope = new AuthScope(authHost, AuthScope.ANY_PORT);

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(this.authScope, this.credentials);

		this.httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
	}

}