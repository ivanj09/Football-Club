package com.ivanjt.footballclub

import com.google.gson.Gson
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.presenter.LastMatchesPresenter
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.LastMatchesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LastMatchesPresenterTest {
    @Mock
    private lateinit var view: LastMatchesView

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: LastMatchesPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = LastMatchesPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getLastMatchesTest() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val idLeague = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    NetworkUtil.doRequest(SportDbAPI.getLastMatches(idLeague)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLastMatches(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLastMatches(events)
            Mockito.verify(view).hideLoading()
        }
    }
}