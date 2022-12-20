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
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.wikimvvm.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.isDialog
import com.example.wikimvvm.FileReader
import com.example.wikimvvm.library.OkHttp3IdlingResource
import com.example.wikimvvm.model.OkHttpProvider
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Assert.assertEquals
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
@LargeTest
class ArticleListFragmentTest {
    private var mockWebServer = MockWebServer()
    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource
    private val abc = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    private var indexRequest = 0

    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        okHttp3IdlingResource = OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.instance
        )
        IdlingRegistry.getInstance().register(
            okHttp3IdlingResource
        )
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                indexRequest++
                val letra = abc[indexRequest % 10]
                println("request.sequenceNumber ${indexRequest % 10} -> ${request.path}")
                return when (request.path) {
                    "/api/rest_v1/page/random/summary" ->
                        MockResponse().setBody(
                            "{\"title\":\"${letra}1\",\"thumbnail\":{\"source\":\"${letra}2\"}," + "\"extract\":\"${letra}3\"," + "\"content_urls\":{\"mobile\":{\"page\":\"${letra}4\"}}}"
                        ).setResponseCode(200)
                    else ->
                        MockResponse().setBody(
                            "{\"title\":\"${letra}1\",\"thumbnail\":{\"source\":\"${letra}2\"}," + "\"extract\":\"${letra}3\"," + "\"content_urls\":{\"mobile\":{\"page\":\"${letra}4\"}}}"
                        ).setResponseCode(200)
                }

            }
        }

        mockWebServer.start(8080)
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
        }
        println(OkHttpProvider.baseUrl)
        OkHttpProvider.baseUrl = mockWebServer.url("/").toString()
        println(OkHttpProvider.baseUrl)
        activityRule.scenario.onActivity(ActivityAction<MainActivity> { activity ->
            decorView = activity.window.decorView
        })
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
    }

    @Test
    fun checkIfArticlesAreDisplayed() {
//        mockWebServer.dispatcher = object : Dispatcher() {
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("success_response.json"))
//            }
//        }
        onView(withText("a1")).check(matches(isDisplayed()))
//        onView(withText("b1")).check(matches(isDisplayed()))
//        onView(withText("c1")).check(matches(isDisplayed()))
//        onView(withText("d1")).check(matches(isDisplayed()))
//        onView(withText("e1")).check(matches(isDisplayed()))
    }

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
        onView(withText(R.string.toast_string))
            .inRoot(RootMatchers.withDecorView(isDisplayed()))
            .check(matches(isDisplayed()))
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
        sleep(3000)
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
    fun checkArticlesListSize() {
        sleep(2000)
        onView(withId(R.id.articleList)).check(
            matches(recyclerViewSizeMatcher(10))
        )
    }

    @Test
    fun addArticleToFavourites_deleteItFromFavourites_checkFavouritesAreEmpty() {
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
                click()
            )
        )
        onView(withId(R.id.articleBackToMenuButton)).perform(
            click()
        )
        onView(withId(R.id.emptyListButton)).perform(
            click()
        )
        onView(withId(R.id.toFavourites)).perform(
            click()
        )
        onView(withId(R.id.favouriteArticlesRecyclerView)).check(
            matches(recyclerViewSizeMatcher(0))
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
}


