package com.ivanjt.footballclub.Utility

import java.net.URL

object NetworkUtil {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}