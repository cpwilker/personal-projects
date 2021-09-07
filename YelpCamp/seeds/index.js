const mongoose = require('mongoose');
const cities = require('./cities');
const { places, descriptors } = require('./seedHelpers');
const Campground = require('../models/campground');

mongoose.connect('mongodb://localhost:27017/yelp-camp', {
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology: true
});

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error:"));
db.once("open", () => {
    console.log("Database connected");
});

const sample = (array) => array[Math.floor(Math.random() * array.length)];

const seedDB = async () => {
    await Campground.deleteMany({});
    for (let i = 0; i < 500; i++) {
        const random1000 = Math.floor(Math.random() * 1000);
        const price = Math.floor(Math.random() * 20) + 10;
        const camp = new Campground({
            author: '612ea512d961fea0984187b3',
            location: `${cities[random1000].city}, ${cities[random1000].state}`,
            title: `${sample(descriptors)} ${sample(places)}`,
            geometry: {
                type: "Point",
                coordinates: [
                    cities[random1000].longitude,
                    cities[random1000].latitude
                ]
            },
            images: [
                {
                    url: 'https://res.cloudinary.com/df7ldehsf/image/upload/v1630532678/YelpCamp/ueilxhczfn6ytcjbrkra.jpg',
                    filename: 'YelpCamp/ueilxhczfn6ytcjbrkra'
                },
                {
                    url: 'https://res.cloudinary.com/df7ldehsf/image/upload/v1630532677/YelpCamp/ydwrtwm4f4qqkgohz3km.jpg',
                    filename: 'YelpCamp/ydwrtwm4f4qqkgohz3km'
                }
            ],
            description: 'Nature, in the broadest sense, is the natural, physical, material world or universe. "Nature" can refer to the phenomena of the physical world, and also to life in general. Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.',
            price

        });
        await camp.save();
    }
}

seedDB().then(() => {
    mongoose.connection.close();
});