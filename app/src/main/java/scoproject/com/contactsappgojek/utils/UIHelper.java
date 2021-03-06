package scoproject.com.contactsappgojek.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class UIHelper {
    public static void showToastMessage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static ProgressDialog showProgressDialog(Context context){
        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        return mProgressDialog;
    }

    public static AlertDialog.Builder showAlertDialog(Context context, String title, String msg){
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(context);
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setPositiveButton("OK", (dialog, which) -> {
           //Automatically hide dialog;
        });
        return mAlertDialog;
    }
}
