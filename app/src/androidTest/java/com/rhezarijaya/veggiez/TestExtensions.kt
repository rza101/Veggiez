package com.rhezarijaya.veggiez

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertEquals

fun <T : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.onNodeWithStringRes(
    @StringRes id: Int,
    useUnmergedTree: Boolean = false,
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id), useUnmergedTree)

fun <T : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.onNodeWithContentDescriptionStringRes(
    @StringRes id: Int,
    useUnmergedTree: Boolean = false,
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id), useUnmergedTree)

fun NavController.assertCurrentRouteName(expected: String) {
    assertEquals(expected, currentBackStackEntry?.destination?.route)
}