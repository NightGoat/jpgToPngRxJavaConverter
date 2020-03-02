package ru.nightgoat.jpgtopngconverter.data;

import android.content.res.Resources;
import android.util.Log;

import io.reactivex.Single;
import ru.nightgoat.jpgtopngconverter.domain.IRepository;

public class RepositoryImpl implements IRepository {

    private final Model model;

    public RepositoryImpl(Model model) {
        this.model = model;
    }

    @Override
    public Single<Resources> getImage() {
        Log.d("RepositoryImpl", "getImage: ");
        return Single.create(emitter -> {
            Log.d("RepositoryImpl", "getImage: create()");
            if (!emitter.isDisposed()) {
                emitter.onSuccess(model.getResources());
            }
        });
    }
}
