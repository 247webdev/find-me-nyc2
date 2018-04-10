-- Find Me NYC --

Find Me NYC is a project proposal for the City of New York to manage users who use their public Data Portal API

 - Users can search for public NYC data

 -  City employees can view and manage users searching their data


How to start the application:

1. In your terminal - git clone the project
2. `cd find-my-nyc`
3. Make sure you have `http://app:8080` in your proxy on `/package.json` in the `/ui` folder
4. run `docker-compose up`
5. Go to `http://localhost:3000/signup` and create an account
6. From here you can browse NYC data by going to the `search` link


How to run all tests:
1. In your terminal - git clone the project (if you haven't already)
2. `cd find-my-nyc` (if you haven't already)
3.  Make sure you have `http://localhost:8080` in your proxy on `/package.json` in the `/ui` folder
4. Run the command `./gradlew allTests` in your terminal