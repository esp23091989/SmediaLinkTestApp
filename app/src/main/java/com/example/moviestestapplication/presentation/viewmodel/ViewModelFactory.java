package com.example.moviestestapplication.presentation.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<?> , Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<?>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> creator = creators.get(modelClass);
        if(creator != null)
            creator = creator;
        else {
            for(Map.Entry<Class<?>, Provider<ViewModel>> entry : creators.entrySet()){
                if(modelClass.isAssignableFrom(entry.getKey()))
                    creator = entry.getValue();
            }

            if(creator == null)
                throw new IllegalArgumentException("unknown model class" + modelClass);
        }

        return (T) creator.get();
    }
}
