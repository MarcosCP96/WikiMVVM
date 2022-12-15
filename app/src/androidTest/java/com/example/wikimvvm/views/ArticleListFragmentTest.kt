package com.example.wikimvvm.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.wikimvvm.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ArticleListFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

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
        onView(withText("c1")).perform(
            click()
        )
        onView(withId(R.id.addToFavouriteButton)).perform(
            click()
        )
//        onView(withText("c1 añadido a favoritos")).inRoot(
//            withDecorView(
//                not(
//                    decorView
//                )
//            )
//        ).check(
//            matches(
//                isDisplayed()
//            )
//        )
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
    fun checkIfArticlesAreDisplayed() {
        onView(withText("a1")).check(matches(isDisplayed()))
        onView(withText("b1")).check(matches(isDisplayed()))
        onView(withText("c1")).check(matches(isDisplayed()))
        onView(withText("d1")).check(matches(isDisplayed()))
        onView(withText("e1")).check(matches(isDisplayed()))
    }

    @Test
    fun checkArticlesListSize(){
        onView(withId(R.id.articleList)).check(
            matches(recyclerViewSizeMatcher(5))
        )

    }

    private fun vaciarLista() {
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.emptyListButton)).perform(
            click()
        )
    }

    private fun recyclerViewSizeMatcher(matcherSize: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with list size: $matcherSize")
            }
            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return matcherSize == recyclerView.adapter!!.itemCount
            }
        }
    }
    private var decorView: View? = null

    @Before
    fun setUp() {
        activityRule.scenario.onActivity(ActivityAction<MainActivity> { activity ->
            decorView = activity.window.decorView
        })
    }
}


