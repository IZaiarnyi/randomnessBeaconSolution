package beacon.api.restclient;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;
import java.io.IOException;

/**
 *
 * Created by Yupa on 24.02.2016.
 */
public enum RestClient {
    /**
     * Singleton instance.
     */
    INSTANCE;

    private static final String DEFAULT_ENDPOINT_URL = "https://beacon.nist.gov";
    private static final int DEFAULT_RETRY_COUNT = 30;
    private Retrofit.Builder RestAdapterBuilder = null;
    private RestApi restApi = null;

    public void initialize(String databaseEndpointUrl) {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                int count = 0;
                while (!response.isSuccessful() && count < DEFAULT_RETRY_COUNT) {
                    count++;
                    response = chain.proceed(request);
                }
                return response;
            }
        });

        RestAdapterBuilder = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(databaseEndpointUrl != null ? databaseEndpointUrl : DEFAULT_ENDPOINT_URL);

        Retrofit databaseRestAdapter = RestAdapterBuilder.build();
        restApi = databaseRestAdapter.create(RestApi.class);
    }

    public void initialize() { initialize(null); }

    public void setEndpointUrl(String endpointUrl) {
        if (RestAdapterBuilder == null) {
            throw new IllegalStateException("RestClient instance was not initialized.");
        }
        if (endpointUrl != null) {
            RestAdapterBuilder.baseUrl(endpointUrl);
            Retrofit restAdapter = RestAdapterBuilder.build();
            restApi = restAdapter.create(RestApi.class);
        }
    }
    public RestApi getRestApi() {
        if (restApi == null) {
            throw new IllegalStateException("RestClient instance was not initialized.");
        }
        return restApi;
    }
}