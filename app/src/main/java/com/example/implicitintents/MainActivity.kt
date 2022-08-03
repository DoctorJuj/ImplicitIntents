package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    private var mWebsiteEditText: EditText? = null
    private var mLocationEditText: EditText? = null
    private var mShareTextEditText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebsiteEditText = findViewById(R.id.website_edittext)
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareTextEditText = findViewById(R.id.share_edittext)
    }

    fun openWebsite(view: View) {
        val url = mWebsiteEditText!!.text.toString()
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }

    fun openLocation(view: View) {
        val loc = mLocationEditText!!.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        startActivity(intent)

/*      https://developers.google.com/maps/documentation/urls/android-intents#kotlin
        If the system cannot identify an app that can respond to the intent, your app may crash.
        For this reason, you should first verify that a receiving application is installed before
        you present one of these intents to a user.

        To verify that an app is available to receive the intent, call resolveActivity() on your
        Intent object. If the result is non-null, there is at least one app that can handle the
        intent and it's safe to call startActivity(). If the result is null, you should not use the
        intent and, if possible, you should disable the feature that invokes the intent.

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", addressUri.toString())
        }*/
    }

    fun shareText(view: View) {
        val txt = mShareTextEditText!!.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.share_text_with)
            .setText(txt)
            .startChooser()
    }
}