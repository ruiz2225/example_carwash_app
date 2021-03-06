package com.example.carwashapp.view.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carwashapp.R
import com.example.carwashapp.navigation.AppScreens
import com.example.carwashapp.utils.ServiceListState

@Composable
fun ServiceListScreen(
    navController: NavController,
    state: ServiceListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    onItemSelect: (String) -> Unit,
    deleteService: (String) -> Unit){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.desc_icon_button),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(start = 16.dp))
            Text(text = stringResource(id = R.string.title_topbar_lisscreen),
                modifier = Modifier.padding(start = 32.dp))
        }
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(AppScreens.ServiceDetail.route+ "?serviceId={serviceId}") }, backgroundColor = MaterialTheme.colors.primary) {
            Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.desc_icon_button))
        }
    }) {
        ServiceList(state, isRefreshing, refreshData, onItemSelect, deleteService)
    }
}