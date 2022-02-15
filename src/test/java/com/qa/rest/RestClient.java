package com.qa.rest;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
	

	public HttpResponse doGet(String url, Map<String, String> reqHeaders) {
		System.out.println("in chehhhhhhhh====================");
		return this.exchange(url, reqHeaders, null, HttpMethod.GET);
	}

	public HttpResponse doPost(String url, Map<String, String> reqHeaders, String requestBody) {
		return this.exchange(url, reqHeaders, requestBody, HttpMethod.POST);
	}

	public HttpResponse doPut(String url, Map<String, String> reqHeaders, String requestBody) {
		return this.exchange(url, reqHeaders, requestBody, HttpMethod.PUT);
	}
	
	public HttpResponse doDelete(String url, Map<String, String> reqHeaders) {
		return this.exchange(url, reqHeaders, null, HttpMethod.DELETE);
	}
	

	private HttpResponse exchange(String url, Map<String, String> reqHeaders, String requestBody,
			HttpMethod httpMethod) {
		System.out.println("in client class =======>>>>>>>");
		HttpResponse httpResponse = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			if (!CollectionUtils.isEmpty(reqHeaders)) {
				headers.setAll(reqHeaders);
			}
			HttpEntity<String> requestEntity = requestBody == null ? new HttpEntity<String>(headers)
					: new HttpEntity<String>(requestBody, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
			httpResponse = new HttpResponse(response.getBody(), response.getHeaders(),
					response.getStatusCode().value());
		} catch (RestClientException restClientException) {
			if (restClientException instanceof HttpClientErrorException) {
				HttpClientErrorException clientErrorException = (HttpClientErrorException) restClientException;
				httpResponse = new HttpResponse(clientErrorException.getResponseBodyAsString(),
						clientErrorException.getResponseHeaders(), clientErrorException.getStatusCode().value());
			} else {
				throw new RestClientException("Failed in exchange method ", restClientException);
			}
		} catch (Exception e) {
			throw new RestClientException("Failed in exchange method  ", e);
		}
		return httpResponse;

	}
	
}
