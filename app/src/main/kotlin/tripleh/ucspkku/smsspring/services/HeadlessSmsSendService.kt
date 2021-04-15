package tripleh.ucspkku.smsmessenger.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import com.klinker.android.send_message.Settings
import com.simplemobiletools.smsmessenger.extensions.config
import com.simplemobiletools.smsmessenger.extensions.getThreadId
import com.simplemobiletools.smsmessenger.receivers.SmsStatusDeliveredReceiver
import com.simplemobiletools.smsmessenger.receivers.SmsStatusSentReceiver

class HeadlessSmsSendService : Service() {
    override fun onBind(intent: Intent?) = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            if (intent == null) {
                return START_NOT_STICKY
            }

            val number = Uri.decode(intent.dataString!!.removePrefix("sms:").removePrefix("smsto:").removePrefix("mms").removePrefix("mmsto:").trim())
            val text = intent.getStringExtra(Intent.EXTRA_TEXT)
            val settings = Settings()
            settings.useSystemSending = true
            settings.deliveryReports = true

            val transaction =
                _root_ide_package_.tripleh.ucspkku.smsmessenger.models.SMSOnlyTransaction(
                    this,
                    settings
                )
            val message = com.klinker.android.send_message.Message(text, number)

            val smsSentIntent = Intent(this, SmsStatusSentReceiver::class.java)
            val deliveredIntent = Intent(this, SmsStatusDeliveredReceiver::class.java)

            transaction.setExplicitBroadcastForSentSms(smsSentIntent)
            transaction.setExplicitBroadcastForDeliveredSms(deliveredIntent)

            transaction.sendNewSmsMessage(message,config.addRegexFilter,config.customizeSplitLength, getThreadId(number))

        } catch (ignored: Exception) {
        }

        return super.onStartCommand(intent, flags, startId)
    }
}
