# Sleep Tracker — Android Kotlin M5

**STEP IT Academy** — Android Mobile Application Development (Kotlin + XML UI)
> Module 5 — RecyclerView: ListAdapter, DiffUtil, DataBinding Adapters, GridLayout, Click Listeners, List Headers

---

## Screenshots

![Home](app/src/main/ic_launcher_sleep_tracker-web.png)

---

## About

Sleep Tracker lets you record when you go to sleep and when you wake up, then rate your sleep quality. This module extends the M4 Room version by replacing the static ScrollView with a **RecyclerView** — you will progressively refactor the adapter from a basic `RecyclerView.Adapter` all the way to a `ListAdapter` with `DiffUtil`, DataBinding, Binding Adapters, and a GridLayout with a header item.

---

## Architecture

**Pattern:** MVVM (Model–View–ViewModel)

```
+----------------------------------------------------------+
|                        UI Layer                          |
|  SleepTrackerFragment  -->  SleepTrackerViewModel        |
|  SleepQualityFragment  -->  SleepQualityViewModel        |
|  SleepDetailFragment   -->  SleepDetailViewModel         |
+----------------------------+-----------------------------+
                             | LiveData (observe)
+----------------------------v-----------------------------+
|                      Data Layer                          |
|  SleepDatabaseDao  <--  SleepDatabase (Room)            |
|  SleepNight (Entity)                                     |
+----------------------------------------------------------+

RecyclerView Stack:
  SleepNightAdapter (ListAdapter + DiffUtil)
    +-- SleepNightDiffCallback
    +-- ViewHolder (DataBinding)
    +-- BindingUtils (@BindingAdapter)
    +-- Header item (sealed DataItem)
```

**Key concepts:** RecyclerView, ListAdapter, DiffUtil, DataBinding in adapter, BindingAdapters, GridLayoutManager, click listeners, SafeArgs navigation, Room + Coroutines, LiveData.map

---

## Project Structure

```
app/src/main/java/com/example/android/trackmysleepquality/
├── MainActivity.kt
├── Util.kt                          # formatNights() helper, Spanned formatting
├── database/
│   ├── SleepNight.kt               # Room Entity
│   ├── SleepDatabaseDao.kt         # Room DAO
│   └── SleepDatabase.kt            # Room Database (singleton)
├── sleeptracker/
│   ├── SleepTrackerFragment.kt     # Main list screen
│   ├── SleepTrackerViewModel.kt    # Start/stop/clear logic + LiveData
│   ├── SleepTrackerViewModelFactory.kt
│   ├── SleepNightAdapter.kt        # RecyclerView ListAdapter + DiffUtil
│   └── BindingUtils.kt             # @BindingAdapter extensions
├── sleepquality/
│   ├── SleepQualityFragment.kt     # Rate sleep quality screen
│   ├── SleepQualityViewModel.kt
│   └── SleepQualityViewModelFactory.kt
└── sleepdetail/
    ├── SleepDetailFragment.kt      # Detail view for one night
    ├── SleepDetailViewModel.kt
    └── SleepDetailViewModelFactory.kt
```

---

## Navigation Flow

```
SleepTrackerFragment
    |
    +---> (START + STOP) SleepQualityFragment
    |           | sleepNightKey: Long (SafeArgs)
    |           +---> back to SleepTrackerFragment (popUpTo inclusive)
    |
    +---> (tap list item) SleepDetailFragment
                | sleepNightKey: Long (SafeArgs)
                +---> back to SleepTrackerFragment (popUpTo inclusive)
```

---

## Tech Stack

| Library | Version |
|---|---|
| Android Gradle Plugin | 9.0.1 |
| Gradle Wrapper | 9.2.1 |
| Kotlin (bundled with AGP) | — |
| AndroidX Core KTX | 1.18.0 |
| AppCompat | 1.7.1 |
| Material | 1.13.0 |
| ConstraintLayout | 2.2.1 |
| Navigation Component | 2.9.7 |
| Lifecycle ViewModel + LiveData | 2.9.0 |
| Room | 2.7.1 |
| Coroutines (Android) | 1.10.2 |
| KSP | 2.1.20-1.0.32 |

