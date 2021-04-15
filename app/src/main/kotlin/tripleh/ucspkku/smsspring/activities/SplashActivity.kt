package tripleh.ucspkku.smsmessenger.activities

import android.content.Intent
import com.simplemobiletools.commons.activities.BaseSplashActivity

class SplashActivity : BaseSplashActivity() {
    override fun initActivity() {
        startActivity(Intent(this, _root_ide_package_.tripleh.ucspkku.smsmessenger.activities.MainActivity::class.java))
        finish()
    }
}
