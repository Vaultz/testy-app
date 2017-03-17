package com.example.vault.testyapp.Preferences;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.NotificationCompat;

import com.example.vault.testyapp.MainActivity;
import com.example.vault.testyapp.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by vault on 15/03/2017.
 */

public class PreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);


        // Attribution d'un clickListener à l'élément XML key_notif des préférences
        findPreference("key_notif").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                // Création d'un PendingIntent pour créer une redirectin
                Intent intent = new Intent(getActivity(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, FLAG_UPDATE_CURRENT);

                // Création d'une notification, à laquelle on fournit l'intent (le but étant que l'application s'ouvre quand on clique dessus)
                android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                    .setSmallIcon(R.drawable.watch)
                    .setContentTitle("HandsomeNotif !")
                    .setContentText("I Like Pigs.")
                    .setContentIntent(pendingIntent);


                // Construction de la notification
                NotificationManager notificationManager =
                        (NotificationManager)
                                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                Notification notification = builder.build();

                // Affichage
                notificationManager.notify(0, notification);

                return false;
            }
        });

    }
}
