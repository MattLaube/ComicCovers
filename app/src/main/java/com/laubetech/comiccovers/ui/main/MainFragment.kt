package com.laubetech.comiccovers.ui.main

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.laubetech.comiccovers.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.File


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val issueListAdapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.issue_list, android.R.layout.simple_spinner_dropdown_item)
        issueListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        issueListspinner.adapter = issueListAdapter

        goButton.setOnClickListener{ view -> viewModel.goButton(issueListspinner.selectedItemId); }

        viewModel.reloadImage.observe(this, Observer { loadComicCover(viewModel.currentImageName.value ) })
        viewModel.currentComicData.observe(this, Observer { comicDetailsTextView.text =  viewModel.currentComicData.value.toString()
            viewModel.startImageDownload(this.context, viewModel.currentComicData.value!!.coverLink, viewModel.currentComicData.value!!.coverImageName )
        })
    }

    private fun loadComicCover(imageName: String? ){
        if (imageName.isNullOrBlank()) {
            loadDefualtComicCover()
        }else {
            val imageFile = File(context!!.filesDir.toString() + "/" + imageName)

            if (imageFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imageFile.canonicalPath)
                comicCoverImageView.setImageBitmap(myBitmap)
            } else {
                loadDefualtComicCover()
            }
        }
    }

    private fun loadDefualtComicCover(){
        comicCoverImageView.setImageDrawable(
            ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.startcover))
    }


}
