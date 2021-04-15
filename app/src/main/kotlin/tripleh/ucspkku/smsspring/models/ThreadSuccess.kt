package tripleh.ucspkku.smsmessenger.models

// show a check after the latest message, if it is a sent one and succeeded
data class ThreadSuccess(val messageID: Long) : ThreadItem()
