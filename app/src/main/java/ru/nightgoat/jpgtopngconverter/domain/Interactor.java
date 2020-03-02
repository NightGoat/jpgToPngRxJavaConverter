package ru.nightgoat.jpgtopngconverter.domain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.nightgoat.jpgtopngconverter.IResourceHolder;
import ru.nightgoat.jpgtopngconverter.R;
import ru.nightgoat.jpgtopngconverter.domain.IRepository;

public class Interactor {

    private IRepository repository;
    private IResourceHolder iResourceHolder;


    public Interactor(IRepository repository, IResourceHolder iResourceHolder ) {
        this.repository = repository;
        this.iResourceHolder = iResourceHolder;
    }

    public Single<Boolean> convertImage(){
        Log.d("Interactor", "convertImage: in convertImage(): ");
        return repository.getImage().subscribeOn(Schedulers.io()).map(s -> {
            boolean success;
            Log.d("Interactor", "convertImage: Resources is: " + s);
            try {
                Bitmap bmp = BitmapFactory.decodeResource(s, R.drawable.image);
                File imagePng = new File(iResourceHolder.getDirectory() + "/convertedImage.png");
                FileOutputStream out = new FileOutputStream(imagePng);
                success = bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                Log.d("Interactor", "convertImage: success:" + success);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            }

            return success;
        });
    }
}
