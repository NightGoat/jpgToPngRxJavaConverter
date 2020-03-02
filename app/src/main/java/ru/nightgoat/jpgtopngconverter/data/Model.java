package ru.nightgoat.jpgtopngconverter.data;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import ru.nightgoat.jpgtopngconverter.IResourceHolder;

public class Model {

    private IResourceHolder iResourceHolder;

    public Model(IResourceHolder iResourceHolder) {
        this.iResourceHolder = iResourceHolder;
    }

    Resources getResources() {
        Log.d("Model", "getJpgPath: ");
        return iResourceHolder.getResources();
    }
}
