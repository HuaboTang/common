package com.codrim.common.utils.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;

/**
 * Http请求响应内容
 * Created by tanghuabo on 01/12/2016.
 */
public class CodrimHttpResponse {
    private int status;
    private String response;

    public CodrimHttpResponse() {
    }

    public CodrimHttpResponse(HttpResponse response) throws IOException {
        if (response == null) {
            throw new IllegalArgumentException("Argument `response` can not be null");
        }
        this.status = response.getStatusLine().getStatusCode();

        if (this.status == HttpStatus.SC_OK) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            this.response = new String(bytes, "UTF-8");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
