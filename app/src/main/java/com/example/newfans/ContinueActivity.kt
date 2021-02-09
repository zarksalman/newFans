package com.example.newfans

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ContinueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue)

        if (AppUtil.isNetworkAvailable(this)) {
            AdsManager.apply {
                showFbNativeAd(
                    this@ContinueActivity,
                    findViewById<com.facebook.ads.NativeAdLayout>(R.id.native_ad_container),
                    findViewById(R.id.continue_adview)
                )
            }
        }


        findViewById<LinearLayout>(R.id.button_container).setOnClickListener {
            startActivity(Intent(this@ContinueActivity, MainActivity::class.java))
            finish()
        }
    }
}