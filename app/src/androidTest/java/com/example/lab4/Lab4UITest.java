package com.example.lab4;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Lab4UITest {

    @Rule
    public ActivityScenarioRule<AddNoteActivity> activityRule =
            new ActivityScenarioRule<>(AddNoteActivity.class);

    @Test
    public void testAddNoteFlow() {
        onView(withId(R.id.etInputName)).perform(typeText("Lab 6 Test"), closeSoftKeyboard());
        onView(withId(R.id.etInputContent)).perform(typeText("Testing UI interaction"), closeSoftKeyboard());
        onView(withId(R.id.btnSaveNote)).perform(click());
    }
}