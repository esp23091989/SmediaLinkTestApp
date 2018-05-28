package com.example.moviestestapplication.util;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.data.api.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
    private final Type bodyType;

    public LiveDataCallAdapter(Type bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public Type responseType() {
        return bodyType;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            private AtomicBoolean started = new AtomicBoolean(false);
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(ApiResponse.Companion.create(response));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable throwable) {
                            postValue(ApiResponse.Companion.create(throwable));
                        }
                    });
                }
            }
        };
    }
}
