package com.example.ramadanchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ramadanchallenge.model.Data
import com.example.ramadanchallenge.model.list
import com.example.ramadanchallenge.ui.theme.RamadanChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RamadanChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RamadanApp()
                }
            }
        }
    }
}

@Composable
fun RamadanApp(){
    LazyColumn(modifier = Modifier.padding(top = 150.dp)){
        itemsIndexed(list){index,item->
            RamadanCard(data = item,day = index + 1,modifier = Modifier
                .fillMaxSize())
        }
    }
}

@Composable
fun Heading(modifier: Modifier){
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 5.dp)
    ){
        Text(
            text = stringResource(id = R.string.head),
            fontFamily = FontFamily.Serif,
            fontSize = 75.sp,
            lineHeight = 60.sp
        )
    }
}

@Composable
fun RamadanCard(data: Data,day : Int,modifier: Modifier){
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(top = 15.dp, start = 10.dp, end = 10.dp)
            .clickable { isExpanded = !isExpanded }
        ) {
        Column {
            Text(
                text = "Day $day",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )

            Text(
                text = stringResource(id = data.piece),
                fontFamily = FontFamily.Monospace,
                fontSize = 30.sp,
                lineHeight = 30.sp,
                modifier = Modifier.
                padding(start = 20.dp)
            )

            Image(
                painter = painterResource(id = data.image),
                contentDescription = "current image",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = shrinkVertically(animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy
                     )
                )
            ) {
                    Text(
                        text = stringResource(id = data.description),
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.
                        padding(start = 15.dp)
                    )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    RamadanChallengeTheme {
        RamadanApp()
    }
}