package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

data class AndroidAppConcept(
    val title: String,
    val tagline: String,
    val category: String,
    val frugalCost: String,
    val enterpriseCost: String,
    val savings: String,
    val features: List<String>,
    val frugalHack: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppForgeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppForgeApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var currentConcept by remember {
        mutableStateOf(
            AndroidAppConcept(
                title = "FocusForge",
                tagline = "Audio & Mindmap Study Engine",
                category = "EdTech / Productivity",
                frugalCost = "$0.00 / mo",
                enterpriseCost = "$420.00 / mo",
                savings = "100% Savings",
                features = listOf(
                    "On-device Speech Recognition (zero cloud transcription cost)",
                    "SM-2 Spaced Repetition in local SQLite (Room)",
                    "Vector Canvas mindmap with zero external dependencies",
                    "P2P Wi-Fi Direct sync for classmate deck sharing"
                ),
                frugalHack = "Run SM-2 algorithmic reviews in Kotlin on device; avoid paying recurring cloud database read quotas."
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Build,
                            contentDescription = "Forge",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AppForge",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            currentConcept = AndroidAppConcept(
                                title = "BazaarDirect",
                                tagline = "Zero-Commission Local Farmer & Baker Market",
                                category = "Hyperlocal / Social",
                                frugalCost = "$0.00 / mo",
                                enterpriseCost = "$680.00 / mo",
                                savings = "100% Savings",
                                features = listOf(
                                    "Haversine bounding box in SQLite (Zero Google Maps API bills)",
                                    "Direct WhatsApp & UPI payment deep links (Zero 30% aggregator cut)",
                                    "On-device WebP image compression to 80KB",
                                    "Zero cloud server requirements for micro-bakers"
                                ),
                                frugalHack = "Bypass paid payment gateways completely by dispatching WhatsApp order intents with pre-filled line items."
                            )
                        },
                        modifier = Modifier.testTag("btn_surprise")
                    ) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = "Surprise Concept",
                            tint = Color(0xFFFFB74D)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = "Idea Lab") },
                    label = { Text("Idea Lab") },
                    modifier = Modifier.testTag("tab_idea_lab")
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Smartphone, contentDescription = "Wireframes") },
                    label = { Text("Wireframes") },
                    modifier = Modifier.testTag("tab_wireframes")
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.AttachMoney, contentDescription = "Frugal Stack") },
                    label = { Text("Frugal Stack") },
                    modifier = Modifier.testTag("tab_frugal_stack")
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.Code, contentDescription = "Codebase") },
                    label = { Text("Codebase") },
                    modifier = Modifier.testTag("tab_codebase")
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            when (selectedTab) {
                0 -> IdeaLabScreen(concept = currentConcept)
                1 -> WireframeScreen(concept = currentConcept)
                2 -> FrugalCostScreen(concept = currentConcept)
                3 -> CodebaseScreen(concept = currentConcept)
            }
        }
    }
}

@Composable
fun IdeaLabScreen(concept: AndroidAppConcept) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth().testTag("concept_card")
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = concept.category,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFF10B981).copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = concept.savings,
                                color = Color(0xFF10B981),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Text(
                        text = concept.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = concept.tagline,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            Text(
                text = "Core Features (MVP)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(concept.features) { feat ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = feat, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun WireframeScreen(concept: AndroidAppConcept) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth().testTag("wireframe_summary_card")
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "UX Architecture & Wireframes",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "Thumb Zone: Bottom 40%",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Text(
                        text = "Screen Wireframe: ${concept.title} Dashboard",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Purpose: ${concept.tagline}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            Text(
                text = "Interactive Screen Component Wireframe",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Mock Phone Wireframe Card
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Top App Bar
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = concept.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = "9:41 AM • 5G", color = Color(0xFF94A3B8), fontSize = 10.sp)
                        }
                    }

                    // Hero Focus Element
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = Color(0xFF1E1B4B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Primary Focus Component", color = Color(0xFFA5B4FC), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(text = concept.features.firstOrNull() ?: "Core interactive element", color = Color.White, fontSize = 13.sp)
                            Button(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6366F1))
                            ) {
                                Text("Primary Action (Thumb Sweet Spot)", fontSize = 12.sp)
                            }
                        }
                    }

                    // Scannable List Item
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF1E293B),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = "Secondary Action Item", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                Text(text = "Hick's Law: Progressive disclosure", color = Color(0xFF94A3B8), fontSize = 10.sp)
                            }
                            Text(text = "Ready", color = Color(0xFF10B981), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "UX Design Principle Rationale",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Fitts's Law & Thumb Zone Ergonomics:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Primary trigger buttons are positioned in the bottom 40% of the screen with a minimum touch target height of 48dp, minimizing reach strain and accidental drops.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Hick's Law (Decision Minimization):",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Reduces choice overload by establishing a single dominant Call to Action on the main screen, tucking complex configuration inside contextual bottom sheets.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun FrugalCostScreen(concept: AndroidAppConcept) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B)),
                modifier = Modifier.fillMaxWidth().testTag("cost_summary_card")
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "कम खर्च में पूरा काम",
                        color = Color(0xFF6EE7B7),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Run for ${concept.frugalCost}",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "Instead of standard enterprise bloat (${concept.enterpriseCost})",
                        color = Color(0xFFA7F3D0),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        item {
            Text(
                text = "Frugal Engineering Strategy",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Architectural Hack:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = concept.frugalHack,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CodebaseScreen(concept: AndroidAppConcept) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        item {
            Text(
                text = "Clean Architecture Compose Snippet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                modifier = Modifier.fillMaxWidth().testTag("code_block")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = """
// Offline-First Room Repository Pattern
class ${concept.title.replace(" ", "")}Repository(
    private val localDao: AppRecordDao,
    private val api: GeminiApiService
) {
    val recordsStream = localDao.getAllRecords()

    suspend fun sync() {
        // Caches 95% of reads locally
        val pending = localDao.getPendingSyncRecords()
        if (pending.isNotEmpty()) {
            api.batchUpload(pending)
            localDao.markAsSynced(pending.map { it.id })
        }
    }
}
                        """.trimIndent(),
                        color = Color(0xFFE2E8F0),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
