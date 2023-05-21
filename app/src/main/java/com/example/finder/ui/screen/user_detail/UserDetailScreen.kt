package com.example.finder.ui.screen.user_detail

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.finder.model.User
import com.example.finder.model.toDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    navController: NavController,
    userDetailViewModel: UserDetailViewModel
) {
    val userFromFlow by userDetailViewModel.user.collectAsState(null)

    val userState = remember { mutableStateOf(userDetailViewModel.userFromParam) }

    LaunchedEffect(userFromFlow) {
        if (userFromFlow != null) {
            userState.value = userFromFlow!!.toDto()
            Log.d("userDetails", userFromFlow!!.toDto().toString())
        }
    }
    val isLoadingUser by userDetailViewModel.isLoadingUser

    val user = userState.value!!
    Log.d("userDetails_user", user.toString())

    val gradientBrush = Brush.horizontalGradient(
        if (user.pronouns.lowercase() == "he/him") {
            listOf(
                Color(0xFF6495ED), // Light blue gradient for men
                Color(0xFF4169E1)  // Dark blue gradient for men (slightly lighter color)
            )
        } else {
            listOf(
                Color(0xFFFFA500), // Orange gradient for women
                Color(0xFFFFD700)
            )
        }
    )
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Profile details",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        content = {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "go back")
                        }
                    )
                }
            )
        },
        content = {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = rememberImagePainter(
                data = user.imageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(
                    width = 6.dp,
                    brush = gradientBrush,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = gradientBrush,
                )
                .padding(horizontal = 16.dp, vertical = 8.dp).
                    align(Alignment.CenterHorizontally)

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${user.firstName} ${user.lastName}, ${user.age}",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    brush = gradientBrush
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = user.pronouns,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "From ${user.city}",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Phone Number: ${user.phoneNumber}",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(40.dp)

                    .border(0.dp, color = Color.Transparent)
                    .background(
                        brush = gradientBrush
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            ) {
                Text(text = "Call", color = Color.Black
                    ,  style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),)
            }

            Button(
                onClick = { if(!isLoadingUser)userDetailViewModel.toggleLike(user)},
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(40.dp)
                    .background(
                        brush = gradientBrush
                    )
                    .border(0.dp, color = Color.Transparent),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            ) {

                Crossfade(targetState = isLoadingUser) {isLoading ->
                    if(isLoading)
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(color= Color.Black, modifier = Modifier.height(20.dp) )
                        }
                    else {
                        Text(
                            text = if(user.isLiked) "Dislike" else "Like",
                            color = Color.Black,
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                        )
                    }

                }

            }
        }

    }})
}
