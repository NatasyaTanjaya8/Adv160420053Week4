package com.natasya.adv160420053week4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
//import androidx.lifecycle.Observer
import com.natasya.adv160420053week4.R
import com.natasya.adv160420053week4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    init{
        instance = this
    }
    companion object{
        var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            manager.notify(1001, builder.build())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App Student Data")
        fab.setOnClickListener {
            val observable = Observable.timer(5, TimeUnit.SECONDS).apply {
                subscribeOn(Schedulers.io())
                observeOn(AndroidSchedulers.mainThread())
                subscribe(){
                    Log.d("observermessage", "five seconds")
                    showNotification("Dummy", "A new notification apppeared", R.drawable.ic_baseline_email_24)
                }
            }
        }
        val observable = Observable.just("Hello", "Welcome to", "Ubaya")
        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Messages", "begin subscribe")
            }

            override fun onNext(t: String) {
                Log.d("Messages", "data: $t")
            }

            override fun onError(e: Throwable) {
                Log.e("Messages", "error: ${e!!.message.toString()}")
            }

            override fun onComplete() {
                Log.d("Messages", "complete")
            }

        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }
}