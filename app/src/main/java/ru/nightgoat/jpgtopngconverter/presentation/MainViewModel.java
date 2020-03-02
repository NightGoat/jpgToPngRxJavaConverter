package ru.nightgoat.jpgtopngconverter.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import ru.nightgoat.jpgtopngconverter.domain.Interactor;

public class MainViewModel extends ViewModel {

    private Interactor interactor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    public MainViewModel(Interactor interactor){
        this.interactor = interactor;
    }

    void convert(){
        compositeDisposable.add(
                interactor.convertImage()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Boolean>() {
                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                isSuccess.setValue(aBoolean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                isSuccess.setValue(false);
                            }
                        }));
    }

    void interrupt(){
        compositeDisposable.dispose();
        isSuccess.setValue(false);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
