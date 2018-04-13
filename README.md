-- Find Me NYC2 --

Find Me NYC is a project proposal for the City of New York to manage users who use their public Data Portal API

 - Users can search for public NYC data
 - Users can view location data on a map

 - City employees can view and manage users searching their data


How to start the application locally:

1. In your terminal - git clone the project
2. `cd find-my-nyc`
4. run `docker-compose up`
5. Go to `http://localhost:3000/signup` and create an account
6. From here you can browse NYC data by going to the `search` link


How to run all tests:
1. In your terminal - git clone the project (if you haven't already)
2. `cd find-my-nyc` (if you haven't already)
4. Run the command `./gradlew allTests` in your terminal

Online:
1. Go to `http://find-me-nyc2.s3-website-us-west-1.amazonaws.com/signup`
2. Sign up
3. Enjoy all of NYC's public data!