package kz.example.lesson_3.Lesson9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicControlReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if("ACTION_PLAY".equals(intent.getAction())){
            Intent serviceIntent = new Intent(context, MusicService.class);
            context.startService(serviceIntent);
            serviceIntent.setAction("ACTION_PLAY");
        }else if("ACTION_PAUSE".equals(intent.getAction())){
            Intent serviceIntent = new Intent(context, MusicService.class);
            serviceIntent.setAction("ACTION_PAUSE");
            context.startService(serviceIntent);
        }
    }
}
