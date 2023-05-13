package woowacourse.movie.movie.java

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.main.MainActivity
import woowacourse.movie.movielist.dto.AdDto
import woowacourse.movie.movielist.dto.MovieDummy

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun 리사이클러뷰가_화면에_보이는지_확인() {
        onView(withId(R.id.movie_rv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 리사이클러뷰_0번째_위치에_영화_데이터가_들어있는지_확인() {
        onView(withId(R.id.movie_rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieDummy.movieDatas[0].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_1번째_위치에_영화_데이터가_들어있는지_확인() {
        onView(withId(R.id.movie_rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieDummy.movieDatas[1].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_2번째_위치에_영화_데이터가_들어있는지_확인() {
        onView(withId(R.id.movie_rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
            .check(
                matches(
                    hasDescendant(
                        allOf(
                            withText(MovieDummy.movieDatas[2].title),
                            isDisplayed(),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun 리사이클러뷰_3번째_위치에_광고가_있는지_확인() {
        onView(withId(R.id.movie_rv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
            .check(matches(hasDescendant(allOf(withId(R.id.ad), isDisplayed()))))
    }

    @Test
    fun 리사이클러뷰_3번째_위치에_있는_광고를_눌렀을_때_우아한테크코스_사이트가_열리는지_확인() {
        onView(withId(R.id.movie_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click(),
                ),
            )

        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasData(Uri.parse(AdDto.getAdData().url)))
    }
}
