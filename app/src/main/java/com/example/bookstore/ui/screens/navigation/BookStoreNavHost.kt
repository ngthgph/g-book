package com.example.bookstore.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.screens.book.BookDetailScreen
import com.example.bookstore.ui.screens.cart.CartScreen
import com.example.bookstore.ui.screens.categories.CategoriesScreen
import com.example.bookstore.ui.screens.categories.CategoryScreen
import com.example.bookstore.ui.screens.favorite.FavoriteScreen
import com.example.bookstore.ui.screens.home.HomeScreen
import com.example.bookstore.ui.utils.NavigationType
import com.example.bookstore.ui.utils.Screen

@Composable
fun BookStoreNavHost(
    navigationType: NavigationType,
    uiState: BookStoreUiState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(Screen.Home.name) {
            HomeScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Favorite.name) {
            FavoriteScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Cart.name) {
            CartScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Categories.name) {
            CategoriesScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Category.name) {
            CategoryScreen(
                uiState = uiState,
                modifier = modifier
            )
        }
        composable(Screen.Book.name) {
            BookDetailScreen(
                navigationType = navigationType,
                uiState = uiState,
                onButtonClick = { },
                modifier = modifier
            )
        }
    }
}