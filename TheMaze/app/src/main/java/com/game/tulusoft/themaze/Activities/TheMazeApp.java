package com.game.tulusoft.themaze.Activities;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;


/**
 * Created by Shazam on 5/8/2016.
 */
public class TheMazeApp extends Application {

    public static final String TAG = TheMazeApp.class.getSimpleName();
    private RequestQueue mRequestQueue;

    private static TheMazeApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        mInstance = this;
//        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.game.tulusoft.themaze",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.d("EX 1:", e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            Log.d("EX 2:", e.getMessage());
//        }
    }


    public static synchronized TheMazeApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
