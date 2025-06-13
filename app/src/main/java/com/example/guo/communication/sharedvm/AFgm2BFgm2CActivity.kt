package com.example.guo.communication.sharedvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guo.R

/**
 * AFragment -> BFragment -> CActivity
 */
class AFgm2BFgm2CActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afgm_2bfgm_2cact)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AFragment())
            .commit()
    }
}