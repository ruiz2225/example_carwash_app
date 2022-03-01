package com.example.carwashapp.view.services

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carwashapp.R
import com.example.carwashapp.utils.ServiceListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ServiceList(
    state: ServiceListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    onItemSelect: (String) -> Unit,
    deleteService: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = refreshData
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.services,
                    key = { service ->
                        service.id //El Key lo usamos para prevenir el error de recomposición de la lista
                    }
                ){  service ->
                    Divider()
                    var isDeleted by remember { mutableStateOf(false) }
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToEnd) isDeleted = !isDeleted
                            it != DismissValue.DismissedToEnd
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.StartToEnd),
                        dismissThresholds = {
                            FractionalThreshold(0.5f)
                        },
                        background = {
                            val direction = dismissState.dismissDirection ?:  return@SwipeToDismiss
                            val color by animateColorAsState(
                                when(dismissState.targetValue) {
                                    DismissValue.Default -> Color.LightGray
                                    DismissValue.DismissedToEnd -> Color.Red
                                    DismissValue.DismissedToStart -> Color.Red
                                }
                            )
                            val alignment = when (direction) {
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                            }
                            val icon = when (direction) {
                                DismissDirection.StartToEnd -> Icons.Default.Delete
                                DismissDirection.EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = stringResource(id = R.string.desc_icon_cards),
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        }
                    ) {
                        if(isDeleted) {
                            deleteService(service.id)
                        } else {
                            ServiceListItem(service, onItemSelect)
                        }
                    }
                }
            }
        }

        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}
