package com.example.traveller_guide.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton singleton;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context context;

    public Singleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
        imageLoader= new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    private RequestQueue get_RequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static Singleton getSingleton(Context context){
        if(singleton == null){
            singleton = new Singleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> tRequest) {
        get_RequestQueue().add(tRequest);
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}
