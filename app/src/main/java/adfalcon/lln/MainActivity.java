package adfalcon.lln;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.noqoush.adfalcon.android.sdk.constant.ADFErrorCode;
import com.noqoush.adfalcon.android.sdk.nativead.ADFNativeAd;
import com.noqoush.adfalcon.android.sdk.nativead.ADFNativeAdListener;
import com.noqoush.adfalcon.android.sdk.nativead.ADFNativeAdStatus;
import com.noqoush.adfalcon.android.sdk.nativead.assets.ADFAssetsBinder;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    ADFNativeAd nativeAd;
    ADFAssetsBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nativeAd = new ADFNativeAd(this);
        nativeAd.setTesting(true);


        try {
            binder = new ADFAssetsBinder.Builder(this, R.layout.activity_main).
                    addAdChoicesRelativeLayout(R.id.adchoices_view).
                    addIconImageView(R.id.template_icon, 50, 50).
                    addTitleTextView(R.id.title_template, 25).
                    addDescriptionTextView(R.id.template_description, 100).
                    addMainAssetRelativeLayout(R.id.template_main_asset, 50, 50).
                    addStarRatingBar(R.id.template_rating).
                    addExtraDataTextView(ADFAssetsBinder.DATA_ID_SPONSORED, R.id.template_sponsored, 20).
                    addActionButton(R.id.template_action_button, 10).
                    build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        nativeAd.setListener(new ADFNativeAdListener() {

            public void onLoadAd(ADFNativeAd nativeAd) {
                Log.d(TAG, "onLoadAd: " + nativeAd);
            }

            public void onFail(ADFNativeAd ad, ADFErrorCode errorCode, String message) {
                Log.d(TAG, "onFail: " + message);
            }

            @Override
            public boolean handleCustomAction(String s) {
                return false;
            }

            @Override
            public void renderExtraData(View view, Hashtable<Integer, String> hashtable) {

            }


            public void onPresentAdScreen(ADFNativeAd ad) {
            }

            public void onDismissAdScreen(ADFNativeAd ad) {
            }

            public void onLeaveApplication() {
            }

        });

        nativeAd.loadAd("6294ed8d2d3d4d7e9ee2c7e2a728363c", null, binder);
    }
}
