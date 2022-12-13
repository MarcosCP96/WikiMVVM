package com.example.wikimvvm.views

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.wikimvvm.R
import com.example.wikimvvm.model.ArticleResponse
import com.example.wikimvvm.model.ContentURLs
import com.example.wikimvvm.model.Mobile
import com.example.wikimvvm.model.Thumbnail
import org.junit.Rule
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class ArticleListFragmentTest {
    private lateinit var fragment: ArticleListFragment
    val article = ArticleResponse("", Thumbnail(""), "", ContentURLs(Mobile("")))

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun title() {
        sleep(1000)
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.backToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
    }
}