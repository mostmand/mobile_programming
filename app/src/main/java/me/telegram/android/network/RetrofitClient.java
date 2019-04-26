package me.telegram.android.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            createNewClient();

        return retrofit;
    }

    public static void createNewClient() {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        httpClient.addInterceptor(logging);
//
//        if (User.getInstance().isAuthenticated()) {
//            httpClient.addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request().newBuilder().addHeader("Authorization", "Token " + User.getInstance().getToken()).build();
//                    return chain.proceed(request);
//                }
//            });
//        }

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
