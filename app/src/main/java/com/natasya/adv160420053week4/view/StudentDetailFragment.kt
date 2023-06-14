package com.natasya.adv160420053week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.natasya.adv160420053week4.R
import com.natasya.adv160420053week4.model.Student
import com.natasya.adv160420053week4.util.loadImage
import com.natasya.adv160420053week4.viewmodel.DetailViewModel
import com.natasya.adv160420053week4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    var id = ""
    var studentName = ""
    var bod = ""
    var phone = ""
    var photoUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            //studentName = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentName
            //bod = StudentDetailFragmentArgs.fromBundle(requireArguments()).bod
            //phone = StudentDetailFragmentArgs.fromBundle(requireArguments()).phone
            //photoUrl = StudentDetailFragmentArgs.fromBundle(requireArguments()).photoUrl
            /*
            textID.setText(id)
            textName.setText(studentName)
            textBod.setText(bod)
            textPhone.setText(phone)
            */
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            textID.setText(it.id)
            textName.setText(it.name)
            textBod.setText(it.bod)
            textPhone.setText(it.phone)
            imageView2.loadImage(it.photoUrl, progressBar2)
        })
    }
}