package com.aashu.kai.ui.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aashu.kai.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun OnboardingScreen(
    onOnboardingComplete: () -> Unit,
    viewModel: OnboardingViewModel = viewModel()
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 }
    )

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        viewModel.updateCurrentPage(pagerState.currentPage)
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        when (page) {

            0 -> StepOne(
                onNext = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )

            1 -> StepTwo(
                viewModel = viewModel,
                onBack = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                },
                onNext = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }
            )

            2 -> StepThree(
                viewModel = viewModel,
                onBack = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                },
                onFinish = onOnboardingComplete
            )
        }
    }
}