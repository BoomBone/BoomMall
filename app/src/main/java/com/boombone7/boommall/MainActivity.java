package com.boombone7.boommall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.boombone7.core.activities.ProxyActivity;
import com.boombone7.core.delegates.OrangeDelegate;
import com.boombone7.core.net.rx.RxRestClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Administrator
 */
public class MainActivity extends ProxyActivity {


    @Override
    public OrangeDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
