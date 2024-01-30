package com.kkyoungs.shortcuts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var shortcut = ShortcutInfoCompat.Builder(applicationContext, "id")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(IconCompat.createWithResource(applicationContext, R.drawable.ic_launcher_background))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com")))
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcut)
    }
}