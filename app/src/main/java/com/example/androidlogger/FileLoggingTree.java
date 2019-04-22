package com.example.androidlogger;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class FileLoggingTree extends Timber.DebugTree {

    private static final String LOG_TAG = FileLoggingTree.class.getSimpleName();

    @Override protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        try {
            String path = "Log";
            String fileNameTimeStamp = new SimpleDateFormat("dd-MM-yyyy",
                    Locale.getDefault()).format(new Date());
            String logTimeStamp = new SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa",
                    Locale.getDefault()).format(new Date());
            String fileName = fileNameTimeStamp + ".html";

            // Create file
            File file  = generateFile(path, fileName);

            // If file created or exists save logs
            if (file != null) {
                FileWriter writer = new FileWriter(file, true);
                writer
                    .append("<p style=\"font-family: monospace; background: #f5f5f5; padding: 5px 0px; margin:10px 0px;\"> ")
                    .append("<span style=\"color:#84A50A;\">") .append(logTimeStamp) .append("</span>")
                    .append("<span style=\"color:#d629c9;margin:0px 15px;\">") .append(tag) .append("</span>")
                    .append("<strong style=\"color:#555753;\">") .append(getPriorityName(priority)) .append("</strong>");

                switch (priority){
                    case 2: //VERBOSE
                        writer.append("<span style=\"color:#4367B8;margin:0px 15px;\">") .append(message) .append("</span>");
                        break;
                    case 3: //DEBUG
                        writer.append("<span style=\"color:#008c25;margin:0px 15px;\">") .append(message) .append("</span>");
                        break;
                    case 4: //INFO
                        writer.append("<span style=\"color:#523927;margin:0px 15px;\">") .append(message) .append("</span>");
                        break;
                    case 5: //WARN
                        writer.append("<span style=\"color:#C4A003;margin:0px 15px;\">") .append(message) .append("</span>");
                        break;
                    case 6: //ERROR
                        writer.append("<strong style=\"color:#EF2929;margin:0px 15px;\">") .append(message) .append("</strong>");
                        break;
                }

                writer.append("</p>");

                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            Log.e(LOG_TAG,"Error while logging into file : " + e);
        }
    }

    private String getPriorityName(int priority) {
        switch (priority){
            case 2: return "VERBOSE";
            case 3: return "DEBUG";
            case 4: return "INFO";
            case 5: return "WARN";
            case 6: return "ERROR";
            default: return "OTHER";
        }
    }

    @Override protected @Nullable String createStackElementTag(@NotNull StackTraceElement element) {
        return super.createStackElementTag(element) + " - " + element.getLineNumber();
    }

    @Nullable private static File generateFile(@NonNull String path, @NonNull String fileName) {
        File file = null;
        if (isExternalStorageAvailable()) {
            File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                    BuildConfig.APPLICATION_ID + File.separator + path);

            boolean dirExists = true;

            if (!root.exists()) {
                dirExists = root.mkdirs();
            }

            if (dirExists) {
                file = new File(root, fileName);
            }
        }
        return file;
    }

    private static boolean isExternalStorageAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
