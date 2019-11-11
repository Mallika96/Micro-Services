package com.microservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Configuration
public class ZuulConfiguration implements FallbackProvider {

	
	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
		if(cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		}
		return response(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ClientHttpResponse response(final HttpStatus status) {
		
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				// TODO Auto-generated method stub
				return new ByteArrayInputStream("Sorry!! Something Went Wrong".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers=new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);
				return headers;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return 200;
			}

			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return "OK";
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	}
