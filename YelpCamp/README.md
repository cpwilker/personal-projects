# YelpCamp

A Node.js web application project from the Udemy course - The Web Developer Bootcamp by Colt Steele

## Description
This project is allows users to share and review campgrounds in a Yelp-style application.  
Important technoligies used in the development of this app include the MERN-stack and cloud-databases.

## Live Demo
To see the app in action, go to https://yelpcamp-62093-wilkerson.herokuapp.com/

## Features
* Authentication:

    * User login with username and password

* Authorization:

    * One cannot manage posts or leave reviews without being authenticated

    * One cannot edit or delete posts and reviews created by other users

* Manage campground posts with basic functionalities:

    * Create, edit and delete posts and comments

    * Upload campground photos

    * Display campground location on Google Maps


* Flash messages responding to users' interaction with the app

* Responsive web design

### Custom Enhancements

* Update campground photos when editing campgrounds

* Improve image load time on the landing page using Cloudinary


## Getting Started
**__NOTE:__** This app contains API secrets and passwords that have been hidden deliberately, so the app cannot be run with its features on your local machine. However, feel free to clone this repository if necessary.

### Clone or download this repository
``git clone https://github.com/cpwilker/personal-projects/YelpCamp.git``
### Install dependencies
``npm install``

## Built with

### Front-end

* ejs
* MapBox API
* Bootstrap

### Back-end
* express
* mongoDB
* mongoose
* async
* crypto
* helmet
* passport
* passport-local
* express-session
* cloudinary
* geocoder
* connect-flash

### Platforms
Cloudinary
Heroku