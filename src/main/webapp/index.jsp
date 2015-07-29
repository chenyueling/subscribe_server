<%@ page import="java.util.Map" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%--
  Created by IntelliJ IDEA.
  User: chenyueling
  Date: 2015/5/22
  Time: 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<%
    static class NativeHttpClient {
        //设置连接超时时间
        public static final int DEFAULT_CONNECTION_TIMEOUT = (10 * 1000); // milliseconds
        //设置读取超时时间
        public static final int DEFAULT_READ_TIMEOUT = (30 * 1000); // milliseconds
        public static final String CHARSET = "UTF-8";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final int RESPONSE_OK = 200;

        enum RequestMethod {
            GET,
            POST,
            DELETE
        }

        public static String get(String url) {
            return _request(url, null, RequestMethod.GET, null);
        }

        public static String doGet(String url, Map<String, String> params) {
            if (params != null) {
                boolean isFirst = true;
                for (String key : params.keySet()) {
                    if (isFirst) {
                        isFirst = false;
                        url += "?";
                    } else {
                        url += "&";
                    }
                    url = url + key + "=" + params.get(key);
                }
            }
            return request(url).setGet().send();
        }


        public static String post(String url, String content) {
            return _request(url, content, RequestMethod.POST, null);
        }

        public static Builder request(String url) {
            return new Builder(url);
        }


        private static String _request(String url, String content, RequestMethod method, Map<String, String> headers) {

            HttpURLConnection conn;
            OutputStream out;
            StringBuilder sb = new StringBuilder();
            Map<String, String> requestInfo = new HashMap<String, String>();
            requestInfo.put("url", url);
            requestInfo.put("content", "content");
            requestInfo.put("method", method.name());
            try {
                URL aUrl = new URL(url);

                conn = (HttpURLConnection) aUrl.openConnection();
                conn.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
                conn.setReadTimeout(DEFAULT_READ_TIMEOUT);
                conn.setUseCaches(false);
                conn.setRequestMethod(method.name());
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Accept-Charset", CHARSET);
                conn.setRequestProperty("Charset", CHARSET);

                if (headers != null) {
                    for (String key : headers.keySet()) {
                        conn.setRequestProperty(key, headers.get(key));
                    }
                }

                switch (method) {
                    case GET:
                        conn.setDoOutput(false);
                        break;
                    case POST:
                        conn.setDoOutput(true);
                        conn.setRequestProperty("Content-Type", CONTENT_TYPE_JSON);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        byte[] data = content.getBytes(CHARSET);
                        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
                        out = conn.getOutputStream();
                        out.write(data);
                        out.flush();
                        break;
                    default:
                        // Could not have happened, ignore
                }

                int status = conn.getResponseCode();
                InputStream in;
                if (status == RESPONSE_OK) {
                    in = conn.getInputStream();
                } else {
                    in = conn.getErrorStream();
                }

                if (null != in) {
                    InputStreamReader reader = new InputStreamReader(in, CHARSET);
                    char[] buff = new char[1024];
                    int len;
                    while ((len = reader.read(buff)) > 0) {
                        sb.append(buff, 0, len);
                    }
                }

                String responseContent = sb.toString();
                if (status == 200) {
                    System.out.println(String.format("Succeed to get response from %s - 200 OK", url));
                } else if (status > 200 && status < 400) {
                    System.out.println("Normal response but unexpected - responseCode:" + status + ", responseContent:" + responseContent);
                } else {

                }

                return responseContent;
            } catch (Exception e) {

            }
            return url;
        }
        static class Builder {
            private String url;
            private RequestMethod method;
            private String content;
            private Map<String, String> headers;

            public Builder() {
            }

            public Builder(String url) {
                this.url = url;
                this.method = RequestMethod.GET;
            }

            public Builder setUrl(String url) {
                this.url = url;
                return this;
            }

            public Builder setGet() {
                this.method = RequestMethod.GET;
                return this;
            }

            public Builder setPost() {
                this.method = RequestMethod.POST;
                return this;
            }

            public Builder setContent(String content) {
                this.content = content;
                return this;
            }

            public Builder setHeaders(Map<String, String> headers) {
                this.headers = headers;
                return this;
            }

            public Builder addHeader(String key, String value) {
                if (this.headers == null) {
                    this.headers = new HashMap();
                }
                this.headers.put(key, value);
                return this;
            }

            public String send(){
                return _request(url, content, method, headers);
            }
        }


        public static void main(String[] args){

                for (int i = 0; i < 500; i++) {
                    System.out.println(NativeHttpClient.post("http://create.maka.im/ext/api/eclick","pid=RMXUQ2BE&btnid=btnid_9354149"));
                }
        }
    }
%>
<%
    for (int i = 0; i < 10; i++) {
        System.out.println(NativeHttpClient.post("http://create.maka.im/ext/api/eclick","pid=RMXUQ2BE&btnid=btnid_9354149"));
    }
%>
投票500~
</body>
</html>
