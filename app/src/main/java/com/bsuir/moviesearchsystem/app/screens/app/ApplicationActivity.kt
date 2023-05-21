package com.bsuir.moviesearchsystem.app.screens.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bsuir.moviesearchsystem.R
import com.bsuir.moviesearchsystem.app.dto.MovieAnswerDTO
import com.bsuir.moviesearchsystem.app.screens.Navigator
import com.bsuir.moviesearchsystem.app.screens.app.home.DetailMovieFragment
import com.bsuir.moviesearchsystem.databinding.ActivityApplicationBinding

class ApplicationActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityApplicationBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener
//    private val viewModel by viewModels<CabinetViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment_app)
        drawerLayout = binding.drawerLayout
        binding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_app)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun showDetailMovie(movie: MovieAnswerDTO) {
        val fragment = DetailMovieFragment.newInstance(movie)
        navController.navigate(R.id.detailMovieFragment, fragment.arguments)
    }


}