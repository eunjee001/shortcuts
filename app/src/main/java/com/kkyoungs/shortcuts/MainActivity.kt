package com.kkyoungs.shortcuts

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private var shortcutManager : ShortcutManager ?=null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shortcutManager = getSystemService(ShortcutManager::class.java)

        val shortcut = ShortcutInfo.Builder(this, "id")
            .setShortLabel("naver")
            .setLongLabel("Open the website")
            .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_background))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com")))
            .build()

        shortcutManager?.dynamicShortcuts = listOf(shortcut)
        addPinnedNaverShortCut()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addPinnedNaverShortCut(){
        shortcutManager?.let {shortcutManager->
            if (shortcutManager.isRequestPinShortcutSupported){
                val shortcut = ShortcutInfo.Builder(this, "id")
                    .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
                    .setShortLabel("google")
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_background)).build()
                val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(shortcut)
                val successCallback = PendingIntent.getBroadcast(this, 0, pinnedShortcutCallbackIntent,
                    PendingIntent.FLAG_IMMUTABLE)
                shortcutManager.requestPinShortcut(shortcut, successCallback.intentSender)
            }

        }
    }
}