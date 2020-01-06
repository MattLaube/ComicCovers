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
import com.laubetech.comiccovers.models.ComicData
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
        // get an instance of the viewmodel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //load the spinner from the strings file
        val issueListAdapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.issue_list, android.R.layout.simple_spinner_dropdown_item)
        issueListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        issueListspinner.adapter = issueListAdapter

        // go button kicks off the data request, but we could just use the spinners onselected method
        goButton.setOnClickListener{ view -> viewModel.goButton(issueListspinner.selectedItemId); }

        //setting up observers to handle when data is changed
        viewModel.reloadImage.observe(this, Observer { loadComicCover(viewModel.currentImageName.value ) })
        viewModel.currentComicData.observe(this, Observer { comicDetailsTextView.text =  viewModel.currentComicData.value.toString()
            viewModel.startImageDownload(this.context, viewModel.currentComicData.value!!.coverLink, viewModel.currentComicData.value!!.coverImageName )
        })

        viewModel.targetComic.observe(this, Observer {
            if ( viewModel.targetComic.value == null){
                viewModel.downloadIssueInfo()
            }else {
                var comicDetails = ComicData(viewModel.targetComic.value)
                comicDetailsTextView.text = comicDetails.toString()
                viewModel.updateImage(comicDetails.coverImageName)
            }
        })


    }

    // load a given file from storage or fall back to the starting image
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
