package ru.nightgoat.jpgtopngconverter.domain;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import io.reactivex.Single;

public interface IRepository {

    Single<Resources> getImage();
}
