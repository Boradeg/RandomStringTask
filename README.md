# 📱 Random String Generator App

A modern Android app built with **Jetpack Compose**, **MVVM**, and **Clean Architecture**, which fetches random strings from a custom `ContentProvider`, inserts them into a Room database, and displays them with formatted metadata.

---

## 🚀 Features

- 🔤 Fetch random strings from a custom `ContentProvider` (`content://com.iav.contestdataprovider/text`)
- 🧠 Insert fetched strings into a local **Room Database**
- 🗑️ Delete strings from Room 
- 🌐 Format ISO UTC time into readable **local time**
- 🎨 Built fully using **Jetpack Compose**
- 💡 Uses `ViewModel`, `StateFlow`, `Hilt`, and `Coroutines`
- 📐 Built with **Clean Architecture** and **SOLID Principles**
- 🧩 Robust **error handling** and separation of concerns

---

## 🧱 Project Structure

- **com.app.randomstringtask**
  - **Data**
    - local/ → AppDatabase, DAO, Entity
    - mapper/ → Data Mappers (DTO ↔ Domain)
    - Repository/ → Implementation of domain repo
  - **di**
    - DatabaseModule.kt
    - RepositoryModule.kt
  - **domain**
    - Repository/ → RandomStringRepository (interface)
    - UseCases/
      - FetchRandomStringUseCase.kt
      - InsertRandomStringUseCase.kt
      - ObserveRandomStringsUseCase.kt
      - DeleteRandomStringUseCase.kt
      - DeleteAllRandomStringsUseCase.kt
  - **presentations**
    - mainactivity/ → MainActivity
    - modal/ → Bottom sheets, dialogs
    - screens/ → Splash, Home screens
    - ui.theme/ → Jetpack Compose theme files
    - viewmodels/ → RandomStringViewModel
  - **utils**
    - RandomStringProvider.kt
  - **Application.kt** → Hilt app class

