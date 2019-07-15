package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 29/06/2019
 * Time: 10:48
 * TesteAndroidv2_CleanCode
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatementListActivityAndroidTest {

    private StatementListViewModel statementListViewModel;
    private StatementListWorker statementListWorker;
    private static final int ITEM_BELOW_THE_FOLD = 2;

    @Rule
    public ActivityTestRule<StatementListActivity> mActivityRule =
            new ActivityTestRule<StatementListActivity>(StatementListActivity.class) {

                @Override
                protected Intent getActivityIntent() {
                    UserAccount userAccount = new UserAccount(1, "Fernando", "1234", "65432-1", 1000.00d);

                    Intent statementListIntent = new Intent();
                    statementListIntent.putExtra(Constants.USERACCOUNT_USERID, (Serializable) userAccount);

                    return statementListIntent;
                }

                @Override
                protected void afterActivityLaunched() {
                    super.afterActivityLaunched();


                }
            };

    @Test
    public void setupComponents_isValid() {

        onView(withId(R.id.statement_activity_et_name)).check(matches(isDisplayed()));
        onView(withId(R.id.statement_activity_et_acoountagency)).check(matches(isDisplayed()));
        onView(withId(R.id.statement_activity_et_balance)).check(matches(isDisplayed()));
        onView(withId(R.id.statement_activity_btn_close)).check(matches(isDisplayed()));

        onView(withId(R.id.statement_activity_rv_statement)).check(matches(isDisplayed()));
    }

    @Test
    public void testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.statement_activity_rv_statement))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCaseForRecyclerClick() {

        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Espresso.onView(ViewMatchers.withId(R.id.statement_activity_rv_statement))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testCaseForRecyclerScroll() {

        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Get total item of RecyclerView
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.statement_activity_rv_statement);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.statement_activity_rv_statement))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }


    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}