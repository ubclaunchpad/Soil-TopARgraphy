package ca.ubc.eml.soiltopargraphy.editor.ui.infopanel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import ca.ubc.eml.soiltopargraphy.editor.R

/**
 * Fragment where user enters Flag name and description
 */

class DescriptionPanelFragment : Fragment() {

    private lateinit var mViewModel: DescriptionPanelViewModel
    private fun onToImageAddButtonClick(){
        //record the field in ui to the viewModel
        mViewModel.name = view?.findViewById<EditText>(R.id.Title)?.text.toString()
        mViewModel.description = view?.findViewById<EditText>(R.id.Description)?.text.toString()
        //call imagePanelFragment
        val manager = activity?.supportFragmentManager
        if(manager!=null){
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, ImagePanelFragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.description_panel_fragment, container, false)
        val toImageEditButton = view.findViewById<View>(R.id.to_image_add)
        toImageEditButton.setOnClickListener{onToImageAddButtonClick()}

        // When the right navigation button is clicked, switches this fragment out for the ImagePanel fragment
        val rightButton = view.findViewById<View>(R.id.buttonRight)
        rightButton.setOnClickListener{
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.container, ImagePanelFragment())
            transaction.commit()}

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(DescriptionPanelViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        // TODO: Use the ViewModel
    }

    companion object {

        fun newInstance(): DescriptionPanelFragment {
            return DescriptionPanelFragment()
        }
    }

}
