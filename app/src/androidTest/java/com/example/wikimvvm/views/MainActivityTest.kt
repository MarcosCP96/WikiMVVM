package com.example.wikimvvm.views


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.wikimvvm.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.tvTitulo), childAtPosition(
                    childAtPosition(
                        withId(R.id.articleList),
                        0
                    ),
                    1
                ),
                withParent(withParent(withId(R.id.articleList))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val materialTextView = onView(
            allOf(
                withId(R.id.tvTitulo),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.articleList),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val appCompatButton = onView(
            allOf(
                withId(R.id.addToFavouriteButton), withText("add to favourite"),
                childAtPosition(
                    allOf(
                        withId(R.id.fragmentLayout),
                        childAtPosition(
                            withId(R.id.scrollView),
                            0
                        )
                    ),
                    4
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.backToMenuButton), withText("back"),
                childAtPosition(
                    allOf(
                        withId(R.id.fragmentLayout),
                        childAtPosition(
                            withId(R.id.scrollView),
                            0
                        )
                    ),
                    3
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.toFavourites), withText("saved articles"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.placeholder),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val materialTextView2 = onView(
            allOf(
                withId(R.id.tvTitulo),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.favouriteArticlesRecyclerView),
                        0
                    ),
                    1
                )
            )
        )
        materialTextView2.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
