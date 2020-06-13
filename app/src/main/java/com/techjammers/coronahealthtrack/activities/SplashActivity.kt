package com.techjammers.coronahealthtrack.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.techjammers.coronahealthtrack.R

class SplashActivity : AppCompatActivity() {

 var permissionsString = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.BLUETOOTH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(!hasPermissions(this@SplashActivity, *permissionsString)) {
            //we have to ask for permissions
            ActivityCompat.requestPermissions(this@SplashActivity, permissionsString, 131)
        }else{
            Handler().postDelayed({
                val startAct = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(startAct)
                this.finish()
             },1000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            131->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED
                    && grantResults[1]== PackageManager.PERMISSION_GRANTED){
                    Handler().postDelayed({
                        val startAct = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    },1000)
                }else{
                    Toast.makeText(this@SplashActivity, "Please grant permission to continue", Toast.LENGTH_LONG).show()
                    this.finish()
                }
                return
            }
            else->{
                Toast.makeText(this@SplashActivity,"Something went wrong", Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }
        }

    }
    //function to check whether permission has been granted or not
    fun hasPermissions(context: Context, vararg permissions: String): Boolean{
        var hasAllPermissions = true
        for(permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }
            return hasAllPermissions
        }
    }
