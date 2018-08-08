package com.example.alexandr.trinitydigitaltest.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.alexandr.trinitydigitaltest.R
import com.example.alexandr.trinitydigitaltest.ui.user.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment(UserListFragment())
    }

    private fun initFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.content, fragment)
        ft.commitAllowingStateLoss()
    }
}
