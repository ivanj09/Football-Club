package com.ivanjt.footballclub

import com.google.gson.Gson
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.model.TeamResponse
import com.ivanjt.footballclub.presenter.TeamsPresenter
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.TeamsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = TeamsPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getTeamListTest() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        GlobalScope.launch {

            Mockito.`when`(
                gson.fromJson(
                    NetworkUtil
                        .doRequest(SportDbAPI.getTeams(league)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}