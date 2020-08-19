# firebase-android-mvvm-tutorial
Firebase exercise project that integrates Kotlin Coroutines, Koin, Room, Navigation Architecture Components, ViewModel/LiveData, and Data Binding.
# How to setup?
- Go to the Firebase console and create a project
- Download google-services and put it in /app directory.
- Run the application.
# FireStore
- Go to the Firebase Database
- Modify the rule to allow read and write
- Create users collection

collection -------- document ----------------------------field----------------------------                                                                          
  users              autoId            name(String), email(String), created(Timestamp)                                                                              
  	users------autoId---------name(String)
                     L--------email(String)
                     L--------created(Timestamp)
  
# Authentication
- Go to the Firebase Authentication
- GO to the sign-in method tab and enable email password

# Crashlytics
- When the testcrash button is pressed in the app, a runtime exception is raised
- Go to the crash tab of the Firebase homepage and check

# Storage
- To be added

# Machine Learning
- To be added
