package ru.devkit.investapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.devkit.investapplication.app.App
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.StocksServiceApi
import javax.inject.Inject

/**
* @author k.i.tayupov
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mockPortfolioService: PortfolioServiceApi
    @Inject
    lateinit var mockStocksService: StocksServiceApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        mockPortfolioService.attach()
        mockStocksService.attach()
    }

    override fun onStop() {
        super.onStop()
        mockPortfolioService.release()
        mockStocksService.release()
    }
}
