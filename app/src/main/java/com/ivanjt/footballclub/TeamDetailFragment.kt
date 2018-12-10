package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TeamDetailFragment : Fragment() {
    private lateinit var description: TextView

    companion object {
        private val TEAM_DESC = "com.ivanjt.footballclub.TEAM_DESC"

        fun newInstance(desc: String): TeamDetailFragment {
            val fragment = TeamDetailFragment()
            val args = Bundle()
            args.putString(TEAM_DESC, desc)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments

        //Reference to xml view
        description = view.findViewById(R.id.tv_team_desc)
        description.text = args?.getString(TEAM_DESC)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }
}
