package ru.devkit.investapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.devkit.investapplication.app.App
import ru.devkit.service.data.SandboxPortfolioService
import ru.devkit.service.mock.MockStocksService
import javax.inject.Inject

/**
* @author k.i.tayupov
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mockPortfolioService: SandboxPortfolioService
    @Inject
    lateinit var mockStocksService: MockStocksService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        mockPortfolioService.start()
        mockStocksService.start()
    }

    override fun onStop() {
        super.onStop()
        mockPortfolioService.release()
        mockStocksService.release()
    }
}
