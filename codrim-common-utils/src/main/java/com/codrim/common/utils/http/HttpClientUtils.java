package com.codrim.common.utils.http;

import com.codrim.common.utils.json.JsonMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * http连接、抓取管理类
 */
public class HttpClientUtils {

    private static Log logger = LogFactory.getLog(HttpClientUtils.class);

    private static HttpClientUtils httpClientUtil;

    private HttpClientBuilder clientBuilder;

    private HttpClientUtils() {
        this.clientBuilder = HttpClientBuilder.create();
        RequestConfig.Builder requestBuilder = RequestConfig
            .custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(15000);
        clientBuilder.setDefaultRequestConfig(requestBuilder.build());
    }

    public static HttpClientUtils getInst() {
        try {
            synchronized (HttpClientUtils.class) {
                if (httpClientUtil == null) {
                    httpClientUtil = new HttpClientUtils();
                }
            }
        } catch (Exception e) {
            logger.error("HttpClientUtil getInst is error:", e);
        }
        return httpClientUtil;
    }

    /**
     * add by wenzhikai 2015-01-23
     *<获取对应文件流>
     * <功能详细描述>
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public byte[] getPostFile(String url) throws ClientProtocolException, IOException {
        final HttpPost httpPost = new HttpPost(url);

        final HttpClient client = clientBuilder.build();
        final HttpResponse response = client.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toByteArray(httpEntity);
        } else {
            throw new ClientProtocolException(response.getStatusLine().getReasonPhrase());
        }
    }

    public byte[] httpPost(String url, byte[] requestBytes) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        byte[] returnBytes = null;
        try {
            final HttpClient client = clientBuilder.build();

            HttpPost httpPost = new HttpPost(url);

            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestBytes);
            httpPost.setEntity(byteArrayEntity);

            HttpResponse response = client.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toByteArray(httpEntity);
            }
        } catch (IOException e) {
            logger.error("httpPost is error:", e);
        }
        return returnBytes;
    }

    public byte[] httpPost(String url, List<NameValuePair> params) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        byte[] returnBytes = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = this.clientBuilder.build().execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            returnBytes = EntityUtils.toByteArray(httpEntity);
        } catch (IOException e) {
            logger.error("httpPost is error:", e);
        }
        return returnBytes;
    }

    public String post(String url, String json) {
        HttpClient client = this.clientBuilder.build();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            // post.setHeader("endPoint","phone");
            ByteArrayEntity s = new ByteArrayEntity(json.getBytes());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                byte[] bytes = IOUtils.toByteArray(entity.getContent());
                result = new String(bytes, "UTF-8");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public <T> T postJson(String url, Object obj, Class<T> resultType) throws IOException {
        if(StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("Invalid arguments \'url\'");
        } else {
            CloseableHttpClient client = this.clientBuilder.build();
            HttpPost post = new HttpPost(url);

            try {
                JsonMapper e = JsonMapper.getInstance();
                ByteArrayEntity entity = new ByteArrayEntity(e.toJson(obj).getBytes("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                CloseableHttpResponse res = client.execute(post);
                if(res.getStatusLine().getStatusCode() == 200) {
                    byte[] bytes = IOUtils.toByteArray(res.getEntity().getContent());
                    String sJson = new String(bytes, "UTF-8");
                    return e.fromJson(sJson, resultType);
                }
            } catch (UnsupportedEncodingException var11) {
                ;
            } catch (UnsupportedOperationException var12) {
                throw var12;
            } catch (IOException var13) {
                throw var13;
            }

            return null;
        }
    }

    public <T> T postEntity(String url, Object obj, Class<T> resultType, Map<String, String> headMap) throws IOException {
        if(StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("Invalid arguments \'url\'");
        } else {
            CloseableHttpClient client = this.clientBuilder.build();
            HttpPost post = new HttpPost(url);

            try {
                JsonMapper e = JsonMapper.getInstance();
                ByteArrayEntity entity = new ByteArrayEntity(e.toJson(obj).getBytes("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                if(!headMap.isEmpty()) {
                    Iterator bytes = headMap.keySet().iterator();

                    while(bytes.hasNext()) {
                        String res = (String)bytes.next();
                        post.setHeader(res, (String)headMap.get(res));
                    }
                }

                CloseableHttpResponse res1 = client.execute(post);
                if(res1.getStatusLine().getStatusCode() == 200) {
                    byte[] bytes1 = IOUtils.toByteArray(res1.getEntity().getContent());
                    String sJson = new String(bytes1, "UTF-8");
                    return e.fromJson(sJson, resultType);
                }
            } catch (UnsupportedEncodingException var12) {
                ;
            } catch (UnsupportedOperationException var13) {
                throw var13;
            } catch (IOException var14) {
                throw var14;
            }

            return null;
        }
    }

    public String post(String url, String json, Map<String, String> headMap) {
        CloseableHttpClient client = this.clientBuilder.build();
        HttpPost post = new HttpPost(url);

        try {
            if(StringUtils.isNotBlank(json)) {
                ByteArrayEntity e = new ByteArrayEntity(json.getBytes());
                e.setContentEncoding("UTF-8");
                e.setContentType("application/json");
                if(!headMap.isEmpty()) {
                    Iterator bytes = headMap.keySet().iterator();

                    while(bytes.hasNext()) {
                        String entity = (String)bytes.next();
                        post.setHeader(entity, (String)headMap.get(entity));
                    }
                }

                post.setEntity(e);
            }

            HttpResponse e1 = client.execute(post);
            if(e1.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity1 = e1.getEntity();
                byte[] bytes1 = IOUtils.toByteArray(entity1.getContent());
                return new String(bytes1, "UTF-8");
            } else {
                return null;
            }
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

}
