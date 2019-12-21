package com.phmb.myplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.FacebookException
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import java.util.*
import com.facebook.AccessTokenTracker
import org.json.JSONObject
import org.json.JSONException

class AuthenticationActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportActionBar?.hide()

        val btnLoginFacebook = findViewById<ImageView>(R.id.imageViewFacebook)

        btnLoginFacebook.setOnClickListener(View.OnClickListener {

            callbackManager = CallbackManager.Factory.create()

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))

            checkLoginStatus()

            LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {

                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("AuthenticationActivity","Token Facebook " + loginResult.accessToken.token)

                    loadUserProfile(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d("AuthenticationActivity", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("AuthenticationActivity", "facebook:onError")
                }
            })
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadUserProfile(newAccessToken: AccessToken) {

        val request: GraphRequest
        request = GraphRequest.newMeRequest(newAccessToken) { params, response ->
            try {
                Log.d("AuthenticationActivity","params: " + params)

                val name = params.getString("first_name") + " " + params.getString("last_name");
                val id = params.getString("id");

                Log.d("AuthenticationActivity","Name: ${name}")
                Log.d("AuthenticationActivity","Id: ${id}")

                val intent = MainActivity.getStartIntent(applicationContext, name, id)
                this@AuthenticationActivity.startActivity(intent)

            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        val bundle = Bundle()
        bundle.putString("fields", "first_name,last_name,email,id,picture.type(normal)")
        request.setParameters(bundle)
        request.executeAsync()

    }

    private fun checkLoginStatus()
    {
        if(AccessToken.getCurrentAccessToken() != null)
        {
            loadUserProfile(AccessToken.getCurrentAccessToken())
        }
    }
}

