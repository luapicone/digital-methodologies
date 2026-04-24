package com.luapicone.proteccionantiestafas.platform

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class PhoneStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != TelephonyManager.ACTION_PHONE_STATE_CHANGED) return

        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            val result = CallRiskEngine.evaluateIncomingNumber(
                rawNumber = incomingNumber,
                isKnownContact = false,
            )

            Toast.makeText(
                context,
                "${result.riskLabel}: ${result.detail}",
                Toast.LENGTH_LONG,
            ).show()
        }
    }
}
