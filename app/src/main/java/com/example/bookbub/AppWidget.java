package com.example.bookbub;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import static android.widget.RemoteViews.*;

public class AppWidget extends AppWidgetProvider
{
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId:appWidgetIds)
        {
            Intent  launch =new Intent(context,AddBookActivity.class);
            PendingIntent pi=PendingIntent.getActivity(context,0,launch,0);
            RemoteViews rv=new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            rv.setOnClickPendingIntent(R.id.idtv,pi);
            appWidgetManager.updateAppWidget(appWidgetId,rv);

        }
    }
}
