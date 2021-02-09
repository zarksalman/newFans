package com.example.newfans

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.google.firebase.database.DatabaseReference
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class SplashActivity : AppCompatActivity() {

    var preference: Preference? = null
    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    private var mDatabase: DatabaseReference? = null
    private val TAG = "REMOTE_CONFIG_TAG"
    private val ADMOD_ID = "admob_app"
    private val ADMOB_BANNER_ID = "admob_banner_id"
    private val ADMOB_INTERSTITIAL_ID = "admob_interstitial_id"
    private val ADMOB_NATIVE_ID = "admob_native_id"

    private val FB_BANNER_ID = "fb_banner_id"
    private val FB_INTERSTITIAL_ID = "fb_interstitial_id"
    private val FB_NATIVE_ID = "fb_native_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preference = Preference(this)
        MobileAds.initialize(this, AdsManager.preference?.admobAppId)
        AudienceNetworkAds.initialize(this)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configBuilder =
            FirebaseRemoteConfigSettings.Builder()
        if (BuildConfig.DEBUG) {
            val cacheInterval: Long = 0
            configBuilder.minimumFetchIntervalInSeconds = cacheInterval
        }
        firebaseRemoteConfig?.setConfigSettingsAsync(configBuilder.build())
        //  firebaseRemoteConfig?.setDefaultsAsync(R.xml.xml)
/*        if (AppUtil.isNetworkAvailable(this)) {
            getConfigData()
        } else
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show()*/

        AdsManager.loadAds(this)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, ContinueActivity::class.java))
            finish()
        }, 1000)
    }

    /* private fun getConfigData() {
         firebaseRemoteConfig?.fetchAndActivate()
             ?.addOnCompleteListener { p0 ->
                 if (p0.isSuccessful) {
                     applyFetchData()
                 }
             }
     }*/

    private fun applyFetchData() {
        // fetching Data
        firebaseRemoteConfig?.apply {

            val admobBannerId = getString(ADMOB_BANNER_ID)
            val admobInterstitialId = getString(ADMOB_INTERSTITIAL_ID)
            val admobNativeId = getString(ADMOB_NATIVE_ID)

            val fbBannerId = getString(FB_BANNER_ID)
            val fbInterstitialId = getString(FB_INTERSTITIAL_ID)
            val fbNativeId = getString(FB_NATIVE_ID)

            val guidContent = getString("first_button_content")
            val createAccountCont = getString("second_button_content")
            val subscriptionCont = getString("third_button_content")

            val profileCont = getString("fourth_button_content")
            val planContent = getString("fifth_button_content")
            val kingContent = getString("sixth_button_content")

            val admobId = firebaseRemoteConfig!!.getString(ADMOD_ID)

            preference?.apply {

                admobAppId = admobId
                admobBanner = admobBannerId
                admobInterstitial = admobInterstitialId
                admobNative = admobNativeId

                fbBanner = fbBannerId
                fbInterstitial = fbInterstitialId
                fbNative = fbNativeId

                guideContent = guidContent
                createAccountContent = createAccountCont
                subScriptionContent = subscriptionCont
                profileContent = profileCont
                planContentContent = planContent
                kingContentContent = kingContent
            }
        }

        AdsManager.loadAds(this)
        startActivity(Intent(this@SplashActivity, ContinueActivity::class.java))
        finish()
    }
}