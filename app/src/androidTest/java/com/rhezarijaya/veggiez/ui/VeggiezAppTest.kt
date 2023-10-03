package com.rhezarijaya.veggiez.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rhezarijaya.veggiez.R
import com.rhezarijaya.veggiez.assertCurrentRouteName
import com.rhezarijaya.veggiez.data.model.VegetableDummy
import com.rhezarijaya.veggiez.onNodeWithContentDescriptionStringRes
import com.rhezarijaya.veggiez.onNodeWithStringRes
import com.rhezarijaya.veggiez.ui.navigation.Screen
import com.rhezarijaya.veggiez.ui.theme.VeggiezTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VeggiezAppTest {
    @get:Rule
    val androidComposeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navHostController: NavHostController

    @Before
    fun setup() {
        androidComposeTestRule.setContent {
            VeggiezTheme {
                navHostController = TestNavHostController(LocalContext.current)
                navHostController.navigatorProvider.addNavigator(ComposeNavigator())

                VeggiezApp(navHostController = navHostController)
            }
        }
    }

    @Test
    fun verifyStartDestination() {
        // memastikan route sekarang adalah Main Screen
        navHostController.assertCurrentRouteName(Screen.Main.route)
    }

    @Test
    fun goToFavoriteSuccess() {
        androidComposeTestRule.apply {
            // melakukan klik pada tombol favorite
            onNodeWithContentDescription("favorite_page").performClick()

            // memastikan route sekarang adalah Favorite Screen
            navHostController.assertCurrentRouteName(Screen.Favorite.route)

            // memastikan ada top bar dengan teks "Favorite" telah tampil
            onNodeWithStringRes(R.string.favorite).assertExists()
        }
    }

    @Test
    fun goToAboutSuccess() {
        androidComposeTestRule.apply {
            // melakukan klik pada tombol about
            onNodeWithContentDescription("about_page").performClick()

            // memastikan route sekarang adalah About Screen
            navHostController.assertCurrentRouteName(Screen.About.route)

            // memastikan nama dan email telah tampil
            onNodeWithStringRes(R.string.my_name).assertExists()
            onNodeWithStringRes(R.string.my_email).assertExists()
        }
    }

    @Test
    fun clickOnItemShouldGoToDetail() {
        androidComposeTestRule.apply {
            // melakukan klik pada item ke 9 (0 adalah search bar) di list
            onNodeWithTag("vegetable_list").performScrollToIndex(9)
            onNodeWithText(VegetableDummy.dummyData[8].name).performClick()

            // memastikan route sekarang adalah Detail Screen
            navHostController.assertCurrentRouteName(Screen.Detail.route)

            // memastikan ada name dan description yang tampil
            onNodeWithText(VegetableDummy.dummyData[8].name).assertExists()
            onNodeWithText(VegetableDummy.dummyData[8].description).assertExists()
        }
    }

    @Test
    fun searchDataShouldShowMatching() {
        androidComposeTestRule.apply {
            // lakukan pencarian data "selada"
            onNodeWithContentDescriptionStringRes(R.string.search, useUnmergedTree = true)
                .performTextInput("selada")

            // terdapat data "selada"
            onNodeWithText(VegetableDummy.dummyData[12].name, useUnmergedTree = true).assertExists()

            // tidak terdapat data "asparagus"
            onNodeWithText(
                VegetableDummy.dummyData[0].name,
                useUnmergedTree = true
            ).assertIsNotDisplayed()
        }
    }

    @Test
    fun addAndRemoveFavoriteSuccess() {
        val vegetableName = VegetableDummy.dummyData[0].name
        androidComposeTestRule.apply {
            // melakukan klik pada item vegetable pertama di list
            onNodeWithText(vegetableName).performClick()

            // memastikan route sekarang adalah Detail Screen
            navHostController.assertCurrentRouteName(Screen.Detail.route)

            // memastikan tombol favorite statusnya belum aktif
            onNodeWithContentDescriptionStringRes(
                R.string.save_on_favorite,
                useUnmergedTree = true
            ).assertExists()

            // lakukan klik pada tombol favorite
            onNodeWithContentDescriptionStringRes(
                R.string.save_on_favorite,
                useUnmergedTree = true
            ).performClick()

            // memastikan tombol favorite statusnya aktif
            onNodeWithContentDescriptionStringRes(
                R.string.remove_from_favorite,
                useUnmergedTree = true
            ).assertExists()

            // kembali ke main screen
            onNodeWithContentDescriptionStringRes(R.string.back).performClick()

            // melakukan klik pada tombol favorite
            onNodeWithContentDescription("favorite_page").performClick()

            // memastikan route sekarang adalah Favorite Screen
            navHostController.assertCurrentRouteName(Screen.Favorite.route)

            // memastikan tulisan no item tidak muncul
            onNodeWithStringRes(R.string.no_item).assertDoesNotExist()

            // melakukan klik pada item yang telah ditambah ke favorite
            onNodeWithText(vegetableName).performClick()

            // memastikan tombol favorite statusnya aktif
            onNodeWithContentDescriptionStringRes(
                R.string.remove_from_favorite,
                useUnmergedTree = true
            ).assertExists()

            // lakukan klik pada tombol favorite
            onNodeWithContentDescriptionStringRes(
                R.string.remove_from_favorite,
                useUnmergedTree = true
            ).performClick()

            // memastikan tombol favorite statusnya tidak aktif
            onNodeWithContentDescriptionStringRes(
                R.string.save_on_favorite,
                useUnmergedTree = true
            ).assertExists()

            // kembali ke favorite screen
            onNodeWithContentDescriptionStringRes(R.string.back).performClick()

            // memastikan tulisan no item muncul
            onNodeWithStringRes(R.string.no_item).assertExists()
        }
    }
}