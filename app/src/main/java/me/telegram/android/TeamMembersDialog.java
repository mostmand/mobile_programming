package me.telegram.android;
import android.app.AlertDialog;
import android.content.Context;

public class TeamMembersDialog {
    public static void show(Context context){
        new AlertDialog.Builder(context)
                .setTitle(R.string.temMembersDialogTitle)
                .setMessage(R.string.teamMembers)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
