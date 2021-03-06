package scoproject.com.contactsappgojek.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import scoproject.com.contactsappgojek.BuildConfig;
import scoproject.com.contactsappgojek.di.scope.AppScope;
import scoproject.com.contactsappgojek.networking.NetworkService;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIService;
import scoproject.com.contactsappgojek.networking.contactlist.GetContactListAPIService;
import scoproject.com.contactsappgojek.networking.detailcontact.GetDetailContactAPIService;
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIService;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */
@Module
public class NetworkModule {
    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @AppScope
    Retrofit provideCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @AppScope
    public NetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @AppScope
    public GetContactListAPIService provideGetContactListAPIService(NetworkService mNetworkService){
        return  new GetContactListAPIService(mNetworkService);
    }

    @Provides
    @AppScope
    public GetDetailContactAPIService provideGetDetailContactAPIService(NetworkService mNetworkService){
        return  new GetDetailContactAPIService(mNetworkService);
    }

    @Provides
    @AppScope
    public AddNewContactAPIService provideAddNewContactAPIService(NetworkService mNetworkService){
        return  new AddNewContactAPIService(mNetworkService);
    }

    @Provides
    @AppScope
    public UpdateContactAPIService provideUpdateContactAPIService(NetworkService mNetworkService){
        return  new UpdateContactAPIService(mNetworkService);
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            Cache cache = null;
            try {
                cache = new Cache(cacheFile, 10 * 1024 * 1024); //10MiB
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .cache(cache)
                    .readTimeout(180, TimeUnit.SECONDS)
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .hostnameVerifier((hostname, session) -> true)
                    .sslSocketFactory(sslSocketFactory)
                    .build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}