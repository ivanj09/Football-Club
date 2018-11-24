package com.ivanjt.footballclub

import com.google.gson.Gson
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.presenter.NextMatchesPresenter
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.NextMatchesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchesPresenterTest {
    @Mock
    private lateinit var view: NextMatchesView

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: NextMatchesPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = NextMatchesPresenter(view, gson, TestContextProvider())
    }

    @Test
    fun getNextMatchesTest() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val idLeague = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    NetworkUtil.doRequest(SportDbAPI.getNextMatches(idLeague)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatches(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextMatches(events)
            Mockito.verify(view).hideLoading()
        }
    }
}