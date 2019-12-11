package com.example.readjson04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void readJSONDemo1(final String urlDemo1){
        Observable<String> observableDemo1 = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(docNoiDung_Tu_URL(urlDemo1));
            }
        });
        observableDemo1
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("BBB",s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
