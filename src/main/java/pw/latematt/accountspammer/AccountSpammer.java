package pw.latematt.accountspammer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The main AccountSpammer class.
 *
 * @author Matthew
 */
public class AccountSpammer {
    /**
     * A URL to the website's php script to POST to.
     */
    private final String url;
    /**
     * The parameters to POST to the URL in question.
     */
    private final String parameters;
    /**
     * Allows use of a proxy in case you get firewalled by the website's host.
     */
    public final boolean proxy;
    /**
     * The host of the proxy. Must be an HTTP proxy.
     */
    private final String proxyHost;
    /**
     * The port of the proxy. Must be an HTTP proxy.
     */
    private final String proxyPort;
    /**
     * List of lines from the reply
     */
    private List<String> reply = new ArrayList<>();

    /**
     * This constructor uses varargs to get the parameters, for entertainment's sake
     *
     * @param url A URL to the website's php script to POST to.
     * @param parameters varargs of parameters to POST to the url
     */
    public AccountSpammer(String url, String... parameters) {
        this.url = url;
        String fixedParameters = "";
        for (String parameter : parameters) {
            fixedParameters = parameter + "&";
        }
        fixedParameters = fixedParameters.substring(0, fixedParameters.length() - 1);
        this.parameters = fixedParameters;
        this.proxy = false;
        this.proxyHost = "";
        this.proxyPort = "";
    }

    /**
     * This constructor is used whenever you don't need a proxy and don't want to enter the arguments.
     *
     * @param url A URL to the website's php script to POST to.
     * @param parameters The parameters to POST to the URL in question.
     */
    public AccountSpammer(String url, String parameters) {
        this(url, parameters, false, "", "");
    }

    /**
     * Main constructor which allows for use of a proxy.
     *
     * @param url A URL to the website's php script to POST to.
     * @param parameters The parameters to POST to the URL in question.
     * @param proxy Allows use of a proxy in case you get firewalled by the website's host.
     * @param proxyHost The host of the proxy. Must be an HTTP proxy.
     * @param proxyPort The port of the proxy. Must be an HTTP proxy.
     */
    public AccountSpammer(String url, String parameters, boolean proxy, String proxyHost, String proxyPort) {
        this.url = url;
        this.parameters = parameters;
        this.proxy = proxy;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    /**
     * @return A URL to the website's php script to POST to.
     */
    public String getURL() {
        return url;
    }

    /**
     * @return The parameters to POST to the URL in question.
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * @return if this instance of AccountSpammer is using a proxy.
     */
    public boolean isUsingProxy() {
        return proxy;
    }

    /**
     * @return proxy host
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * @return proxy port
     */
    public String getProxyPort() {
        return proxyPort;
    }

    /**
     * @return list of lines from reply
     */
    public List<String> getReply() {
        return reply;
    }

    /**
     * Posts data to the URL specified in the constructor.
     */
    public void postData() {
        if (proxy) {
            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", proxyPort);
        }
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
            connection.setUseCaches(false);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(parameters);
            outputStream.flush();
            outputStream.close();
            connection.disconnect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                reply.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}