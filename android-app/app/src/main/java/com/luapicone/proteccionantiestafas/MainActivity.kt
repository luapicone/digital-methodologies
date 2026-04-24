package com.luapicone.proteccionantiestafas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.luapicone.proteccionantiestafas.ui.ProteccionAntiestafasApp
import com.luapicone.proteccionantiestafas.ui.theme.ProteccionAntiestafasTheme

class MainActivity : ComponentActivity() {
    private var sharedText by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        sharedText = extractSharedText(intent)

        setContent {
            ProteccionAntiestafasTheme {
                ProteccionAntiestafasApp(initialSharedText = sharedText)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        sharedText = extractSharedText(intent)
    }

    private fun extractSharedText(intent: Intent?): String? {
        if (intent?.action != Intent.ACTION_SEND) return null
        return intent.getStringExtra(Intent.EXTRA_TEXT)
    }
}
