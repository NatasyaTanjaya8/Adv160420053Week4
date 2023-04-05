package com.natasya.adv160420053week4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natasya.adv160420053week4.model.Student

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()
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