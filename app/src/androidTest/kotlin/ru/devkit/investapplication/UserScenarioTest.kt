package ru.devkit.investapplication

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import ru.devkit.investapplication.di.DaggerTestAppComponent
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
@RunWith(AndroidJUnit4::class)
class UserScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity {
            DaggerTestAppComponent.create().inject(it)
            navController.setGraph(R.navigation.nav_graph)
        }
    }

    @After
    fun tearDown() {
        activityScenarioRule.scenario.close()
    }

    @Test
    fun shouldStartWithSplashScreen() {
        assertEquals(R.id.splashScreenFragment, navController.currentDestination?.id)
    }

    @Test
    fun shouldNavigateFromSplashScreenToPortfolio() = runTest {
        activityScenarioRule.scenario.onActivity {
            navController.setCurrentDestination(R.id.splashScreenFragment)
        }
        delay(4_000)
        assertEquals(R.id.portfolioFragment, navController.currentDestination?.id)
    }
}
