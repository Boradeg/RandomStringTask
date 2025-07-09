# 📱 Random String Generator App

A modern Android app built with **Jetpack Compose**, **MVVM**, and **Clean Architecture**, which fetches random strings from a custom `ContentProvider`, inserts them into a Room database, and displays them with formatted metadata.

---

## 🚀 Features

- 🔤 Fetch random strings from a custom `ContentProvider` (`content://com.iav.contestdataprovider/text`)
- 🧠 Insert fetched strings into a local **Room Database**
- 🗑️ Delete strings from Room using swipe
- 🌐 Format ISO UTC time into readable **local time**
- 🎨 Built fully using **Jetpack Compose**
- 💡 Uses `ViewModel`, `StateFlow`, `Hilt`, and `Coroutines`
- 📐 Built with **Clean Architecture** and **SOLID Principles**
- 🧩 Robust **error handling** and separation of concerns

---

## 🧱 Project Structure
com.app.randomstringtask/
├── Data/
│   ├── local/                   # Room DB, DAO, Entity
│   ├── mapper/                 # Model converters
│   └── Repository/            # Data layer implementation
│
├── di/                         # Hilt modules
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
│
├── domain/                     # Business logic
│   ├── Repository/
│   │   └── RandomStringRepository.kt
│   └── UseCases/
│       ├── FetchRandomStringUseCase.kt
│       ├── InsertRandomStringUseCase.kt
│       ├── ObserveRandomStringsUseCase.kt
│       ├── DeleteRandomStringUseCase.kt
│       └── DeleteAllRandomStringsUseCase.kt
│
├── presentations/
│   ├── mainactivity/           # Main entry point
│   ├── modal/                  # Modal UI components
│   ├── screens/                # Splash, Home, etc.
│   ├── ui.theme/               # Compose themes/colors
│   └── viewmodels/             # Shared ViewModel
│
├── utils/                      # Extensions and providers
│   └── RandomStringProvider.kt
│
└── Application.kt              # App class for Hilt



