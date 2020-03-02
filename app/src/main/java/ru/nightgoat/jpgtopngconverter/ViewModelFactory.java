package ru.nightgoat.jpgtopngconverter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nightgoat.jpgtopngconverter.domain.Interactor;
import ru.nightgoat.jpgtopngconverter.presentation.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Interactor interactor;

    public ViewModelFactory(Interactor interactor) {
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(interactor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
