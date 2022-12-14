package com.example.wikimvvm.views

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.wikimvvm.R
import org.junit.Rule
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class ArticleListFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addAnArticleToFavourite_goToFavourite_andGoBack() {
        vaciarLista()
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                swipeUp()
            )
        ).check(matches(isDisplayed()))
        onView(withId(R.id.backToMenuButton)).perform(
            click()
        )
    }

    @Test
    fun articleWithTitleAndExtract() {
        vaciarLista()
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvTitle)).check(matches(withText("a1")))
        onView(withId(R.id.tvExtract)).check(matches(withText("a3")))
    }

    @Test
    fun addSecondItemToFavouritesAndDeleteIt() {
        vaciarLista()
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.deleteButton)).perform(
            click()
        )
    }

    @Test
    fun addTwoArticlesToFavouritesAndEmptyList() {
        vaciarLista()
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.emptyListButton)).perform(
            click()
        )
    }

    @Test
    fun addArticleToFavouritesThenDeleteFromFavourites() {
        vaciarLista()
        onView(withText("c1")).check(
            matches(isDisplayed())
        ).perform(
            click()
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.deleteButton)).perform(
            click()
        )
        onView(withId(R.id.backToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
    }

    @Test
    fun getNewListOfArticles_andAccessLastOne() {
        vaciarLista()
        onView(withId(R.id.randomButton)).perform(
            click()
        )
        sleep(2000)
        onView(withId(R.id.articleList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click()
            )
        )
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvExtract)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfArticlesAreDisplayed(){
        onView(withText("a1")).check(matches(isDisplayed()))
        onView(withText("b1")).check(matches(isDisplayed()))
        onView(withText("c1")).check(matches(isDisplayed()))
        onView(withText("d1")).check(matches(isDisplayed()))
        onView(withText("e1")).check(matches(isDisplayed()))
    }

    private fun vaciarLista() {
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.emptyListButton)).perform(
            click()
        )
    }
}
