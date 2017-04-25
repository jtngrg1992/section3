package com.example.jatingarg.section3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jatingarg on 25/04/17.
 */

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static junit.framework.Assert.assertTrue;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testApp(){
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withId(R.id.submitButton)).check(matches(isDisplayed()));

        onView(withId(R.id.email)).perform(typeText(mActivityTestRule.getActivity().getResources().getString(R.string.correct_email)));
        onView(withId(R.id.password)).perform(typeText(mActivityTestRule.getActivity().getResources().getString(R.string.correct_password)));
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.indicatorImage)).check(matches(isDisplayed()));
        onView(withId(R.id.indicatorMessage)).check(matches(withText("Login Successful!")));

        Espresso.pressBack();
        onView(withId(R.id.email)).perform(typeText("test123@doSelect.com"));
        onView(withId(R.id.password)).perform(typeText("test"));

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.indicatorMessage)).check(matches(withText("Login Failure!")));
    }

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }

    public static class DrawableMatcher extends TypeSafeMatcher<View> {
        private final int expectedId;
        String resourceName;

        public DrawableMatcher(int resourceId) {
            super(View.class);
            this.expectedId = resourceId;
        }

        @Override
        protected boolean matchesSafely(View target) {
            if (!(target instanceof ImageView)){
                return false;
            }
            ImageView imageView = (ImageView) target;
            if (expectedId < 0){
                return imageView.getDrawable() == null;
            }
            Resources resources = target.getContext().getResources();
            Drawable expectedDrawable = resources.getDrawable(expectedId);
            if (expectedDrawable == null) {
                return false;
            }
            BitmapDrawable bmd = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = bmd.getBitmap();
            BitmapDrawable expected = (BitmapDrawable) expectedDrawable;
            Bitmap otherBitmap = expected.getBitmap();
            return bitmap.sameAs(otherBitmap);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with drawable from resource id: ");
            description.appendValue(expectedId);
            if (resourceName != null) {
                description.appendText("[");
                description.appendText(resourceName);
                description.appendText("]");
            }
        }
    }
}
