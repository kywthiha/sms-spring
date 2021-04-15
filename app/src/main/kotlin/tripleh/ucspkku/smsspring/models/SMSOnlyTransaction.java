package tripleh.ucspkku.smsmessenger.models;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SMSOnlyTransaction extends Transaction {

    public SMSOnlyTransaction(Context context) {
        super(context, new Settings());
    }

    public SMSOnlyTransaction(Context context, Settings settings) {
        super(context, settings);
    }

    private List<String> splitEqually(String text, int size) {
        // Give the list the right capacity to start with. You could use an array
        // instead if you wanted.
        List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }

    private String clearString(String str,String regex) {
        return str.trim().replaceAll("\\s+", " ").replaceAll(regex,"");
    }

    private void checkMMSPassOnlySms(Message message) {
        settings.setSendLongAsMms(false);
        settings.setGroup(false);
        settings.setSendLongAsMmsAfter(100000);
        message.setSubject(null);
    }

    public void sendNewSmsMessage(Message message,String regex,int length, long threadId, Parcelable sentMessageParcelable, Parcelable deliveredParcelable) {
        this.checkMMSPassOnlySms(message);
        String messageText = this.clearString(message.getText(),regex);
        for (String text : this.splitEqually(messageText, length)) {
            message.setText(text);
            this.sendNewMessage(message, threadId, sentMessageParcelable, deliveredParcelable);
        }

    }

    public void sendNewSmsMessage(Message message,String regex,int length, long threadId) {
        this.sendNewSmsMessage(message,regex,length, threadId, new Bundle(), new Bundle());
    }


}
