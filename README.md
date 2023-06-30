# Newsify
Newsify is an Android app that brings news from news-api.org to your phone. You can add articles to your favourites list and delete them using swipe gestures.

## Screenshots
<p align="center"><img src="/screenshots/screenshot1.jpg" alt="Screenshot 1" width="200" height="400" /></p>
<p align="center"><img src="/screenshots/screenshot2.jpg" alt="Screenshot 2" width="200" height="400" /></p>
<p align="center"><img src="/screenshots/screenshot3.jpg" alt="Screenshot 3" width="200" height="400" /></p>

## Getting started
To get started with the app, clone the repository and open it in Android Studio:
<br>
`git clone https://github.com/iAbanoubSamir/Newsify.git`

## Build with
The app uses the following dependencies:
**- Dagger-Hilt:** Dependency injection library
**- Coroutines:** Concurrency library
**- Flow:** Asynchronous stream processing library
**- ViewModel:** Architecture component for managing UI-related data in a lifecycle-conscious way
**- Room:** SQLite database library
**- Retrofit2:** HTTP client library
**- okHttp:** HTTP client library
**- Navigation:** Navigation component for navigating between screens
**- Glide:** Image loading and caching library
**- ViewBinding:** Library for generating view binding classes

## Architecture
The app follows the Clean Architecture pattern, with separate layers for presentation, domain, and data. The MVVM pattern is used for the presentation layer.

## Acknowledgments
We used [news-api.org](news-api.org).

### License
<pre><code>Copyright 2023 Abanoub Samir
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.</code></pre>
