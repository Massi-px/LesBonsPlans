# LesBonsPlans
Application de recherche d'annonce issue de plusieurs site

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Contributing](#contributing)


## Features

- Search for advertisements from multiple sites.
- Save and manage listings.
- Customizable search parameters.
- Context menu for saving listings.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Rdjaouzi/LesBonsPlans.git
    ```
2. **Open the project in your favorite IDE**:
3. **Run the Docker compose file**:
   
4. **site-annonce**:
   - mvn clean install
   - run the file db.sql in the database
   - and deploy the project war in Tomcat 9 server

5. **APP-GUI**:
    

## Usage

1. Use the search interface to enter keywords, select sites, and set refresh frequency.
2. View and manage the search results in the listings view.

## Project Structure

- `src/main/java/com/coding/app/appgui/` - Contains the GUI controllers.
- `src/main/java/com/coding/app/dispacher/` - Contains the dispatcher for handling site wrappers.
- `src/main/java/com/coding/app/wrappers/` - Contains the site wrappers for different sites.
- `src/main/resources/com/coding/app/appgui/components/` - Contains the FXML files for the GUI.
- `src/main/resources/styles.css` - Contains the CSS styles for the application.

## Technologies

- Java
- JavaFX
- Maven
- JDBC
- MARIA DB
- Docker

## contributing

MASSI RIADH SAMI