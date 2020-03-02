package ru.nightgoat.jpgtopngconverter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import java.util.Objects;

public class ResourceHolder implements IResourceHolder {

    private Context context;

    public ResourceHolder(Context context){
        this.context = context;
    }

    @Override
    public Resources getResources() {
        return context.getResources();
    }

    @Override
    public String getDirectory() {
        return Objects.requireNonNull(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)).getPath();
    }
}