---

## Build Requirements

| Tool | Version |
|---|---|
| Android Studio | Meerkat (2024.3+) |
| JDK | 21 |
| Min SDK | 24 |
| Target SDK | 36 |
| Compile SDK | 36 |

**JDK setup in Android Studio:**
`File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK` → select **Embedded JDK**

---

## How to Work with This Repo

### Branch structure

| Branch pattern | Purpose |
|---|---|
| `main` | Complete solution — full working app |
| `Step.XX-Exercise-<Topic>` | Starter code with TODO comments |
| `Step.XX-Solution-<Topic>` | Reference answer for the exercise |

### Workflow per step

```bash
# 1. Check out the exercise branch for the step you are on
git checkout Step.01-Exercise-Add-a-RecyclerView

# 2. Open in Android Studio — find TODOs in the TODO panel (View > Tool Windows > TODO)

# 3. Complete each TODO in order

# 4. Compare with the solution branch
git diff Step.01-Solution-Add-a-RecyclerView
```

---

## Exercise Steps

| Step | Branch | Topic | Key Files |
|---|---|---|---|
| 01 | Step.01-Exercise-Add-a-RecyclerView | Add RecyclerView to layout | `fragment_sleep_tracker.xml`, `SleepTrackerFragment.kt` |
| 02 | Step.02-Exercise-Display-Data | Create ViewHolder and Adapter | `SleepNightAdapter.kt` |
| 03 | Step.03-Exercise-Recycling-ViewHolders | Efficient ViewHolder pattern | `SleepNightAdapter.kt` |
| 04 | Step.04-Exercise-Display-SleepQuality-List | Show sleep quality in list | `SleepNightAdapter.kt`, `list_item_sleep_night.xml` |
| 05 | Step.05-Exercise-Refactor-onBindViewHolder | Refactor bind logic into ViewHolder | `SleepNightAdapter.kt` |
| 06 | Step.06-Exercise-Refactor-onCreateViewHolder | Refactor ViewHolder creation | `SleepNightAdapter.kt` |
| 07 | Step.07-Exercise-Add-DiffUtil-to-Adapter | ListAdapter + DiffUtil | `SleepNightAdapter.kt` |
| 08 | Step.08-Exercise-Add-DataBinding-to-Adapter | DataBinding in ViewHolder | `SleepNightAdapter.kt`, `list_item_sleep_night.xml` |
| 09 | Step.09-Exercise-Add-Binding-Adapters | @BindingAdapter extensions | `BindingUtils.kt` |
| 10 | Step.10-Exercise-Replace-LinearLayout-with-GridLayout | GridLayoutManager | `SleepTrackerFragment.kt` |
| 11 | Step.11-Exercise-Implement-a-Click-Listener | Click listener in adapter | `SleepNightAdapter.kt`, `SleepTrackerFragment.kt` |
| 12 | Step.12-Exercise-Navigate-on-Click | Navigate to detail with SafeArgs | `SleepTrackerFragment.kt`, `SleepTrackerViewModel.kt` |
| 13 | Step.13-Exercise-Add-a-List-Header | Header item with sealed class | `SleepNightAdapter.kt` |
| 14 | Step.14-Exercise-Add-Header-to-GridLayout | GridLayoutManager spanSizeLookup | `SleepTrackerFragment.kt` |

---

## Course Info

- **Instructor:** Magn
- **Organization:** [chamkartechcambodia-sudo](https://github.com/chamkartechcambodia-sudo)
- **Course:** Android Mobile Application Development — Kotlin + XML UI
- **Batch:** Batch 1 · Module 5 (Day 19–20)
