package io.stormx.offertoro;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Intent;

public class RNOfferToroModule extends ReactContextBaseJavaModule {

    public RNOfferToroModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNOfferToro";
    }

    @ReactMethod
    public void showOfferwall(final String appId, final String secretKey, final String pubUid) {
        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.appId, appId);
        intent.putExtra(MainActivity.secretKey, secretKey);
        intent.putExtra(MainActivity.pubUid, pubUid);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
