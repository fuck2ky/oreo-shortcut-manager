package com.github.spitson.takeo.myshortcut

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.view.View
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openShortcut(view: View) {
        val intent = Intent(this, ShortcutActivity::class.java)
        startActivity(intent)
    }

    fun createShortcut(view: View) {
        // ShortcutManager#requestPinShortcut needs API 26
        if (Build.VERSION.SDK_INT < 26) {
            return
        }

        val manager = getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager
        if (manager.isRequestPinShortcutSupported) {
            val intent = Intent(this, ShortcutActivity::class.java)
            intent.action = Intent.ACTION_VIEW

            val info = ShortcutInfo.Builder(this, "shortcut-id")
                    .setShortLabel("label")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher_round))
                    .setIntent(intent)
                    .build()
            manager.requestPinShortcut(info, null)
        }
    }
}
