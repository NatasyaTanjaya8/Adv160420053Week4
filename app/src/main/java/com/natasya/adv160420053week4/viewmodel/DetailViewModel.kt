package com.natasya.adv160420053week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.natasya.adv160420053week4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val studentsLD = MutableLiveData<Student>()
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    fun fetch(id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$id"
        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val result = Gson().fromJson<Student>(it, Student::class.java)
                studentsLD.value = result
                loadingLD.value = false
                Log.e("showvoley2", result.toString())
            },
            {
                Log.e("showvoley2", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    /*
    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1
    }
    */
    fun fetch(id: String, studentName: String, bod: String, phone: String, photoUrl: String) {
        val student1 = Student(id,studentName,bod,phone,
            photoUrl)
        studentLD.value = student1
    }
}