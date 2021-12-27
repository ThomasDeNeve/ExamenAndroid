# Android devops Project - T4

## Used Technology
This app is built using the MVVM-architecture. It fetches the rooms & reservations via API calls to the backend, using the single-source-of-truth principle. Data is saved to a local Room (Android) database. While fetching the data from API, it asynchronously loads the data from this Room database and updates it with the fetched (new) data. The app is fully lifecycle-aware and uses coroutines where needed. 
Used technologies:
- Kotlin
- coroutines
- databinding
- bindingAdapters
- Room (database)
- Auth0 for logging in & registering
- ktlint (org.jlleitschuh.gradle:ktlint-gradle:10.2.0)
- ...

## How to run
In Android Studio, press 'run app' & have fun! Alternatively, you could download & install the APK on your Android device from [here](http://ec2-3-212-186-23.compute-1.amazonaws.com:8080/) (thanks Ops team!)

Our app will only work if the backend is running online! 
For testing purposes, run the backend locally and uncomment the line below in build.gradle(module) :
>buildConfigField 'String', 'BASE_URL', "\"https://10.0.2.2:5001/\""



To perform a ktlint check, type this in terminal:
> gradlew ktlintCheck

The output should look like this: 
![ktlint_success](https://user-images.githubusercontent.com/17140451/147497553-989d653a-db32-43a3-99a1-23e0de0dc177.png)

## Members
- Groupmember 1 : thomasboghaert - Boghaert - Thomas
- Groupmember 2 : LorenzBaeleOfficial - Baele - Lorenz
- Groupmember 3 : YvesVanduynslager - Vanduynslager - Yves
- Groupmember 4 : MaartenGoethals - Goethals - Maarten
- Groupmember 5 : ThomasDeNeve - De Neve - Thomas
- Groupmember 6 : CuyLil - Cuypers - Liliane

## MVVM
![image](https://user-images.githubusercontent.com/17140451/147498646-18700c66-ddf7-4887-a288-d648671d2dd6.png)

## Single source of truth
![image](https://user-images.githubusercontent.com/17140451/147498672-bab04634-a790-495c-84bb-9234d971785b.png)

