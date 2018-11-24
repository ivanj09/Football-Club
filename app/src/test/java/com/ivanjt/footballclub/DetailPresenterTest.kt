package com.ivanjt.footballclub

import com.google.gson.Gson
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.presenter.DetailPresenter
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.DetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetailTest() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val idEvent = "576630"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    NetworkUtil.doRequest(SportDbAPI.getMatchDetail(idEvent)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail(idEvent)

            Mockito.verify(view).showMatchDetail(events)
        }
    }
}